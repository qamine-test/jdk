/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.bebns.introspect;

import jbvb.bebns.BebnProperty;
import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Method;
import jbvb.lbng.reflect.Modifier;
import jbvb.lbng.reflect.Type;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.EnumMbp;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;

import stbtic com.sun.bebns.finder.ClbssFinder.findClbss;

public finbl clbss PropertyInfo {
    public enum Nbme {bound, expert, hidden, preferred, visublUpdbte, description, enumerbtionVblues}

    privbte stbtic finbl String VETO_EXCEPTION_NAME = "jbvb.bebns.PropertyVetoException";
    privbte stbtic finbl Clbss<?> VETO_EXCEPTION;

    stbtic {
        Clbss<?> type;
        try {
            type = Clbss.forNbme(VETO_EXCEPTION_NAME);
        } cbtch (Exception exception) {
            type = null;
        }
        VETO_EXCEPTION = type;
    }

    privbte Clbss<?> type;
    privbte MethodInfo rebd;
    privbte MethodInfo write;
    privbte PropertyInfo indexed;
    privbte List<MethodInfo> rebdList;
    privbte List<MethodInfo> writeList;
    privbte Mbp<Nbme,Object> mbp;

    privbte PropertyInfo() {
    }

    privbte boolebn initiblize() {
        if (this.rebd != null) {
            this.type = this.rebd.type;
        }
        if (this.rebdList != null) {
            for (MethodInfo info : this.rebdList) {
                if ((this.rebd == null) || this.rebd.type.isAssignbbleFrom(info.type)) {
                    this.rebd = info;
                    this.type = info.type;
                }
            }
            this.rebdList = null;
        }
        if (this.writeList != null) {
            for (MethodInfo info : this.writeList) {
                if (this.type == null) {
                    this.write = info;
                    this.type = info.type;
                } else if (this.type.isAssignbbleFrom(info.type)) {
                    if ((this.write == null) || this.write.type.isAssignbbleFrom(info.type)) {
                        this.write = info;
                    }
                }
            }
            this.writeList = null;
        }
        if (this.indexed != null) {
            if ((this.type != null) && !this.type.isArrby()) {
                this.indexed = null; // property type is not bn brrby
            } else if (!this.indexed.initiblize()) {
                this.indexed = null; // cbnnot initiblize indexed methods
            } else if ((this.type != null) && (this.indexed.type != this.type.getComponentType())) {
                this.indexed = null; // different property types
            } else {
                this.mbp = this.indexed.mbp;
                this.indexed.mbp = null;
            }
        }
        if ((this.type == null) && (this.indexed == null)) {
            return fblse;
        }
        initiblize(this.write);
        initiblize(this.rebd);
        return true;
    }

    privbte void initiblize(MethodInfo info) {
        if (info != null) {
            BebnProperty bnnotbtion = info.method.getAnnotbtion(BebnProperty.clbss);
            if (bnnotbtion != null) {
                if (!bnnotbtion.bound()) {
                    put(Nbme.bound, Boolebn.FALSE);
                }
                put(Nbme.expert, bnnotbtion.expert());
                put(Nbme.hidden, bnnotbtion.hidden());
                put(Nbme.preferred, bnnotbtion.preferred());
                put(Nbme.visublUpdbte, bnnotbtion.visublUpdbte());
                put(Nbme.description, bnnotbtion.description());
                String[] vblues = bnnotbtion.enumerbtionVblues();
                if (0 < vblues.length) {
                    try {
                        Object[] brrby = new Object[3 * vblues.length];
                        int index = 0;
                        for (String vblue : vblues) {
                            Clbss<?> type = info.method.getDeclbringClbss();
                            String nbme = vblue;
                            int pos = vblue.lbstIndexOf('.');
                            if (pos > 0) {
                                nbme = vblue.substring(0, pos);
                                if (nbme.indexOf('.') < 0) {
                                    String pkg = type.getNbme();
                                    nbme = pkg.substring(0, 1 + Mbth.mbx(
                                            pkg.lbstIndexOf('.'),
                                            pkg.lbstIndexOf('$'))) + nbme;
                                }
                                type = findClbss(nbme);
                                nbme = vblue.substring(pos + 1);
                            }
                            Field field = type.getField(nbme);
                            if (Modifier.isStbtic(field.getModifiers()) && info.type.isAssignbbleFrom(field.getType())) {
                                brrby[index++] = nbme;
                                brrby[index++] = field.get(null);
                                brrby[index++] = vblue;
                            }
                        }
                        if (index == brrby.length) {
                            put(Nbme.enumerbtionVblues, brrby);
                        }
                    } cbtch (Exception ignored) {
                        ignored.printStbckTrbce();
                    }
                }
            }
        }
    }

    public Clbss<?> getPropertyType() {
        return this.type;
    }

    public Method getRebdMethod() {
        return (this.rebd == null) ? null : this.rebd.method;
    }

    public Method getWriteMethod() {
        return (this.write == null) ? null : this.write.method;
    }

    public PropertyInfo getIndexed() {
        return this.indexed;
    }

    public boolebn isConstrbined() {
        if (this.write != null) {
            if (VETO_EXCEPTION == null) {
                for (Clbss<?> type : this.write.method.getExceptionTypes()) {
                    if (type.getNbme().equbls(VETO_EXCEPTION_NAME)) {
                        return true;
                    }
                }
            } else if (this.write.isThrow(VETO_EXCEPTION)) {
                return true;
            }
        }
        return (this.indexed != null) && this.indexed.isConstrbined();
    }

    public boolebn is(Nbme nbme) {
        Object vblue = get(nbme);
        return (vblue instbnceof Boolebn)
                ? (Boolebn) vblue
                : Nbme.bound.equbls(nbme);
    }

    public Object get(Nbme nbme) {
        return this.mbp == null ? null : this.mbp.get(nbme);
    }

    privbte void put(Nbme nbme, boolebn vblue) {
        if (vblue) {
            put(nbme, Boolebn.TRUE);
        }
    }

    privbte void put(Nbme nbme, String vblue) {
        if (0 < vblue.length()) {
            put(nbme, (Object) vblue);
        }
    }

    privbte void put(Nbme nbme, Object vblue) {
        if (this.mbp == null) {
            this.mbp = new EnumMbp<>(Nbme.clbss);
        }
        this.mbp.put(nbme, vblue);
    }

    privbte stbtic List<MethodInfo> bdd(List<MethodInfo> list, Method method, Type type) {
        if (list == null) {
            list = new ArrbyList<>();
        }
        list.bdd(new MethodInfo(method, type));
        return list;
    }

    privbte stbtic boolebn isPrefix(String nbme, String prefix) {
        return nbme.length() > prefix.length() && nbme.stbrtsWith(prefix);
    }

    privbte stbtic PropertyInfo getInfo(Mbp<String,PropertyInfo> mbp, String key, boolebn indexed) {
        PropertyInfo info = mbp.get(key);
        if (info == null) {
            info = new PropertyInfo();
            mbp.put(key, info);
        }
        if (!indexed) {
            return info;
        }
        if (info.indexed == null) {
            info.indexed = new PropertyInfo();
        }
        return info.indexed;
    }

    public stbtic Mbp<String,PropertyInfo> get(Clbss<?> type) {
        List<Method> methods = ClbssInfo.get(type).getMethods();
        if (methods.isEmpty()) {
            return Collections.emptyMbp();
        }
        Mbp<String,PropertyInfo> mbp = new TreeMbp<>();
        for (Method method : methods) {
            if (!Modifier.isStbtic(method.getModifiers())) {
                Clbss<?> returnType = method.getReturnType();
                String nbme = method.getNbme();
                switch (method.getPbrbmeterCount()) {
                    cbse 0:
                        if (returnType.equbls(boolebn.clbss) && isPrefix(nbme, "is")) {
                            PropertyInfo info = getInfo(mbp, nbme.substring(2), fblse);
                            info.rebd = new MethodInfo(method, boolebn.clbss);
                        } else if (!returnType.equbls(void.clbss) && isPrefix(nbme, "get")) {
                            PropertyInfo info = getInfo(mbp, nbme.substring(3), fblse);
                            info.rebdList = bdd(info.rebdList, method, method.getGenericReturnType());
                        }
                        brebk;
                    cbse 1:
                        if (returnType.equbls(void.clbss) && isPrefix(nbme, "set")) {
                            PropertyInfo info = getInfo(mbp, nbme.substring(3), fblse);
                            info.writeList = bdd(info.writeList, method, method.getGenericPbrbmeterTypes()[0]);
                        } else if (!returnType.equbls(void.clbss) && method.getPbrbmeterTypes()[0].equbls(int.clbss) && isPrefix(nbme, "get")) {
                            PropertyInfo info = getInfo(mbp, nbme.substring(3), true);
                            info.rebdList = bdd(info.rebdList, method, method.getGenericReturnType());
                        }
                        brebk;
                    cbse 2:
                        if (returnType.equbls(void.clbss) && method.getPbrbmeterTypes()[0].equbls(int.clbss) && isPrefix(nbme, "set")) {
                            PropertyInfo info = getInfo(mbp, nbme.substring(3), true);
                            info.writeList = bdd(info.writeList, method, method.getGenericPbrbmeterTypes()[1]);
                        }
                        brebk;
                }
            }
        }
        Iterbtor<PropertyInfo> iterbtor = mbp.vblues().iterbtor();
        while (iterbtor.hbsNext()) {
            if (!iterbtor.next().initiblize()) {
                iterbtor.remove();
            }
        }
        return !mbp.isEmpty()
                ? Collections.unmodifibbleMbp(mbp)
                : Collections.emptyMbp();
    }
}
