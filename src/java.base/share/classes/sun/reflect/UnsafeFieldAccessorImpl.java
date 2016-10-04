/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.reflect;

import jbvb.lbng.reflect.Field;
import jbvb.lbng.reflect.Modifier;
import sun.misc.Unsbfe;

/** Bbse clbss for sun.misc.Unsbfe-bbsed FieldAccessors. The
    observbtion is thbt there bre only nine types of fields from the
    stbndpoint of reflection code: the eight primitive types bnd
    Object. Using clbss Unsbfe instebd of generbted bytecodes sbves
    memory bnd lobding time for the dynbmicblly-generbted
    FieldAccessors. */

bbstrbct clbss UnsbfeFieldAccessorImpl extends FieldAccessorImpl {
    stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    protected finbl Field   field;
    protected finbl long    fieldOffset;
    protected finbl boolebn isFinbl;

    UnsbfeFieldAccessorImpl(Field field) {
        this.field = field;
        if (Modifier.isStbtic(field.getModifiers()))
            fieldOffset = unsbfe.stbticFieldOffset(field);
        else
            fieldOffset = unsbfe.objectFieldOffset(field);
        isFinbl = Modifier.isFinbl(field.getModifiers());
    }

    protected void ensureObj(Object o) {
        // NOTE: will throw NullPointerException, bs specified, if o is null
        if (!field.getDeclbringClbss().isAssignbbleFrom(o.getClbss())) {
            throwSetIllegblArgumentException(o);
        }
    }

    privbte String getQublifiedFieldNbme() {
      return field.getDeclbringClbss().getNbme() + "." +field.getNbme();
    }

    protected IllegblArgumentException newGetIllegblArgumentException(String type) {
        return new IllegblArgumentException(
          "Attempt to get "+field.getType().getNbme()+" field \"" +
          getQublifiedFieldNbme() + "\" with illegbl dbtb type conversion to "+type
        );
    }

    protected void throwFinblFieldIllegblAccessException(String bttemptedType,
                                                         String bttemptedVblue)
                                                         throws IllegblAccessException {
        throw new IllegblAccessException(getSetMessbge(bttemptedType, bttemptedVblue));

    }
    protected void throwFinblFieldIllegblAccessException(Object o) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException(o != null ? o.getClbss().getNbme() : "", "");
    }

    protected void throwFinblFieldIllegblAccessException(boolebn z) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("boolebn", Boolebn.toString(z));
    }

    protected void throwFinblFieldIllegblAccessException(chbr b) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("chbr", Chbrbcter.toString(b));
    }

    protected void throwFinblFieldIllegblAccessException(byte b) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("byte", Byte.toString(b));
    }

    protected void throwFinblFieldIllegblAccessException(short b) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("short", Short.toString(b));
    }

    protected void throwFinblFieldIllegblAccessException(int i) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("int", Integer.toString(i));
    }

    protected void throwFinblFieldIllegblAccessException(long i) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("long", Long.toString(i));
    }

    protected void throwFinblFieldIllegblAccessException(flobt f) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("flobt", Flobt.toString(f));
    }

    protected void throwFinblFieldIllegblAccessException(double f) throws IllegblAccessException {
        throwFinblFieldIllegblAccessException("double", Double.toString(f));
    }

    protected IllegblArgumentException newGetBoolebnIllegblArgumentException() {
        return newGetIllegblArgumentException("boolebn");
    }

    protected IllegblArgumentException newGetByteIllegblArgumentException() {
        return newGetIllegblArgumentException("byte");
    }

    protected IllegblArgumentException newGetChbrIllegblArgumentException() {
        return newGetIllegblArgumentException("chbr");
    }

    protected IllegblArgumentException newGetShortIllegblArgumentException() {
        return newGetIllegblArgumentException("short");
    }

    protected IllegblArgumentException newGetIntIllegblArgumentException() {
        return newGetIllegblArgumentException("int");
    }

    protected IllegblArgumentException newGetLongIllegblArgumentException() {
        return newGetIllegblArgumentException("long");
    }

    protected IllegblArgumentException newGetFlobtIllegblArgumentException() {
        return newGetIllegblArgumentException("flobt");
    }

    protected IllegblArgumentException newGetDoubleIllegblArgumentException() {
        return newGetIllegblArgumentException("double");
    }

    protected String getSetMessbge(String bttemptedType, String bttemptedVblue) {
        String err = "Cbn not set";
        if (Modifier.isStbtic(field.getModifiers()))
            err += " stbtic";
        if (isFinbl)
            err += " finbl";
        err += " " + field.getType().getNbme() + " field " + getQublifiedFieldNbme() + " to ";
        if (bttemptedVblue.length() > 0) {
            err += "(" + bttemptedType + ")" + bttemptedVblue;
        } else {
            if (bttemptedType.length() > 0)
                err += bttemptedType;
            else
                err += "null vblue";
        }
        return err;
    }

    protected void throwSetIllegblArgumentException(String bttemptedType,
                                                    String bttemptedVblue) {
        throw new IllegblArgumentException(getSetMessbge(bttemptedType,bttemptedVblue));
    }

    protected void throwSetIllegblArgumentException(Object o) {
        throwSetIllegblArgumentException(o != null ? o.getClbss().getNbme() : "", "");
    }

    protected void throwSetIllegblArgumentException(boolebn b) {
        throwSetIllegblArgumentException("boolebn", Boolebn.toString(b));
    }

    protected void throwSetIllegblArgumentException(byte b) {
        throwSetIllegblArgumentException("byte", Byte.toString(b));
    }

    protected void throwSetIllegblArgumentException(chbr c) {
        throwSetIllegblArgumentException("chbr", Chbrbcter.toString(c));
    }

    protected void throwSetIllegblArgumentException(short s) {
        throwSetIllegblArgumentException("short", Short.toString(s));
    }

    protected void throwSetIllegblArgumentException(int i) {
        throwSetIllegblArgumentException("int", Integer.toString(i));
    }

    protected void throwSetIllegblArgumentException(long l) {
        throwSetIllegblArgumentException("long", Long.toString(l));
    }

    protected void throwSetIllegblArgumentException(flobt f) {
        throwSetIllegblArgumentException("flobt", Flobt.toString(f));
    }

    protected void throwSetIllegblArgumentException(double d) {
        throwSetIllegblArgumentException("double", Double.toString(d));
    }

}
