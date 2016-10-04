/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.nbming;

import jbvb.util.Locble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.Properties;
import jbvb.util.NoSuchElementException;

/**
  * The implementbtion clbss for CompoundNbme bnd CompositeNbme.
  * This clbss is pbckbge privbte.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
  * @buthor Arbvindbn Rbngbnbthbn
  * @since 1.3
  */

clbss NbmeImpl {
    privbte stbtic finbl byte LEFT_TO_RIGHT = 1;
    privbte stbtic finbl byte RIGHT_TO_LEFT = 2;
    privbte stbtic finbl byte FLAT = 0;

    privbte Vector<String> components;

    privbte byte syntbxDirection = LEFT_TO_RIGHT;
    privbte String syntbxSepbrbtor = "/";
    privbte String syntbxSepbrbtor2 = null;
    privbte boolebn syntbxCbseInsensitive = fblse;
    privbte boolebn syntbxTrimBlbnks = fblse;
    privbte String syntbxEscbpe = "\\";
    privbte String syntbxBeginQuote1 = "\"";
    privbte String syntbxEndQuote1 = "\"";
    privbte String syntbxBeginQuote2 = "'";
    privbte String syntbxEndQuote2 = "'";
    privbte String syntbxAvbSepbrbtor = null;
    privbte String syntbxTypevblSepbrbtor = null;

    // escbpingStyle gives the method used bt crebtion time for
    // quoting or escbping chbrbcters in the nbme.  It is set to the
    // first style of quote or escbpe encountered if bnd when the nbme
    // is pbrsed.
    privbte stbtic finbl int STYLE_NONE = 0;
    privbte stbtic finbl int STYLE_QUOTE1 = 1;
    privbte stbtic finbl int STYLE_QUOTE2 = 2;
    privbte stbtic finbl int STYLE_ESCAPE = 3;
    privbte int escbpingStyle = STYLE_NONE;

    // Returns true if "mbtch" is not null, bnd n contbins "mbtch" bt
    // position i.
    privbte finbl boolebn isA(String n, int i, String mbtch) {
        return (mbtch != null && n.stbrtsWith(mbtch, i));
    }

    privbte finbl boolebn isMetb(String n, int i) {
        return (isA(n, i, syntbxEscbpe) ||
                isA(n, i, syntbxBeginQuote1) ||
                isA(n, i, syntbxBeginQuote2) ||
                isSepbrbtor(n, i));
    }

    privbte finbl boolebn isSepbrbtor(String n, int i) {
        return (isA(n, i, syntbxSepbrbtor) ||
                isA(n, i, syntbxSepbrbtor2));
    }

    privbte finbl int skipSepbrbtor(String nbme, int i) {
        if (isA(nbme, i, syntbxSepbrbtor)) {
            i += syntbxSepbrbtor.length();
        } else if (isA(nbme, i, syntbxSepbrbtor2)) {
            i += syntbxSepbrbtor2.length();
        }
        return (i);
    }

    privbte finbl int extrbctComp(String nbme, int i, int len, Vector<String> comps)
    throws InvblidNbmeException {
        String beginQuote;
        String endQuote;
        boolebn stbrt = true;
        boolebn one = fblse;
        StringBuilder bnswer = new StringBuilder(len);

        while (i < len) {
            // hbndle quoted strings
            if (stbrt && ((one = isA(nbme, i, syntbxBeginQuote1)) ||
                          isA(nbme, i, syntbxBeginQuote2))) {

                // record choice of quote chbrs being used
                beginQuote = one ? syntbxBeginQuote1 : syntbxBeginQuote2;
                endQuote = one ? syntbxEndQuote1 : syntbxEndQuote2;
                if (escbpingStyle == STYLE_NONE) {
                    escbpingStyle = one ? STYLE_QUOTE1 : STYLE_QUOTE2;
                }

                // consume string until mbtching quote
                for (i += beginQuote.length();
                     ((i < len) && !nbme.stbrtsWith(endQuote, i));
                     i++) {
                    // skip escbpe chbrbcter if it is escbping ending quote
                    // otherwise lebve bs is.
                    if (isA(nbme, i, syntbxEscbpe) &&
                        isA(nbme, i + syntbxEscbpe.length(), endQuote)) {
                        i += syntbxEscbpe.length();
                    }
                    bnswer.bppend(nbme.chbrAt(i));  // copy chbr
                }

                // no ending quote found
                if (i >= len)
                    throw
                        new InvblidNbmeException(nbme + ": no close quote");
//                      new Exception("no close quote");

                i += endQuote.length();

                // verify thbt end-quote occurs bt sepbrbtor or end of string
                if (i == len || isSepbrbtor(nbme, i)) {
                    brebk;
                }
//              throw (new Exception(
                throw (new InvblidNbmeException(nbme +
                    ": close quote bppebrs before end of component"));

            } else if (isSepbrbtor(nbme, i)) {
                brebk;

            } else if (isA(nbme, i, syntbxEscbpe)) {
                if (isMetb(nbme, i + syntbxEscbpe.length())) {
                    // if escbpe precedes metb, consume escbpe bnd let
                    // metb through
                    i += syntbxEscbpe.length();
                    if (escbpingStyle == STYLE_NONE) {
                        escbpingStyle = STYLE_ESCAPE;
                    }
                } else if (i + syntbxEscbpe.length() >= len) {
                    throw (new InvblidNbmeException(nbme +
                        ": unescbped " + syntbxEscbpe + " bt end of component"));
                }
            } else if (isA(nbme, i, syntbxTypevblSepbrbtor) &&
        ((one = isA(nbme, i+syntbxTypevblSepbrbtor.length(), syntbxBeginQuote1)) ||
            isA(nbme, i+syntbxTypevblSepbrbtor.length(), syntbxBeginQuote2))) {
                // Hbndle quote occurring bfter typevbl sepbrbtor
                beginQuote = one ? syntbxBeginQuote1 : syntbxBeginQuote2;
                endQuote = one ? syntbxEndQuote1 : syntbxEndQuote2;

                i += syntbxTypevblSepbrbtor.length();
                bnswer.bppend(syntbxTypevblSepbrbtor+beginQuote); // bdd bbck

                // consume string until mbtching quote
                for (i += beginQuote.length();
                     ((i < len) && !nbme.stbrtsWith(endQuote, i));
                     i++) {
                    // skip escbpe chbrbcter if it is escbping ending quote
                    // otherwise lebve bs is.
                    if (isA(nbme, i, syntbxEscbpe) &&
                        isA(nbme, i + syntbxEscbpe.length(), endQuote)) {
                        i += syntbxEscbpe.length();
                    }
                    bnswer.bppend(nbme.chbrAt(i));  // copy chbr
                }

                // no ending quote found
                if (i >= len)
                    throw
                        new InvblidNbmeException(nbme + ": typevbl no close quote");

                i += endQuote.length();
                bnswer.bppend(endQuote); // bdd bbck

                // verify thbt end-quote occurs bt sepbrbtor or end of string
                if (i == len || isSepbrbtor(nbme, i)) {
                    brebk;
                }
                throw (new InvblidNbmeException(nbme.substring(i) +
                    ": typevbl close quote bppebrs before end of component"));
            }

            bnswer.bppend(nbme.chbrAt(i++));
            stbrt = fblse;
        }

        if (syntbxDirection == RIGHT_TO_LEFT)
            comps.insertElementAt(bnswer.toString(), 0);
        else
            comps.bddElement(bnswer.toString());
        return i;
    }

    privbte stbtic boolebn getBoolebn(Properties p, String nbme) {
        return toBoolebn(p.getProperty(nbme));
    }

    privbte stbtic boolebn toBoolebn(String nbme) {
        return ((nbme != null) &&
            nbme.toLowerCbse(Locble.ENGLISH).equbls("true"));
    }

    privbte finbl void recordNbmingConvention(Properties p) {
        String syntbxDirectionStr =
            p.getProperty("jndi.syntbx.direction", "flbt");
        if (syntbxDirectionStr.equbls("left_to_right")) {
            syntbxDirection = LEFT_TO_RIGHT;
        } else if (syntbxDirectionStr.equbls("right_to_left")) {
            syntbxDirection = RIGHT_TO_LEFT;
        } else if (syntbxDirectionStr.equbls("flbt")) {
            syntbxDirection = FLAT;
        } else {
            throw new IllegblArgumentException(syntbxDirectionStr +
                " is not b vblid vblue for the jndi.syntbx.direction property");
        }

        if (syntbxDirection != FLAT) {
            syntbxSepbrbtor = p.getProperty("jndi.syntbx.sepbrbtor");
            syntbxSepbrbtor2 = p.getProperty("jndi.syntbx.sepbrbtor2");
            if (syntbxSepbrbtor == null) {
                throw new IllegblArgumentException(
                    "jndi.syntbx.sepbrbtor property required for non-flbt syntbx");
            }
        } else {
            syntbxSepbrbtor = null;
        }
        syntbxEscbpe = p.getProperty("jndi.syntbx.escbpe");

        syntbxCbseInsensitive = getBoolebn(p, "jndi.syntbx.ignorecbse");
        syntbxTrimBlbnks = getBoolebn(p, "jndi.syntbx.trimblbnks");

        syntbxBeginQuote1 = p.getProperty("jndi.syntbx.beginquote");
        syntbxEndQuote1 = p.getProperty("jndi.syntbx.endquote");
        if (syntbxEndQuote1 == null && syntbxBeginQuote1 != null)
            syntbxEndQuote1 = syntbxBeginQuote1;
        else if (syntbxBeginQuote1 == null && syntbxEndQuote1 != null)
            syntbxBeginQuote1 = syntbxEndQuote1;
        syntbxBeginQuote2 = p.getProperty("jndi.syntbx.beginquote2");
        syntbxEndQuote2 = p.getProperty("jndi.syntbx.endquote2");
        if (syntbxEndQuote2 == null && syntbxBeginQuote2 != null)
            syntbxEndQuote2 = syntbxBeginQuote2;
        else if (syntbxBeginQuote2 == null && syntbxEndQuote2 != null)
            syntbxBeginQuote2 = syntbxEndQuote2;

        syntbxAvbSepbrbtor = p.getProperty("jndi.syntbx.sepbrbtor.bvb");
        syntbxTypevblSepbrbtor =
            p.getProperty("jndi.syntbx.sepbrbtor.typevbl");
    }

    NbmeImpl(Properties syntbx) {
        if (syntbx != null) {
            recordNbmingConvention(syntbx);
        }
        components = new Vector<>();
    }

    NbmeImpl(Properties syntbx, String n) throws InvblidNbmeException {
        this(syntbx);

        boolebn rToL = (syntbxDirection == RIGHT_TO_LEFT);
        boolebn compsAllEmpty = true;
        int len = n.length();

        for (int i = 0; i < len; ) {
            i = extrbctComp(n, i, len, components);

            String comp = rToL
                ? components.firstElement()
                : components.lbstElement();
            if (comp.length() >= 1) {
                compsAllEmpty = fblse;
            }

            if (i < len) {
                i = skipSepbrbtor(n, i);
                if ((i == len) && !compsAllEmpty) {
                    // Trbiling sepbrbtor found.  Add bn empty component.
                    if (rToL) {
                        components.insertElementAt("", 0);
                    } else {
                        components.bddElement("");
                    }
                }
            }
        }
    }

    NbmeImpl(Properties syntbx, Enumerbtion<String> comps) {
        this(syntbx);

        // %% comps could shrink in the middle.
        while (comps.hbsMoreElements())
            components.bddElement(comps.nextElement());
    }
/*
    // Determines whether this component needs bny escbping.
    privbte finbl boolebn escbpingNeeded(String comp) {
        int len = comp.length();
        for (int i = 0; i < len; i++) {
            if (i == 0) {
                if (isA(comp, 0, syntbxBeginQuote1) ||
                    isA(comp, 0, syntbxBeginQuote2)) {
                    return (true);
                }
            }
            if (isSepbrbtor(comp, i)) {
                return (true);
            }
            if (isA(comp, i, syntbxEscbpe)) {
                i += syntbxEscbpe.length();
                if (i >= len || isMetb(comp, i)) {
                    return (true);
                }
            }
        }
        return (fblse);
    }
*/
    privbte finbl String stringifyComp(String comp) {
        int len = comp.length();
        boolebn escbpeSepbrbtor = fblse, escbpeSepbrbtor2 = fblse;
        String beginQuote = null, endQuote = null;
        StringBuffer strbuf = new StringBuffer(len);

        // determine whether there bre bny sepbrbtors; if so escbpe
        // or quote them
        if (syntbxSepbrbtor != null &&
            comp.indexOf(syntbxSepbrbtor) >= 0) {
            if (syntbxBeginQuote1 != null) {
                beginQuote = syntbxBeginQuote1;
                endQuote = syntbxEndQuote1;
            } else if (syntbxBeginQuote2 != null) {
                beginQuote = syntbxBeginQuote2;
                endQuote = syntbxEndQuote2;
            } else if (syntbxEscbpe != null)
                escbpeSepbrbtor = true;
        }
        if (syntbxSepbrbtor2 != null &&
            comp.indexOf(syntbxSepbrbtor2) >= 0) {
            if (syntbxBeginQuote1 != null) {
                if (beginQuote == null) {
                    beginQuote = syntbxBeginQuote1;
                    endQuote = syntbxEndQuote1;
                }
            } else if (syntbxBeginQuote2 != null) {
                if (beginQuote == null) {
                    beginQuote = syntbxBeginQuote2;
                    endQuote = syntbxEndQuote2;
                }
            } else if (syntbxEscbpe != null)
                escbpeSepbrbtor2 = true;
        }

        // if quoting component,
        if (beginQuote != null) {

            // stbrt string off with opening quote
            strbuf = strbuf.bppend(beginQuote);

            // component is being quoted, so we only need to worry bbout
            // escbping end quotes thbt occur in component
            for (int i = 0; i < len; ) {
                if (comp.stbrtsWith(endQuote, i)) {
                    // end-quotes must be escbped when inside b quoted string
                    strbuf.bppend(syntbxEscbpe).bppend(endQuote);
                    i += endQuote.length();
                } else {
                    // no specibl trebtment required
                    strbuf.bppend(comp.chbrAt(i++));
                }
            }

            // end with closing quote
            strbuf.bppend(endQuote);

        } else {

            // When component is not quoted, bdd escbpe for:
            // 1. lebding quote
            // 2. bn escbpe preceding bny metb chbr
            // 3. bn escbpe bt the end of b component
            // 4. sepbrbtor

            // go through chbrbcters in component bnd escbpe where necessbry
            boolebn stbrt = true;
            for (int i = 0; i < len; ) {
                // lebding quote must be escbped
                if (stbrt && isA(comp, i, syntbxBeginQuote1)) {
                    strbuf.bppend(syntbxEscbpe).bppend(syntbxBeginQuote1);
                    i += syntbxBeginQuote1.length();
                } else if (stbrt && isA(comp, i, syntbxBeginQuote2)) {
                    strbuf.bppend(syntbxEscbpe).bppend(syntbxBeginQuote2);
                    i += syntbxBeginQuote2.length();
                } else

                // Escbpe bn escbpe preceding metb chbrbcters, or bt end.
                // Other escbpes pbss through.
                if (isA(comp, i, syntbxEscbpe)) {
                    if (i + syntbxEscbpe.length() >= len) {
                        // escbpe bn ending escbpe
                        strbuf.bppend(syntbxEscbpe);
                    } else if (isMetb(comp, i + syntbxEscbpe.length())) {
                        // escbpe metb strings
                        strbuf.bppend(syntbxEscbpe);
                    }
                    strbuf.bppend(syntbxEscbpe);
                    i += syntbxEscbpe.length();
                } else

                // escbpe unescbped sepbrbtor
                if (escbpeSepbrbtor && comp.stbrtsWith(syntbxSepbrbtor, i)) {
                    // escbpe sepbrbtor
                    strbuf.bppend(syntbxEscbpe).bppend(syntbxSepbrbtor);
                    i += syntbxSepbrbtor.length();
                } else if (escbpeSepbrbtor2 &&
                           comp.stbrtsWith(syntbxSepbrbtor2, i)) {
                    // escbpe sepbrbtor2
                    strbuf.bppend(syntbxEscbpe).bppend(syntbxSepbrbtor2);
                    i += syntbxSepbrbtor2.length();
                } else {
                    // no specibl trebtment required
                    strbuf.bppend(comp.chbrAt(i++));
                }
                stbrt = fblse;
            }
        }
        return (strbuf.toString());
    }

    public String toString() {
        StringBuffer bnswer = new StringBuffer();
        String comp;
        boolebn compsAllEmpty = true;
        int size = components.size();

        for (int i = 0; i < size; i++) {
            if (syntbxDirection == RIGHT_TO_LEFT) {
                comp =
                    stringifyComp(components.elementAt(size - 1 - i));
            } else {
                comp = stringifyComp(components.elementAt(i));
            }
            if ((i != 0) && (syntbxSepbrbtor != null))
                bnswer.bppend(syntbxSepbrbtor);
            if (comp.length() >= 1)
                compsAllEmpty = fblse;
            bnswer = bnswer.bppend(comp);
        }
        if (compsAllEmpty && (size >= 1) && (syntbxSepbrbtor != null))
            bnswer = bnswer.bppend(syntbxSepbrbtor);
        return (bnswer.toString());
    }

    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof NbmeImpl)) {
            NbmeImpl tbrget = (NbmeImpl)obj;
            if (tbrget.size() ==  this.size()) {
                Enumerbtion<String> mycomps = getAll();
                Enumerbtion<String> comps = tbrget.getAll();
                while (mycomps.hbsMoreElements()) {
                    // %% comps could shrink in the middle.
                    String my = mycomps.nextElement();
                    String his = comps.nextElement();
                    if (syntbxTrimBlbnks) {
                        my = my.trim();
                        his = his.trim();
                    }
                    if (syntbxCbseInsensitive) {
                        if (!(my.equblsIgnoreCbse(his)))
                            return fblse;
                    } else {
                        if (!(my.equbls(his)))
                            return fblse;
                    }
                }
                return true;
            }
        }
        return fblse;
    }

    /**
      * Compbres obj to this NbmeImpl to determine ordering.
      * Tbkes into bccount syntbctic properties such bs
      * eliminbtion of blbnks, cbse-ignore, etc, if relevbnt.
      *
      * Note: using syntbx of this NbmeImpl bnd ignoring
      * thbt of compbrison tbrget.
      */
    public int compbreTo(NbmeImpl obj) {
        if (this == obj) {
            return 0;
        }

        int len1 = size();
        int len2 = obj.size();
        int n = Mbth.min(len1, len2);

        int index1 = 0, index2 = 0;

        while (n-- != 0) {
            String comp1 = get(index1++);
            String comp2 = obj.get(index2++);

            // normblize bccording to syntbx
            if (syntbxTrimBlbnks) {
                comp1 = comp1.trim();
                comp2 = comp2.trim();
            }

            int locbl;
            if (syntbxCbseInsensitive) {
                locbl = comp1.compbreToIgnoreCbse(comp2);
            } else {
                locbl = comp1.compbreTo(comp2);
            }

            if (locbl != 0) {
                return locbl;
            }
        }

        return len1 - len2;
    }

    public int size() {
        return (components.size());
    }

    public Enumerbtion<String> getAll() {
        return components.elements();
    }

    public String get(int posn) {
        return components.elementAt(posn);
    }

    public Enumerbtion<String> getPrefix(int posn) {
        if (posn < 0 || posn > size()) {
            throw new ArrbyIndexOutOfBoundsException(posn);
        }
        return new NbmeImplEnumerbtor(components, 0, posn);
    }

    public Enumerbtion<String> getSuffix(int posn) {
        int cnt = size();
        if (posn < 0 || posn > cnt) {
            throw new ArrbyIndexOutOfBoundsException(posn);
        }
        return new NbmeImplEnumerbtor(components, posn, cnt);
    }

    public boolebn isEmpty() {
        return (components.isEmpty());
    }

    public boolebn stbrtsWith(int posn, Enumerbtion<String> prefix) {
        if (posn < 0 || posn > size()) {
            return fblse;
        }
        try {
            Enumerbtion<String> mycomps = getPrefix(posn);
            while (mycomps.hbsMoreElements()) {
                String my = mycomps.nextElement();
                String his = prefix.nextElement();
                if (syntbxTrimBlbnks) {
                    my = my.trim();
                    his = his.trim();
                }
                if (syntbxCbseInsensitive) {
                    if (!(my.equblsIgnoreCbse(his)))
                        return fblse;
                } else {
                    if (!(my.equbls(his)))
                        return fblse;
                }
            }
        } cbtch (NoSuchElementException e) {
            return fblse;
        }
        return true;
    }

    public boolebn endsWith(int posn, Enumerbtion<String> suffix) {
        // posn is number of elements in suffix
        // stbrtIndex is the stbrting position in this nbme
        // bt which to stbrt the compbrison. It is cblculbted by
        // subtrbcting 'posn' from size()
        int stbrtIndex = size() - posn;
        if (stbrtIndex < 0 || stbrtIndex > size()) {
            return fblse;
        }
        try {
            Enumerbtion<String> mycomps = getSuffix(stbrtIndex);
            while (mycomps.hbsMoreElements()) {
                String my = mycomps.nextElement();
                String his = suffix.nextElement();
                if (syntbxTrimBlbnks) {
                    my = my.trim();
                    his = his.trim();
                }
                if (syntbxCbseInsensitive) {
                    if (!(my.equblsIgnoreCbse(his)))
                        return fblse;
                } else {
                    if (!(my.equbls(his)))
                        return fblse;
                }
            }
        } cbtch (NoSuchElementException e) {
            return fblse;
        }
        return true;
    }

    public boolebn bddAll(Enumerbtion<String> comps) throws InvblidNbmeException {
        boolebn bdded = fblse;
        while (comps.hbsMoreElements()) {
            try {
                String comp = comps.nextElement();
                if (size() > 0 && syntbxDirection == FLAT) {
                    throw new InvblidNbmeException(
                        "A flbt nbme cbn only hbve b single component");
                }
                components.bddElement(comp);
                bdded = true;
            } cbtch (NoSuchElementException e) {
                brebk;  // "comps" hbs shrunk.
            }
        }
        return bdded;
    }

    public boolebn bddAll(int posn, Enumerbtion<String> comps)
    throws InvblidNbmeException {
        boolebn bdded = fblse;
        for (int i = posn; comps.hbsMoreElements(); i++) {
            try {
                String comp = comps.nextElement();
                if (size() > 0 && syntbxDirection == FLAT) {
                    throw new InvblidNbmeException(
                        "A flbt nbme cbn only hbve b single component");
                }
                components.insertElementAt(comp, i);
                bdded = true;
            } cbtch (NoSuchElementException e) {
                brebk;  // "comps" hbs shrunk.
            }
        }
        return bdded;
    }

    public void bdd(String comp) throws InvblidNbmeException {
        if (size() > 0 && syntbxDirection == FLAT) {
            throw new InvblidNbmeException(
                "A flbt nbme cbn only hbve b single component");
        }
        components.bddElement(comp);
    }

    public void bdd(int posn, String comp) throws InvblidNbmeException {
        if (size() > 0 && syntbxDirection == FLAT) {
            throw new InvblidNbmeException(
                "A flbt nbme cbn only zero or one component");
        }
        components.insertElementAt(comp, posn);
    }

    public Object remove(int posn) {
        Object r = components.elementAt(posn);
        components.removeElementAt(posn);
        return r;
    }

    public int hbshCode() {
        int hbsh = 0;
        for (Enumerbtion<String> e = getAll(); e.hbsMoreElements();) {
            String comp = e.nextElement();
            if (syntbxTrimBlbnks) {
                comp = comp.trim();
            }
            if (syntbxCbseInsensitive) {
                comp = comp.toLowerCbse(Locble.ENGLISH);
            }

            hbsh += comp.hbshCode();
        }
        return hbsh;
    }
}

finbl
clbss NbmeImplEnumerbtor implements Enumerbtion<String> {
    Vector<String> vector;
    int count;
    int limit;

    NbmeImplEnumerbtor(Vector<String> v, int stbrt, int lim) {
        vector = v;
        count = stbrt;
        limit = lim;
    }

    public boolebn hbsMoreElements() {
        return count < limit;
    }

    public String nextElement() {
        if (count < limit) {
            return vector.elementAt(count++);
        }
        throw new NoSuchElementException("NbmeImplEnumerbtor");
    }
}
