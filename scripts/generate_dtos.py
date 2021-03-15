from pathlib import Path

from paths import dtos_path, models_path, dtos_test_path
import re
from typing import List, Optional, Set


# for path in models_path.glob('*.java'):
#    with open(path, 'r') as f:
#        cls = f.read()
#        print(cls)
#        class_name = path.stem
#        attributes = get_attributes(cls)


def get_all_models() -> List[Path]:
    return [model for model in models_path.glob('*.java')]


ALL_MODELS_PATH = get_all_models()

ALL_MODELS = [model.stem for model in ALL_MODELS_PATH]

DTOS_PACKAGE_NAME = 'dtos'

DTO_STATIC_DEPENDENCIES = [
    'lombok.Data',
    'lombok.AllArgsConstructor',
    'lombok.NoArgsConstructor',
    'lombok.Builder'
]

DTO_CLASS_STATIC_ANNOTATIONS = [
    'Data',
    'AllArgsConstructor',
    'NoArgsConstructor',
    'Builder'
]

DTO_PREFIX = 'Dto'

JAVA_BUILTIN_TYPES = [
    'Long',
    'long',
    'float'
    'int',
    'String',
    'boolean',
]


class ClassNotFoundException(Exception):
    def __init__(self):
        super().__init__('key word class has not founded, maybe the file stores an enum')


class Dto:
    def __init__(self):
        self.package: Package = Package(None)
        self.dependencies: List[Dependency] = [Dependency(dep) for dep in DTO_STATIC_DEPENDENCIES]
        self.class_: Class = Class(None, DTO_CLASS_STATIC_ANNOTATIONS)
        self.attributes: List[Attribute] = []

    def _resolve_attr_dependencies(self):
        for att in self.attributes:
            if att.attribute_type.packages:
                self.dependencies.extend([pck for pck in att.attribute_type.packages if pck not in self.dependencies])

    def text(self):
        self._resolve_attr_dependencies()
        return self.package.text() \
               + Dependency.text_from_list(self.dependencies) \
               + self.class_.text() \
               + Attribute.text_from_list(self.attributes) + '}'


class MyBase:
    def __repr__(self):
        attrs = [att for att in dir(self) if att[0] != '_' and not callable(self.__getattribute__(att))]

        string = f'{self.__class__.__name__}: ('
        for att in attrs:
            string += f'\t {att}: {self.__getattribute__(att)}'
        return string + ')'

    def text(self):
        pass

    def to_dto(self):
        pass

    @staticmethod
    def text_from_list(lst):
        if not lst:
            return ''
        text = ''
        for i in lst:
            text += i.text()
        return text + '\n\n'


class Package(MyBase):
    def __init__(self, package_name):
        self.package_name: str = package_name

    @property
    def parent(self):
        parent = re.match(r'[.|\w+]+(?=\.\w+$)', self.package_name)
        return Package(parent.group()) if parent else None

    def join(self, package_name):
        return Package(f'{self.package_name}.{package_name}')

    def text(self):
        return f'package {self.package_name};\n\n\n'

    def to_dto(self):
        return self.parent.join(DTOS_PACKAGE_NAME)


class Dependency(MyBase):
    def __init__(self, dependency):
        self.dependency = dependency

    def text(self):
        return f'import {self.dependency};\n'

    def to_dto(self):
        return Dependency('new dependency')

    def __eq__(self, other):
        return self.dependency == other.dependency

    def __ne__(self, other):
        return self.__eq__(other)

    # @staticmethod
    # def get_dto_static_dependencies():
    #     return [Dependency(dep) for dep in DTO_STATIC_DEPENDENCIES]


class Class(MyBase):
    def __init__(self, class_name, annotations):
        self.class_name = class_name
        self.annotations = annotations

    def text(self):
        annotations = ''
        for ann in self.annotations:
            annotations += f'@{ann}\n'
        return f'{annotations}public class {self.class_name} {"{"}\n'

    def to_dto(self):
        return Class(self.class_name + DTO_PREFIX, DTO_CLASS_STATIC_ANNOTATIONS)


class Attribute(MyBase):
    def __init__(self, attribute_name, attribute_type):
        self.attribute_name = attribute_name
        self.attribute_type: Type = attribute_type

    def to_dto(self):
        attr = Attribute(self.attribute_name, self.attribute_type)
        if attr.attribute_type.type_name in ALL_MODELS:
            attr.attribute_type.type_name += DTO_PREFIX
        if attr.attribute_type.data_type in ALL_MODELS:
            attr.attribute_type.data_type += DTO_PREFIX

        return attr

    def text(self):
        return f'\tprivate {self.attribute_type.text()} {self.attribute_name};\n'


class Type(MyBase):
    def __init__(self, type_name, data_type):
        self.type_name = type_name
        self.data_type = data_type
        self.packages = []

    def text(self):
        return f'{self.type_name}{"<" + self.data_type + ">" if self.data_type else ""}'


def get_package(cls) -> Package:
    """returns package as a string"""
    return Package(re.search(r'^package\s+([^;]+)', cls).group(1))


def get_dependencies(cls) -> List[Dependency]:
    pattern = re.compile(r'import\s(\D[\w.*]+)(?=;)')
    return [Dependency(dep) for dep in pattern.findall(cls)]


def get_class_name(cls) -> Class:
    #    pattern = re.compile(r'(?P<annotations>@\w+(?=[\s|\n]+))[\s\n]*\w*class\s(\b\w+\b)[\s|\n]*\{')
    annotations = re.search(r'@.*class', cls, re.DOTALL)
    annotations_list = re.findall(r'@\b(\D\w+)\b', annotations.group()) if annotations else None
    class_ = re.search(r'class\s+(\D\w+)(?:\simplements\s(\D\w+))*[\s|\n]*{', cls)
    if class_:
        return Class(class_.group(1), annotations_list)
    else:
        raise ClassNotFoundException()


def get_attributes(cls) -> List[Attribute]:
    pattern = re.compile(r'private\s+(\w+)(?:<(\w+)>)*\s+(\w+);')
    attributes = pattern.findall(cls)
    package_pattern = lambda x: re.compile(r'import\s([\w.]+' + x + ')(?=;)')

    for index, att in enumerate(attributes):
        attr = Attribute(att[2], Type(att[0], None))

        if package := package_pattern(att[0]).search(cls):
            attr.attribute_type.packages.append(Dependency(package.group(1)))

        if att[1]:
            attr.attribute_type.data_type = att[1]
            if package := package_pattern(att[1]).search(cls):
                attr.attribute_type.packages.append(Dependency(package.group(1)))

        attributes[index] = attr

    return attributes


def generate_dto(cls):
    try:
        class_info = get_class_name(cls).to_dto()
    except ClassNotFoundException:
        raise ClassNotFoundException()

    dto = Dto()
    dto.package = get_package(cls).to_dto()
    dto.class_ = class_info

    attributes = [att.to_dto() for att in get_attributes(cls)]
    dto.attributes.extend(attributes)

    return dto.text()


def main():
    for model_path in ALL_MODELS_PATH:
        print(f'##########  process for {model_path}')
        with open(model_path) as f:
            cls = f.read()

        dto_path = dtos_test_path / (model_path.stem + 'Dto.java')
        dto_path.touch()
        try:
            with open(dto_path, 'w') as f:
                f.write(generate_dto(cls))
        except ClassNotFoundException as e:
            print(str(e))
            dto_path.unlink()


main()
