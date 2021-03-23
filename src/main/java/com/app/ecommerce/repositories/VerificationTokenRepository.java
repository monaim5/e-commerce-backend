/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ecommerce.models.structure.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}