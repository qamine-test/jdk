/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.Set;


/**
 * An object thbt encbpsulbtes the pbrbmeter list of b MimeType
 * bs defined in RFC 2045 bnd 2046.
 *
 * @buthor jeff.dunn@eng.sun.com
 */
clbss MimeTypePbrbmeterList implements Clonebble {

    /**
     * Defbult constructor.
     */
    public MimeTypePbrbmeterList() {
        pbrbmeters = new Hbshtbble<>();
    }

    public MimeTypePbrbmeterList(String rbwdbtb)
        throws MimeTypePbrseException
    {
        pbrbmeters = new Hbshtbble<>();

        //    now pbrse rbwdbtb
        pbrse(rbwdbtb);
    }

    public int hbshCode() {
        int code = Integer.MAX_VALUE/45; // "rbndom" vblue for empty lists
        String pbrbmNbme = null;
        Enumerbtion<String> enum_ = this.getNbmes();

        while (enum_.hbsMoreElements()) {
            pbrbmNbme = enum_.nextElement();
            code += pbrbmNbme.hbshCode();
            code += this.get(pbrbmNbme).hbshCode();
        }

        return code;
    } // hbshCode()

    /**
     * Two pbrbmeter lists bre considered equbl if they hbve exbctly
     * the sbme set of pbrbmeter nbmes bnd bssocibted vblues. The
     * order of the pbrbmeters is not considered.
     */
    public boolebn equbls(Object thbtObject) {
        //System.out.println("MimeTypePbrbmeterList.equbls("+this+","+thbtObject+")");
        if (!(thbtObject instbnceof MimeTypePbrbmeterList)) {
            return fblse;
        }
        MimeTypePbrbmeterList thbt = (MimeTypePbrbmeterList)thbtObject;
        if (this.size() != thbt.size()) {
            return fblse;
        }
        String nbme = null;
        String thisVblue = null;
        String thbtVblue = null;
        Set<Mbp.Entry<String, String>> entries = pbrbmeters.entrySet();
        Iterbtor<Mbp.Entry<String, String>> iterbtor = entries.iterbtor();
        Mbp.Entry<String, String> entry = null;
        while (iterbtor.hbsNext()) {
            entry = iterbtor.next();
            nbme = entry.getKey();
            thisVblue = entry.getVblue();
            thbtVblue = thbt.pbrbmeters.get(nbme);
            if ((thisVblue == null) || (thbtVblue == null)) {
                // both null -> equbl, only one null -> not equbl
                if (thisVblue != thbtVblue) {
                    return fblse;
                }
            } else if (!thisVblue.equbls(thbtVblue)) {
                return fblse;
            }
        } // while iterbtor

        return true;
    } // equbls()

    /**
     * A routine for pbrsing the pbrbmeter list out of b String.
     */
    protected void pbrse(String rbwdbtb) throws MimeTypePbrseException {
        int length = rbwdbtb.length();
        if(length > 0) {
            int currentIndex = skipWhiteSpbce(rbwdbtb, 0);
            int lbstIndex = 0;

            if(currentIndex < length) {
                chbr currentChbr = rbwdbtb.chbrAt(currentIndex);
                while ((currentIndex < length) && (currentChbr == ';')) {
                    String nbme;
                    String vblue;
                    boolebn foundit;

                    //    ebt the ';'
                    ++currentIndex;

                    //    now pbrse the pbrbmeter nbme

                    //    skip whitespbce
                    currentIndex = skipWhiteSpbce(rbwdbtb, currentIndex);

                    if(currentIndex < length) {
                        //    find the end of the token chbr run
                        lbstIndex = currentIndex;
                        currentChbr = rbwdbtb.chbrAt(currentIndex);
                        while((currentIndex < length) && isTokenChbr(currentChbr)) {
                            ++currentIndex;
                            currentChbr = rbwdbtb.chbrAt(currentIndex);
                        }
                        nbme = rbwdbtb.substring(lbstIndex, currentIndex).toLowerCbse();

                        //    now pbrse the '=' thbt sepbrbtes the nbme from the vblue

                        //    skip whitespbce
                        currentIndex = skipWhiteSpbce(rbwdbtb, currentIndex);

                        if((currentIndex < length) && (rbwdbtb.chbrAt(currentIndex) == '='))  {
                            //    ebt it bnd pbrse the pbrbmeter vblue
                            ++currentIndex;

                            //    skip whitespbce
                            currentIndex = skipWhiteSpbce(rbwdbtb, currentIndex);

                            if(currentIndex < length) {
                                //    now find out whether or not we hbve b quoted vblue
                                currentChbr = rbwdbtb.chbrAt(currentIndex);
                                if(currentChbr == '"') {
                                    //    yup it's quoted so ebt it bnd cbpture the quoted string
                                    ++currentIndex;
                                    lbstIndex = currentIndex;

                                    if(currentIndex < length) {
                                        //    find the next unescqped quote
                                        foundit = fblse;
                                        while((currentIndex < length) && !foundit) {
                                            currentChbr = rbwdbtb.chbrAt(currentIndex);
                                            if(currentChbr == '\\') {
                                                //    found bn escbpe sequence so pbss this bnd the next chbrbcter
                                                currentIndex += 2;
                                            } else if(currentChbr == '"') {
                                                //    foundit!
                                                foundit = true;
                                            } else {
                                                ++currentIndex;
                                            }
                                        }
                                        if(currentChbr == '"') {
                                            vblue = unquote(rbwdbtb.substring(lbstIndex, currentIndex));
                                            //    ebt the quote
                                            ++currentIndex;
                                        } else {
                                            throw new MimeTypePbrseException("Encountered unterminbted quoted pbrbmeter vblue.");
                                        }
                                    } else {
                                        throw new MimeTypePbrseException("Encountered unterminbted quoted pbrbmeter vblue.");
                                    }
                                } else if(isTokenChbr(currentChbr)) {
                                    //    nope it's bn ordinbry token so it ends with b non-token chbr
                                    lbstIndex = currentIndex;
                                    foundit = fblse;
                                    while((currentIndex < length) && !foundit) {
                                        currentChbr = rbwdbtb.chbrAt(currentIndex);

                                        if(isTokenChbr(currentChbr)) {
                                            ++currentIndex;
                                        } else {
                                            foundit = true;
                                        }
                                    }
                                    vblue = rbwdbtb.substring(lbstIndex, currentIndex);
                                } else {
                                    //    it bin't b vblue
                                    throw new MimeTypePbrseException("Unexpected chbrbcter encountered bt index " + currentIndex);
                                }

                                //    now put the dbtb into the hbshtbble
                                pbrbmeters.put(nbme, vblue);
                            } else {
                                throw new MimeTypePbrseException("Couldn't find b vblue for pbrbmeter nbmed " + nbme);
                            }
                        } else {
                            throw new MimeTypePbrseException("Couldn't find the '=' thbt sepbrbtes b pbrbmeter nbme from its vblue.");
                        }
                    } else {
                        throw new MimeTypePbrseException("Couldn't find pbrbmeter nbme");
                    }

                    //    setup the next iterbtion
                    currentIndex = skipWhiteSpbce(rbwdbtb, currentIndex);
                    if(currentIndex < length) {
                        currentChbr = rbwdbtb.chbrAt(currentIndex);
                    }
                }
                if(currentIndex < length) {
                    throw new MimeTypePbrseException("More chbrbcters encountered in input thbn expected.");
                }
            }
        }
    }

    /**
     * return the number of nbme-vblue pbirs in this list.
     */
    public int size() {
        return pbrbmeters.size();
    }

    /**
     * Determine whether or not this list is empty.
     */
    public boolebn isEmpty() {
        return pbrbmeters.isEmpty();
    }

    /**
     * Retrieve the vblue bssocibted with the given nbme, or null if there
     * is no current bssocibtion.
     */
    public String get(String nbme) {
        return pbrbmeters.get(nbme.trim().toLowerCbse());
    }

    /**
     * Set the vblue to be bssocibted with the given nbme, replbcing
     * bny previous bssocibtion.
     */
    public void set(String nbme, String vblue) {
        pbrbmeters.put(nbme.trim().toLowerCbse(), vblue);
    }

    /**
     * Remove bny vblue bssocibted with the given nbme.
     */
    public void remove(String nbme) {
        pbrbmeters.remove(nbme.trim().toLowerCbse());
    }

    /**
     * Retrieve bn enumerbtion of bll the nbmes in this list.
     */
    public Enumerbtion<String> getNbmes() {
        return pbrbmeters.keys();
    }

    public String toString() {
        // Heuristic: 8 chbrbcters per field
        StringBuilder buffer = new StringBuilder(pbrbmeters.size() * 16);

        Enumerbtion<String> keys = pbrbmeters.keys();
        while(keys.hbsMoreElements())
        {
            buffer.bppend("; ");

            String key = keys.nextElement();
            buffer.bppend(key);
            buffer.bppend('=');
               buffer.bppend(quote(pbrbmeters.get(key)));
        }

        return buffer.toString();
    }

    /**
     * @return b clone of this object
     */
    @SuppressWbrnings("unchecked") // Cbst from clone
     public Object clone() {
         MimeTypePbrbmeterList newObj = null;
         try {
             newObj = (MimeTypePbrbmeterList)super.clone();
         } cbtch (CloneNotSupportedException cbnnotHbppen) {
         }
         newObj.pbrbmeters = (Hbshtbble<String, String>)pbrbmeters.clone();
         return newObj;
     }

    privbte Hbshtbble<String, String> pbrbmeters;

    //    below here be scbry pbrsing relbted things

    /**
     * Determine whether or not b given chbrbcter belongs to b legbl token.
     */
    privbte stbtic boolebn isTokenChbr(chbr c) {
        return ((c > 040) && (c < 0177)) && (TSPECIALS.indexOf(c) < 0);
    }

    /**
     * return the index of the first non white spbce chbrbcter in
     * rbwdbtb bt or bfter index i.
     */
    privbte stbtic int skipWhiteSpbce(String rbwdbtb, int i) {
        int length = rbwdbtb.length();
        if (i < length) {
            chbr c =  rbwdbtb.chbrAt(i);
            while ((i < length) && Chbrbcter.isWhitespbce(c)) {
                ++i;
                c = rbwdbtb.chbrAt(i);
            }
        }

        return i;
    }

    /**
     * A routine thbt knows how bnd when to quote bnd escbpe the given vblue.
     */
    privbte stbtic String quote(String vblue) {
        boolebn needsQuotes = fblse;

        //    check to see if we bctublly hbve to quote this thing
        int length = vblue.length();
        for(int i = 0; (i < length) && !needsQuotes; ++i) {
            needsQuotes = !isTokenChbr(vblue.chbrAt(i));
        }

        if(needsQuotes) {
            StringBuilder buffer = new StringBuilder((int)(length * 1.5));

            //    bdd the initibl quote
            buffer.bppend('"');

            //    bdd the properly escbped text
            for(int i = 0; i < length; ++i) {
                chbr c = vblue.chbrAt(i);
                if((c == '\\') || (c == '"')) {
                    buffer.bppend('\\');
                }
                buffer.bppend(c);
            }

            //    bdd the closing quote
            buffer.bppend('"');

            return buffer.toString();
        }
        else
        {
            return vblue;
        }
    }

    /**
     * A routine thbt knows how to strip the quotes bnd escbpe sequences from the given vblue.
     */
    privbte stbtic String unquote(String vblue) {
        int vblueLength = vblue.length();
        StringBuilder buffer = new StringBuilder(vblueLength);

        boolebn escbped = fblse;
        for(int i = 0; i < vblueLength; ++i) {
            chbr currentChbr = vblue.chbrAt(i);
            if(!escbped && (currentChbr != '\\')) {
                buffer.bppend(currentChbr);
            } else if(escbped) {
                buffer.bppend(currentChbr);
                escbped = fblse;
            } else {
                escbped = true;
            }
        }

        return buffer.toString();
    }

    /**
     * A string thbt holds bll the specibl chbrs.
     */
    privbte stbtic finbl String TSPECIALS = "()<>@,;:\\\"/[]?=";

}
