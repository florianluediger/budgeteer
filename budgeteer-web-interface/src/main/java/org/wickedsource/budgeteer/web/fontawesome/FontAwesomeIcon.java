package org.wickedsource.budgeteer.web.fontawesome;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class FontAwesomeIcon extends WebMarkupContainer {

    public FontAwesomeIcon(String id, IModel<FontAwesomeIconType> model) {
        super(id, model);
    }

    public FontAwesomeIcon(String id, FontAwesomeIconType iconType) {
        super(id, Model.of(iconType));
    }

    @Override
    protected void onComponentTag(ComponentTag tag) {
        if (!"i".equals(tag.getName())) {
            throw new WicketRuntimeException("A FontAwesomeIcon component is only allowed on <i>-Tags!");
        }
        tag.put("class", ((FontAwesomeIconType) getDefaultModelObject()).getCssClass());
    }
}