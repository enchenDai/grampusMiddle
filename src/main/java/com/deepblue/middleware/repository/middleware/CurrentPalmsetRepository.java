package com.deepblue.middleware.repository.middleware;

import com.deepblue.middleware.domian.CurrentPalmset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by enchen on 10/12/17.
 */

public interface CurrentPalmsetRepository extends JpaRepository<CurrentPalmset, Long> {

    List<CurrentPalmset> findByEnable(Integer enable);

}
