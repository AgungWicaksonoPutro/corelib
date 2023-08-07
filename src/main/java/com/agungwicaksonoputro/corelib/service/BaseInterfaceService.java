package com.agungwicaksonoputro.corelib.service;

import com.agungwicaksonoputro.corelib.model.base.BaseInterfaceRequest;
import com.agungwicaksonoputro.corelib.model.base.BaseInterfaceResponse;

public interface BaseInterfaceService<T extends BaseInterfaceRequest, V extends BaseInterfaceResponse> {
    V execute(T input);
}
