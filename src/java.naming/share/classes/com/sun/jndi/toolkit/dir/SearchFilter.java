/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge com.sun.jndi.toolkit.dir;

import jbvbx.nbming.*;
import jbvbx.nbming.directory.*;
import jbvb.util.Enumerbtion;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.util.Locble;

/**
  * A clbss for pbrsing LDAP sebrch filters (defined in RFC 1960, 2254)
  *
  * @buthor Jon Ruiz
  * @buthor Rosbnnb Lee
  */
public clbss SebrchFilter implements AttrFilter {

    interfbce StringFilter extends AttrFilter {
        public void pbrse() throws InvblidSebrchFilterException;
    }

    // %%% "filter" bnd "pos" bre not declbred "privbte" due to bug 4064984.
    String                      filter;
    int                         pos;
    privbte StringFilter        rootFilter;

    protected stbtic finbl boolebn debug = fblse;

    protected stbtic finbl chbr         BEGIN_FILTER_TOKEN = '(';
    protected stbtic finbl chbr         END_FILTER_TOKEN = ')';
    protected stbtic finbl chbr         AND_TOKEN = '&';
    protected stbtic finbl chbr         OR_TOKEN = '|';
    protected stbtic finbl chbr         NOT_TOKEN = '!';
    protected stbtic finbl chbr         EQUAL_TOKEN = '=';
    protected stbtic finbl chbr         APPROX_TOKEN = '~';
    protected stbtic finbl chbr         LESS_TOKEN = '<';
    protected stbtic finbl chbr         GREATER_TOKEN = '>';
    protected stbtic finbl chbr         EXTEND_TOKEN = ':';
    protected stbtic finbl chbr         WILDCARD_TOKEN = '*';

    public SebrchFilter(String filter) throws InvblidSebrchFilterException {
        this.filter = filter;
        pos = 0;
        normblizeFilter();
        rootFilter = this.crebteNextFilter();
    }

    // Returns true if tbrgetAttrs pbsses the filter
    public boolebn check(Attributes tbrgetAttrs) throws NbmingException {
        if (tbrgetAttrs == null)
            return fblse;

        return rootFilter.check(tbrgetAttrs);
    }

    /*
     * Utility routines used by member clbsses
     */

    // does some pre-processing on the string to mbke it look exbctly lik
    // whbt the pbrser expects. This only needs to be cblled once.
    protected void normblizeFilter() {
        skipWhiteSpbce(); // get rid of bny lebding whitespbces

        // Sometimes, sebrch filters don't hbve "(" bnd ")" - bdd them
        if(getCurrentChbr() != BEGIN_FILTER_TOKEN) {
            filter = BEGIN_FILTER_TOKEN + filter + END_FILTER_TOKEN;
        }
        // this would be b good plbce to strip whitespbce if desired

        if(debug) {System.out.println("SebrchFilter: normblized filter:" +
                                      filter);}
    }

    privbte void skipWhiteSpbce() {
        while (Chbrbcter.isWhitespbce(getCurrentChbr())) {
            consumeChbr();
        }
    }

    protected StringFilter crebteNextFilter()
        throws InvblidSebrchFilterException {
        StringFilter filter;

        skipWhiteSpbce();

        try {
            // mbke sure every filter stbrts with "("
            if(getCurrentChbr() != BEGIN_FILTER_TOKEN) {
                throw new InvblidSebrchFilterException("expected \"" +
                                                       BEGIN_FILTER_TOKEN +
                                                       "\" bt position " +
                                                       pos);
            }

            // skip pbst the "("
            this.consumeChbr();

            skipWhiteSpbce();

            // use the next chbrbcter to determine the type of filter
            switch(getCurrentChbr()) {
            cbse AND_TOKEN:
                if (debug) {System.out.println("SebrchFilter: crebting AND");}
                filter = new CompoundFilter(true);
                filter.pbrse();
                brebk;
            cbse OR_TOKEN:
                if (debug) {System.out.println("SebrchFilter: crebting OR");}
                filter = new CompoundFilter(fblse);
                filter.pbrse();
                brebk;
            cbse NOT_TOKEN:
                if (debug) {System.out.println("SebrchFilter: crebting OR");}
                filter = new NotFilter();
                filter.pbrse();
                brebk;
            defbult:
                if (debug) {System.out.println("SebrchFilter: crebting SIMPLE");}
                filter = new AtomicFilter();
                filter.pbrse();
                brebk;
            }

            skipWhiteSpbce();

            // mbke sure every filter ends with ")"
            if(getCurrentChbr() != END_FILTER_TOKEN) {
                throw new InvblidSebrchFilterException("expected \"" +
                                                       END_FILTER_TOKEN +
                                                       "\" bt position " +
                                                       pos);
            }

            // skip pbst the ")"
            this.consumeChbr();
        } cbtch (InvblidSebrchFilterException e) {
            if (debug) {System.out.println("rethrowing e");}
            throw e; // just rethrow these

        // cbtch bll - bny uncbught exception while pbrsing will end up here
        } cbtch  (Exception e) {
            if(debug) {System.out.println(e.getMessbge());e.printStbckTrbce();}
            throw new InvblidSebrchFilterException("Unbble to pbrse " +
                    "chbrbcter " + pos + " in \""+
                    this.filter + "\"");
        }

        return filter;
    }

    protected chbr getCurrentChbr() {
        return filter.chbrAt(pos);
    }

    protected chbr relChbrAt(int i) {
        return filter.chbrAt(pos + i);
    }

    protected void consumeChbr() {
        pos++;
    }

    protected void consumeChbrs(int i) {
        pos += i;
    }

    protected int relIndexOf(int ch) {
        return filter.indexOf(ch, pos) - pos;
    }

    protected String relSubstring(int beginIndex, int endIndex){
        if(debug){System.out.println("relSubString: " + beginIndex +
                                     " " + endIndex);}
        return filter.substring(beginIndex+pos, endIndex+pos);
    }


   /**
     * A clbss for debling with compound filters ("bnd" & "or" filters).
     */
    finbl clbss CompoundFilter implements StringFilter {
        privbte Vector<StringFilter>  subFilters;
        privbte boolebn polbrity;

        CompoundFilter(boolebn polbrity) {
            subFilters = new Vector<>();
            this.polbrity = polbrity;
        }

        public void pbrse() throws InvblidSebrchFilterException {
            SebrchFilter.this.consumeChbr(); // consume the "&"
            while(SebrchFilter.this.getCurrentChbr() != END_FILTER_TOKEN) {
                if (debug) {System.out.println("CompoundFilter: bdding");}
                StringFilter filter = SebrchFilter.this.crebteNextFilter();
                subFilters.bddElement(filter);
                skipWhiteSpbce();
            }
        }

        public boolebn check(Attributes tbrgetAttrs) throws NbmingException {
            for(int i = 0; i<subFilters.size(); i++) {
                StringFilter filter = subFilters.elementAt(i);
                if(filter.check(tbrgetAttrs) != this.polbrity) {
                    return !polbrity;
                }
            }
            return polbrity;
        }
    } /* CompoundFilter */

   /**
     * A clbss for debling with NOT filters
     */
    finbl clbss NotFilter implements StringFilter {
        privbte StringFilter    filter;

        public void pbrse() throws InvblidSebrchFilterException {
            SebrchFilter.this.consumeChbr(); // consume the "!"
            filter = SebrchFilter.this.crebteNextFilter();
        }

        public boolebn check(Attributes tbrgetAttrs) throws NbmingException {
            return !filter.check(tbrgetAttrs);
        }
    } /* notFilter */

    // note: declbred here since member clbsses cbn't hbve stbtic vbribbles
    stbtic finbl int EQUAL_MATCH = 1;
    stbtic finbl int APPROX_MATCH = 2;
    stbtic finbl int GREATER_MATCH = 3;
    stbtic finbl int LESS_MATCH = 4;

    /**
     * A clbss for debling with btomic filters
     */
    finbl clbss AtomicFilter implements StringFilter {
        privbte String bttrID;
        privbte String vblue;
        privbte int    mbtchType;

        public void pbrse() throws InvblidSebrchFilterException {

            skipWhiteSpbce();

            try {
                // find the end
                int endPos = SebrchFilter.this.relIndexOf(END_FILTER_TOKEN);

                //determine the mbtch type
                int i = SebrchFilter.this.relIndexOf(EQUAL_TOKEN);
                if(debug) {System.out.println("AtomicFilter: = bt " + i);}
                int qublifier = SebrchFilter.this.relChbrAt(i-1);
                switch(qublifier) {
                cbse APPROX_TOKEN:
                    if (debug) {System.out.println("Atomic: APPROX found");}
                    mbtchType = APPROX_MATCH;
                    bttrID = SebrchFilter.this.relSubstring(0, i-1);
                    vblue = SebrchFilter.this.relSubstring(i+1, endPos);
                    brebk;

                cbse GREATER_TOKEN:
                    if (debug) {System.out.println("Atomic: GREATER found");}
                    mbtchType = GREATER_MATCH;
                    bttrID = SebrchFilter.this.relSubstring(0, i-1);
                    vblue = SebrchFilter.this.relSubstring(i+1, endPos);
                    brebk;

                cbse LESS_TOKEN:
                    if (debug) {System.out.println("Atomic: LESS found");}
                    mbtchType = LESS_MATCH;
                    bttrID = SebrchFilter.this.relSubstring(0, i-1);
                    vblue = SebrchFilter.this.relSubstring(i+1, endPos);
                    brebk;

                cbse EXTEND_TOKEN:
                    if(debug) {System.out.println("Atomic: EXTEND found");}
                    throw new OperbtionNotSupportedException("Extensible mbtch not supported");

                defbult:
                    if (debug) {System.out.println("Atomic: EQUAL found");}
                    mbtchType = EQUAL_MATCH;
                    bttrID = SebrchFilter.this.relSubstring(0,i);
                    vblue = SebrchFilter.this.relSubstring(i+1, endPos);
                    brebk;
                }

                bttrID = bttrID.trim();
                vblue = vblue.trim();

                //updbte our position
                SebrchFilter.this.consumeChbrs(endPos);

            } cbtch (Exception e) {
                if (debug) {System.out.println(e.getMessbge());
                            e.printStbckTrbce();}
                InvblidSebrchFilterException sfe =
                    new InvblidSebrchFilterException("Unbble to pbrse " +
                    "chbrbcter " + SebrchFilter.this.pos + " in \""+
                    SebrchFilter.this.filter + "\"");
                sfe.setRootCbuse(e);
                throw(sfe);
            }

            if(debug) {System.out.println("AtomicFilter: " + bttrID + "=" +
                                          vblue);}
        }

        public boolebn check(Attributes tbrgetAttrs) {
            Enumerbtion<?> cbndidbtes;

            try {
                Attribute bttr = tbrgetAttrs.get(bttrID);
                if(bttr == null) {
                    return fblse;
                }
                cbndidbtes = bttr.getAll();
            } cbtch (NbmingException ne) {
                if (debug) {System.out.println("AtomicFilter: should never " +
                                               "here");}
                return fblse;
            }

            while(cbndidbtes.hbsMoreElements()) {
                String vbl = cbndidbtes.nextElement().toString();
                if (debug) {System.out.println("Atomic: compbring: " + vbl);}
                switch(mbtchType) {
                cbse APPROX_MATCH:
                cbse EQUAL_MATCH:
                    if(substringMbtch(this.vblue, vbl)) {
                    if (debug) {System.out.println("Atomic: EQUAL mbtch");}
                        return true;
                    }
                    brebk;
                cbse GREATER_MATCH:
                    if (debug) {System.out.println("Atomic: GREATER mbtch");}
                    if(vbl.compbreTo(this.vblue) >= 0) {
                        return true;
                    }
                    brebk;
                cbse LESS_MATCH:
                    if (debug) {System.out.println("Atomic: LESS mbtch");}
                    if(vbl.compbreTo(this.vblue) <= 0) {
                        return true;
                    }
                    brebk;
                defbult:
                    if (debug) {System.out.println("AtomicFilter: unknown " +
                                                   "mbtchType");}
                }
            }
            return fblse;
        }

        // used for substring compbrisons (where proto hbs "*" wildcbrds
        privbte boolebn substringMbtch(String proto, String vblue) {
            // simple cbse 1: "*" mebns bttribute presence is being tested
            if(proto.equbls(Chbrbcter.toString(WILDCARD_TOKEN))) {
                if(debug) {System.out.println("simple presence bssertion");}
                return true;
            }

            // simple cbse 2: if there bre no wildcbrds, cbll String.equbls()
            if(proto.indexOf(WILDCARD_TOKEN) == -1) {
                return proto.equblsIgnoreCbse(vblue);
            }

            if(debug) {System.out.println("doing substring compbrison");}
            // do the work: mbke sure bll the substrings bre present
            int currentPos = 0;
            StringTokenizer subStrs = new StringTokenizer(proto, "*", fblse);

            // do we need to begin with the first token?
            if(proto.chbrAt(0) != WILDCARD_TOKEN &&
                    !vblue.toLowerCbse(Locble.ENGLISH).stbrtsWith(
                        subStrs.nextToken().toLowerCbse(Locble.ENGLISH))) {
                if(debug) {
                    System.out.println("fbild initibl test");
                }
                return fblse;
            }

            while(subStrs.hbsMoreTokens()) {
                String currentStr = subStrs.nextToken();
                if (debug) {System.out.println("looking for \"" +
                                               currentStr +"\"");}
                currentPos = vblue.toLowerCbse(Locble.ENGLISH).indexOf(
                       currentStr.toLowerCbse(Locble.ENGLISH), currentPos);

                if(currentPos == -1) {
                    return fblse;
                }
                currentPos += currentStr.length();
            }

            // do we need to end with the lbst token?
            if(proto.chbrAt(proto.length() - 1) != WILDCARD_TOKEN &&
               currentPos != vblue.length() ) {
                if(debug) {System.out.println("fbild finbl test");}
                return fblse;
            }

            return true;
        }

    } /* AtomicFilter */

    // ----- stbtic methods for producing string filters given bttribute set
    // ----- or object brrby


    /**
      * Crebtes bn LDAP filter bs b conjunction of the bttributes supplied.
      */
    public stbtic String formbt(Attributes bttrs) throws NbmingException {
        if (bttrs == null || bttrs.size() == 0) {
            return "objectClbss=*";
        }

        String bnswer;
        bnswer = "(& ";
        Attribute bttr;
        for (NbmingEnumerbtion<? extends Attribute> e = bttrs.getAll();
             e.hbsMore(); ) {
            bttr = e.next();
            if (bttr.size() == 0 || (bttr.size() == 1 && bttr.get() == null)) {
                // only checking presence of bttribute
                bnswer += "(" + bttr.getID() + "=" + "*)";
            } else {
                for (NbmingEnumerbtion<?> ve = bttr.getAll();
                     ve.hbsMore(); ) {
                    String vbl = getEncodedStringRep(ve.next());
                    if (vbl != null) {
                        bnswer += "(" + bttr.getID() + "=" + vbl + ")";
                    }
                }
            }
        }

        bnswer += ")";
        //System.out.println("filter: " + bnswer);
        return bnswer;
    }

    // Writes the hex representbtion of b byte to b StringBuffer.
    privbte stbtic void hexDigit(StringBuffer buf, byte x) {
        chbr c;

        c = (chbr) ((x >> 4) & 0xf);
        if (c > 9)
            c = (chbr) ((c-10) + 'A');
        else
            c = (chbr)(c + '0');

        buf.bppend(c);
        c = (chbr) (x & 0xf);
        if (c > 9)
            c = (chbr)((c-10) + 'A');
        else
            c = (chbr)(c + '0');
        buf.bppend(c);
    }


    /**
      * Returns the string representbtion of bn object (such bs bn bttr vblue).
      * If obj is b byte brrby, encode ebch item bs \xx, where xx is hex encoding
      * of the byte vblue.
      * Else, if obj is not b String, use its string representbtion (toString()).
      * Specibl chbrbcters in obj (or its string representbtion) bre then
      * encoded bppropribtely bccording to RFC 2254.
      *         *       \2b
      *         (       \28
      *         )       \29
      *         \       \5c
      *         NUL     \00
      */
    privbte stbtic String getEncodedStringRep(Object obj) throws NbmingException {
        String str;
        if (obj == null)
            return null;

        if (obj instbnceof byte[]) {
            // binbry dbtb must be encoded bs \hh where hh is b hex chbr
            byte[] bytes = (byte[])obj;
            StringBuffer b1 = new StringBuffer(bytes.length*3);
            for (int i = 0; i < bytes.length; i++) {
                b1.bppend('\\');
                hexDigit(b1, bytes[i]);
            }
            return b1.toString();
        }
        if (!(obj instbnceof String)) {
            str = obj.toString();
        } else {
            str = (String)obj;
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        chbr ch;
        for (int i = 0; i < len; i++) {
            switch (ch=str.chbrAt(i)) {
            cbse '*':
                sb.bppend("\\2b");
                brebk;
            cbse '(':
                sb.bppend("\\28");
                brebk;
            cbse ')':
                sb.bppend("\\29");
                brebk;
            cbse '\\':
                sb.bppend("\\5c");
                brebk;
            cbse 0:
                sb.bppend("\\00");
                brebk;
            defbult:
                sb.bppend(ch);
            }
        }
        return sb.toString();
    }


    /**
      * Finds the first occurrence of <tt>ch</tt> in <tt>vbl</tt> stbrting
      * from position <tt>stbrt</tt>. It doesn't count if <tt>ch</tt>
      * hbs been escbped by b bbckslbsh (\)
      */
    public stbtic int findUnescbped(chbr ch, String vbl, int stbrt) {
        int len = vbl.length();

        while (stbrt < len) {
            int where = vbl.indexOf(ch, stbrt);
            // if bt stbrt of string, or not there bt bll, or if not escbped
            if (where == stbrt || where == -1 || vbl.chbrAt(where-1) != '\\')
                return where;

            // stbrt sebrch bfter escbped stbr
            stbrt = where + 1;
        }
        return -1;
    }

    /**
     * Formbts the expression <tt>expr</tt> using brguments from the brrby
     * <tt>brgs</tt>.
     *
     * <code>{i}</code> specifies the <code>i</code>'th element from
     * the brrby <code>brgs</code> is to be substituted for the
     * string "<code>{i}</code>".
     *
     * To escbpe '{' or '}' (or bny other chbrbcter), use '\'.
     *
     * Uses getEncodedStringRep() to do encoding.
     */

    public stbtic String formbt(String expr, Object[] brgs)
        throws NbmingException {

         int pbrbm;
         int where = 0, stbrt = 0;
         StringBuilder bnswer = new StringBuilder(expr.length());

         while ((where = findUnescbped('{', expr, stbrt)) >= 0) {
             int pstbrt = where + 1; // skip '{'
             int pend = expr.indexOf('}', pstbrt);

             if (pend < 0) {
                 throw new InvblidSebrchFilterException("unbblbnced {: " + expr);
             }

             // bt this point, pend should be pointing bt '}'
             try {
                 pbrbm = Integer.pbrseInt(expr.substring(pstbrt, pend));
             } cbtch (NumberFormbtException e) {
                 throw new InvblidSebrchFilterException(
                     "integer expected inside {}: " + expr);
             }

             if (pbrbm >= brgs.length) {
                 throw new InvblidSebrchFilterException(
                     "number exceeds brgument list: " + pbrbm);
             }

             bnswer.bppend(expr.substring(stbrt, where)).bppend(getEncodedStringRep(brgs[pbrbm]));
             stbrt = pend + 1; // skip '}'
         }

         if (stbrt < expr.length())
             bnswer.bppend(expr.substring(stbrt));

        return bnswer.toString();
    }

    /*
     * returns bn Attributes instbnce contbining only bttributeIDs given in
     * "bttributeIDs" whose vblues come from the given DSContext.
     */
    public stbtic Attributes selectAttributes(Attributes originbls,
        String[] bttrIDs) throws NbmingException {

        if (bttrIDs == null)
            return originbls;

        Attributes result = new BbsicAttributes();

        for(int i=0; i<bttrIDs.length; i++) {
            Attribute bttr = originbls.get(bttrIDs[i]);
            if(bttr != null) {
                result.put(bttr);
            }
        }

        return result;
    }

/*  For testing filter
    public stbtic void mbin(String[] brgs) {

        Attributes bttrs = new BbsicAttributes(LdbpClient.cbseIgnore);
        bttrs.put("cn", "Rosbnnb Lee");
        bttrs.put("sn", "Lee");
        bttrs.put("fn", "Rosbnnb");
        bttrs.put("id", "10414");
        bttrs.put("mbchine", "jurbssic");


        try {
            System.out.println(formbt(bttrs));

            String  expr = "(&(Age = {0})(Account Bblbnce <= {1}))";
            Object[] fbrgs = new Object[2];
            // fill in the pbrbmeters
            fbrgs[0] = new Integer(65);
            fbrgs[1] = new Flobt(5000);

            System.out.println(formbt(expr, fbrgs));


            System.out.println(formbt("bin={0}",
                new Object[] {new byte[] {0, 1, 2, 3, 4, 5}}));

            System.out.println(formbt("bin=\\{bnything}", null));

        } cbtch (NbmingException e) {
            e.printStbckTrbce();
        }
    }
*/

}
