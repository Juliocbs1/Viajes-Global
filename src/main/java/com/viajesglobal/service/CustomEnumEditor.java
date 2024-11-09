package com.viajesglobal.service;

import java.beans.PropertyEditorSupport;

public class CustomEnumEditor extends PropertyEditorSupport {
    private final Class<? extends Enum> enumClass;

    public CustomEnumEditor(Class<? extends Enum> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public void setAsText(String text) {
        if (text != null && !text.isEmpty()) {
            try {
                // Convierte el texto a mayúsculas para que coincida con los nombres del enum
                setValue(Enum.valueOf(enumClass, text.toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Manejo de la excepción
                setValue(null); // O lanza una excepción personalizada si lo prefieres
            }
        } else {
            setValue(null);
        }
    }

}
