package com.sxx.sivir.core.dal.manager.impl;

import com.sxx.sivir.core.common.base.BaseManagerImpl;
import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.dao.CarDao;
import com.sxx.sivir.core.dal.manager.CarManager;
import org.springframework.stereotype.Component;

@Component
public class CarManagerImpl extends BaseManagerImpl<CarDao, Car> implements CarManager{

}
