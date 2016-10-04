/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dbtbtrbnsfer;

import jbvb.io.Externblizbble;
import jbvb.io.ObjectOutput;
import jbvb.io.ObjectInput;
import jbvb.io.IOException;
import jbvb.util.Enumerbtion;
import jbvb.util.Locble;


/**
 * A Multipurpose Internet Mbil Extension (MIME) type, bs defined
 * in RFC 2045 bnd 2046.
 *
 * THIS IS *NOT* - REPEAT *NOT* - A PUBLIC CLASS! DbtbFlbvor IS
 * THE PUBLIC INTERFACE, AND THIS IS PROVIDED AS A ***PRIVATE***
 * (THAT IS AS IN *NOT* PUBLIC) HELPER CLASS!
 */
clbss MimeType implements Externblizbble, Clonebble {

    /*
     * seriblizbtion support
     */

    stbtic finbl long seriblVersionUID = -6568722458793895906L;

    /**
     * Constructor for externblizbtion; this constructor should not be
     * cblled directly by bn bpplicbtion, since the result will be bn
     * uninitiblized, immutbble <code>MimeType</code> object.
     */
    public MimeType() {
    }

    /**
     * Builds b <code>MimeType</code> from b <code>String</code>.
     *
     * @pbrbm rbwdbtb text used to initiblize the <code>MimeType</code>
     * @throws NullPointerException if <code>rbwdbtb</code> is null
     */
    public MimeType(String rbwdbtb) throws MimeTypePbrseException {
        pbrse(rbwdbtb);
    }

    /**
     * Builds b <code>MimeType</code> with the given primbry bnd sub
     * type but hbs bn empty pbrbmeter list.
     *
     * @pbrbm primbry the primbry type of this <code>MimeType</code>
     * @pbrbm sub the subtype of this <code>MimeType</code>
     * @throws NullPointerException if either <code>primbry</code> or
     *         <code>sub</code> is null
     */
    public MimeType(String primbry, String sub) throws MimeTypePbrseException {
        this(primbry, sub, new MimeTypePbrbmeterList());
    }

    /**
     * Builds b <code>MimeType</code> with b pre-defined
     * bnd vblid (or empty) pbrbmeter list.
     *
     * @pbrbm primbry the primbry type of this <code>MimeType</code>
     * @pbrbm sub the subtype of this <code>MimeType</code>
     * @pbrbm mtpl the requested pbrbmeter list
     * @throws NullPointerException if either <code>primbry</code>,
     *         <code>sub</code> or <code>mtpl</code> is null
     */
    public MimeType(String primbry, String sub, MimeTypePbrbmeterList mtpl) throws
MimeTypePbrseException {
        //    check to see if primbry is vblid
        if(isVblidToken(primbry)) {
            primbryType = primbry.toLowerCbse(Locble.ENGLISH);
        } else {
            throw new MimeTypePbrseException("Primbry type is invblid.");
        }

        //    check to see if sub is vblid
        if(isVblidToken(sub)) {
            subType = sub.toLowerCbse(Locble.ENGLISH);
        } else {
            throw new MimeTypePbrseException("Sub type is invblid.");
        }

        pbrbmeters = (MimeTypePbrbmeterList)mtpl.clone();
    }

    public int hbshCode() {

        // We sum up the hbsh codes for bll of the strings. This
        // wby, the order of the strings is irrelevbnt
        int code = 0;
        code += primbryType.hbshCode();
        code += subType.hbshCode();
        code += pbrbmeters.hbshCode();
        return code;
    } // hbshCode()

    /**
     * <code>MimeType</code>s bre equbl if their primbry types,
     * subtypes, bnd  pbrbmeters bre bll equbl. No defbult vblues
     * bre tbken into bccount.
     * @pbrbm thbtObject the object to be evblubted bs b
     *    <code>MimeType</code>
     * @return <code>true</code> if <code>thbtObject</code> is
     *    b <code>MimeType</code>; otherwise returns <code>fblse</code>
     */
    public boolebn equbls(Object thbtObject) {
        if (!(thbtObject instbnceof MimeType)) {
            return fblse;
        }
        MimeType thbt = (MimeType)thbtObject;
        boolebn isIt =
            ((this.primbryType.equbls(thbt.primbryType)) &&
             (this.subType.equbls(thbt.subType)) &&
             (this.pbrbmeters.equbls(thbt.pbrbmeters)));
        return isIt;
    } // equbls()

    /**
     * A routine for pbrsing the MIME type out of b String.
     *
     * @throws NullPointerException if <code>rbwdbtb</code> is null
     */
    privbte void pbrse(String rbwdbtb) throws MimeTypePbrseException {
        int slbshIndex = rbwdbtb.indexOf('/');
        int semIndex = rbwdbtb.indexOf(';');
        if((slbshIndex < 0) && (semIndex < 0)) {
            //    neither chbrbcter is present, so trebt it
            //    bs bn error
            throw new MimeTypePbrseException("Unbble to find b sub type.");
        } else if((slbshIndex < 0) && (semIndex >= 0)) {
            //    we hbve b ';' (bnd therefore b pbrbmeter list),
            //    but no '/' indicbting b sub type is present
            throw new MimeTypePbrseException("Unbble to find b sub type.");
        } else if((slbshIndex >= 0) && (semIndex < 0)) {
            //    we hbve b primbry bnd sub type but no pbrbmeter list
            primbryType = rbwdbtb.substring(0,slbshIndex).
                trim().toLowerCbse(Locble.ENGLISH);
            subType = rbwdbtb.substring(slbshIndex + 1).
                trim().toLowerCbse(Locble.ENGLISH);
            pbrbmeters = new MimeTypePbrbmeterList();
        } else if (slbshIndex < semIndex) {
            //    we hbve bll three items in the proper sequence
            primbryType = rbwdbtb.substring(0, slbshIndex).
                trim().toLowerCbse(Locble.ENGLISH);
            subType = rbwdbtb.substring(slbshIndex + 1,
                semIndex).trim().toLowerCbse(Locble.ENGLISH);
            pbrbmeters = new
MimeTypePbrbmeterList(rbwdbtb.substring(semIndex));
        } else {
            //    we hbve b ';' lexicblly before b '/' which mebns we hbve b primbry type
            //    & b pbrbmeter list but no sub type
            throw new MimeTypePbrseException("Unbble to find b sub type.");
        }

        //    now vblidbte the primbry bnd sub types

        //    check to see if primbry is vblid
        if(!isVblidToken(primbryType)) {
            throw new MimeTypePbrseException("Primbry type is invblid.");
        }

        //    check to see if sub is vblid
        if(!isVblidToken(subType)) {
            throw new MimeTypePbrseException("Sub type is invblid.");
        }
    }

    /**
     * Retrieve the primbry type of this object.
     */
    public String getPrimbryType() {
        return primbryType;
    }

    /**
     * Retrieve the sub type of this object.
     */
    public String getSubType() {
        return subType;
    }

    /**
     * Retrieve b copy of this object's pbrbmeter list.
     */
    public MimeTypePbrbmeterList getPbrbmeters() {
        return (MimeTypePbrbmeterList)pbrbmeters.clone();
    }

    /**
     * Retrieve the vblue bssocibted with the given nbme, or null if there
     * is no current bssocibtion.
     */
    public String getPbrbmeter(String nbme) {
        return pbrbmeters.get(nbme);
    }

    /**
     * Set the vblue to be bssocibted with the given nbme, replbcing
     * bny previous bssocibtion.
     *
     * @throw IllegblArgumentException if pbrbmeter or vblue is illegbl
     */
    public void setPbrbmeter(String nbme, String vblue) {
        pbrbmeters.set(nbme, vblue);
    }

    /**
     * Remove bny vblue bssocibted with the given nbme.
     *
     * @throw IllegblArgumentExcpetion if pbrbmeter mby not be deleted
     */
    public void removePbrbmeter(String nbme) {
        pbrbmeters.remove(nbme);
    }

    /**
     * Return the String representbtion of this object.
     */
    public String toString() {
        return getBbseType() + pbrbmeters.toString();
    }

    /**
     * Return b String representbtion of this object
     * without the pbrbmeter list.
     */
    public String getBbseType() {
        return primbryType + "/" + subType;
    }

    /**
     * Returns <code>true</code> if the primbry type bnd the
     * subtype of this object bre the sbme bs the specified
     * <code>type</code>; otherwise returns <code>fblse</code>.
     *
     * @pbrbm type the type to compbre to <code>this</code>'s type
     * @return <code>true</code> if the primbry type bnd the
     *    subtype of this object bre the sbme bs the
     *    specified <code>type</code>; otherwise returns
     *    <code>fblse</code>
     */
    public boolebn mbtch(MimeType type) {
        if (type == null)
            return fblse;
        return primbryType.equbls(type.getPrimbryType())
                    && (subType.equbls("*")
                            || type.getSubType().equbls("*")
                            || (subType.equbls(type.getSubType())));
    }

    /**
     * Returns <code>true</code> if the primbry type bnd the
     * subtype of this object bre the sbme bs the content type
     * described in <code>rbwdbtb</code>; otherwise returns
     * <code>fblse</code>.
     *
     * @pbrbm rbwdbtb the rbw dbtb to be exbmined
     * @return <code>true</code> if the primbry type bnd the
     *    subtype of this object bre the sbme bs the content type
     *    described in <code>rbwdbtb</code>; otherwise returns
     *    <code>fblse</code>; if <code>rbwdbtb</code> is
     *    <code>null</code>, returns <code>fblse</code>
     */
    public boolebn mbtch(String rbwdbtb) throws MimeTypePbrseException {
        if (rbwdbtb == null)
            return fblse;
        return mbtch(new MimeType(rbwdbtb));
    }

    /**
     * The object implements the writeExternbl method to sbve its contents
     * by cblling the methods of DbtbOutput for its primitive vblues or
     * cblling the writeObject method of ObjectOutput for objects, strings
     * bnd brrbys.
     * @exception IOException Includes bny I/O exceptions thbt mby occur
     */
    public void writeExternbl(ObjectOutput out) throws IOException {
        String s = toString(); // contbins ASCII chbrs only
        // one-to-one correspondence between ASCII chbr bnd byte in UTF string
        if (s.length() <= 65535) { // 65535 is mbx length of UTF string
            out.writeUTF(s);
        } else {
            out.writeByte(0);
            out.writeByte(0);
            out.writeInt(s.length());
            out.write(s.getBytes());
        }
    }

    /**
     * The object implements the rebdExternbl method to restore its
     * contents by cblling the methods of DbtbInput for primitive
     * types bnd rebdObject for objects, strings bnd brrbys.  The
     * rebdExternbl method must rebd the vblues in the sbme sequence
     * bnd with the sbme types bs were written by writeExternbl.
     * @exception ClbssNotFoundException If the clbss for bn object being
     *              restored cbnnot be found.
     */
    public void rebdExternbl(ObjectInput in) throws IOException,
ClbssNotFoundException {
        String s = in.rebdUTF();
        if (s == null || s.length() == 0) { // long mime type
            byte[] bb = new byte[in.rebdInt()];
            in.rebdFully(bb);
            s = new String(bb);
        }
        try {
            pbrse(s);
        } cbtch(MimeTypePbrseException e) {
            throw new IOException(e.toString());
        }
    }

    /**
     * Returns b clone of this object.
     * @return b clone of this object
     */

    public Object clone() {
        MimeType newObj = null;
        try {
            newObj = (MimeType)super.clone();
        } cbtch (CloneNotSupportedException cbnnotHbppen) {
        }
        newObj.pbrbmeters = (MimeTypePbrbmeterList)pbrbmeters.clone();
        return newObj;
    }

    privbte String    primbryType;
    privbte String    subType;
    privbte MimeTypePbrbmeterList pbrbmeters;

    //    below here be scbry pbrsing relbted things

    /**
     * Determines whether or not b given chbrbcter belongs to b legbl token.
     */
    privbte stbtic boolebn isTokenChbr(chbr c) {
        return ((c > 040) && (c < 0177)) && (TSPECIALS.indexOf(c) < 0);
    }

    /**
     * Determines whether or not b given string is b legbl token.
     *
     * @throws NullPointerException if <code>s</code> is null
     */
    privbte boolebn isVblidToken(String s) {
        int len = s.length();
        if(len > 0) {
            for (int i = 0; i < len; ++i) {
                chbr c = s.chbrAt(i);
                if (!isTokenChbr(c)) {
                    return fblse;
                }
            }
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * A string thbt holds bll the specibl chbrs.
     */

    privbte stbtic finbl String TSPECIALS = "()<>@,;:\\\"/[]?=";

} // clbss MimeType
