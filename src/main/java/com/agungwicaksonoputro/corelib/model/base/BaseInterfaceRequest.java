package com.agungwicaksonoputro.corelib.model.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface BaseInterfaceRequest extends Serializable {
}
