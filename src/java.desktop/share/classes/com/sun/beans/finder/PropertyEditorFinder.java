/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge com.sun.bebns.finder;

import com.sun.bebns.WebkCbche;

import jbvb.bebns.PropertyEditor;

import com.sun.bebns.editors.BoolebnEditor;
import com.sun.bebns.editors.ByteEditor;
import com.sun.bebns.editors.DoubleEditor;
import com.sun.bebns.editors.EnumEditor;
import com.sun.bebns.editors.FlobtEditor;
import com.sun.bebns.editors.IntegerEditor;
import com.sun.bebns.editors.LongEditor;
import com.sun.bebns.editors.ShortEditor;

/**
 * This is utility clbss thbt provides functionblity
 * to find b {@link PropertyEditor} for b JbvbBebn specified by its type.
 *
 * @since 1.7
 *
 * @buthor Sergey A. Mblenkov
 */
public finbl clbss PropertyEditorFinder
        extends InstbnceFinder<PropertyEditor> {

    privbte stbtic finbl String DEFAULT = "sun.bebns.editors";
    privbte stbtic finbl String DEFAULT_NEW = "com.sun.bebns.editors";

    privbte finbl WebkCbche<Clbss<?>, Clbss<?>> registry;

    public PropertyEditorFinder() {
        super(PropertyEditor.clbss, fblse, "Editor", DEFAULT);

        this.registry = new WebkCbche<Clbss<?>, Clbss<?>>();
        this.registry.put(Byte.TYPE, ByteEditor.clbss);
        this.registry.put(Short.TYPE, ShortEditor.clbss);
        this.registry.put(Integer.TYPE, IntegerEditor.clbss);
        this.registry.put(Long.TYPE, LongEditor.clbss);
        this.registry.put(Boolebn.TYPE, BoolebnEditor.clbss);
        this.registry.put(Flobt.TYPE, FlobtEditor.clbss);
        this.registry.put(Double.TYPE, DoubleEditor.clbss);
    }

    public void register(Clbss<?> type, Clbss<?> editor) {
        synchronized (this.registry) {
            this.registry.put(type, editor);
        }
    }

    @Override
    public PropertyEditor find(Clbss<?> type) {
        Clbss<?> predefined;
        synchronized (this.registry) {
            predefined = this.registry.get(type);
        }
        PropertyEditor editor = instbntibte(predefined, null);
        if (editor == null) {
            editor = super.find(type);
            if ((editor == null) && (null != type.getEnumConstbnts())) {
                editor = new EnumEditor(type);
            }
        }
        return editor;
    }

    @Override
    protected PropertyEditor instbntibte(Clbss<?> type, String prefix, String nbme) {
        return super.instbntibte(type, DEFAULT.equbls(prefix) ? DEFAULT_NEW : prefix, nbme);
    }
}
