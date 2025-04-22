package com.shoto.spring.conversion;

import java.beans.PropertyEditor;

/**
 * @see PropertyEditor
 */
public class PropertyEditorDemo {

    public static void main(String[] args) {
        StringToPropertiesPropertyEditor editor = new StringToPropertiesPropertyEditor();
        editor.setAsText("name=zhangsan");
        System.out.println(editor.getAsText());
    }
}
