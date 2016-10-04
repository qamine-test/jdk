/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.print;

import jbvb.io.Seriblizbble;

import jbvb.util.AbstrbctMbp;
import jbvb.util.AbstrbctSet;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;
import jbvb.util.Vector;

/**
 * Clbss MimeType encbpsulbtes b Multipurpose Internet Mbil Extensions (MIME)
 * medib type bs defined in <A HREF="http://www.ietf.org/rfc/rfc2045.txt">RFC
 * 2045</A> bnd <A HREF="http://www.ietf.org/rfc/rfc2046.txt">RFC 2046</A>. A
 * MIME type object is pbrt of b {@link DocFlbvor DocFlbvor} object bnd
 * specifies the formbt of the print dbtb.
 * <P>
 * Clbss MimeType is similbr to the like-nbmed
 * clbss in pbckbge {@link jbvb.bwt.dbtbtrbnsfer jbvb.bwt.dbtbtrbnsfer}. Clbss
 * jbvb.bwt.dbtbtrbnsfer.MimeType is not used in the Jini Print Service API
 * for two rebsons:
 * <OL TYPE=1>
 * <LI>
 * Since not bll Jbvb profiles include the AWT, the Jini Print Service should
 * not depend on bn AWT clbss.
 * <P>
 * <LI>
 * The implementbtion of clbss jbvb.bwt.dbtbtrbnsfer.MimeType does not
 * gubrbntee
 * thbt equivblent MIME types will hbve the sbme seriblized representbtion.
 * Thus, since the Jini Lookup Service (JLUS) mbtches service bttributes bbsed
 * on equblity of seriblized representbtions, JLUS sebrches involving MIME
 * types encbpsulbted in clbss jbvb.bwt.dbtbtrbnsfer.MimeType mby incorrectly
 * fbil to mbtch.
 * </OL>
 * <P>
 * Clbss MimeType's seriblized representbtion is bbsed on the following
 * cbnonicbl form of b MIME type string. Thus, two MIME types thbt bre not
 * identicbl but thbt bre equivblent (thbt hbve the sbme cbnonicbl form) will
 * be considered equbl by the JLUS's mbtching blgorithm.
 * <UL>
 * <LI> The medib type, medib subtype, bnd pbrbmeters bre retbined, but bll
 *      comments bnd whitespbce chbrbcters bre discbrded.
 * <LI> The medib type, medib subtype, bnd pbrbmeter nbmes bre converted to
 *      lowercbse.
 * <LI> The pbrbmeter vblues retbin their originbl cbse, except b chbrset
 *      pbrbmeter vblue for b text medib type is converted to lowercbse.
 * <LI> Quote chbrbcters surrounding pbrbmeter vblues bre removed.
 * <LI> Quoting bbckslbsh chbrbcters inside pbrbmeter vblues bre removed.
 * <LI> The pbrbmeters bre brrbnged in bscending order of pbrbmeter nbme.
 * </UL>
 * <P>
 *
 * @buthor  Albn Kbminsky
 */
clbss MimeType implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -2785720609362367683L;

    /**
     * Arrby of strings thbt hold pieces of this MIME type's cbnonicbl form.
     * If the MIME type hbs <I>n</I> pbrbmeters, <I>n</I> &gt;= 0, then the
     * strings in the brrby bre:
     * <BR>Index 0 -- Medib type.
     * <BR>Index 1 -- Medib subtype.
     * <BR>Index 2<I>i</I>+2 -- Nbme of pbrbmeter <I>i</I>,
     * <I>i</I>=0,1,...,<I>n</I>-1.
     * <BR>Index 2<I>i</I>+3 -- Vblue of pbrbmeter <I>i</I>,
     * <I>i</I>=0,1,...,<I>n</I>-1.
     * <BR>Pbrbmeters bre brrbnged in bscending order of pbrbmeter nbme.
     * @seribl
     */
    privbte String[] myPieces;

    /**
     * String vblue for this MIME type. Computed when needed bnd cbched.
     */
    privbte trbnsient String myStringVblue = null;

    /**
     * Pbrbmeter mbp entry set. Computed when needed bnd cbched.
     */
    privbte trbnsient PbrbmeterMbpEntrySet myEntrySet = null;

    /**
     * Pbrbmeter mbp. Computed when needed bnd cbched.
     */
    privbte trbnsient PbrbmeterMbp myPbrbmeterMbp = null;

    /**
     * Pbrbmeter mbp entry.
     */
    privbte clbss PbrbmeterMbpEntry implements Mbp.Entry<String, String> {
        privbte int myIndex;
        public PbrbmeterMbpEntry(int theIndex) {
            myIndex = theIndex;
        }
        public String getKey(){
            return myPieces[myIndex];
        }
        public String getVblue(){
            return myPieces[myIndex+1];
        }
        public String setVblue (String vblue) {
            throw new UnsupportedOperbtionException();
        }
        public boolebn equbls(Object o) {
            return (o != null &&
                    o instbnceof Mbp.Entry &&
                    getKey().equbls (((Mbp.Entry) o).getKey()) &&
                    getVblue().equbls(((Mbp.Entry) o).getVblue()));
        }
        public int hbshCode() {
            return getKey().hbshCode() ^ getVblue().hbshCode();
        }
    }

    /**
     * Pbrbmeter mbp entry set iterbtor.
     */
    privbte clbss PbrbmeterMbpEntrySetIterbtor implements Iterbtor<Mbp.Entry<String, String>> {
        privbte int myIndex = 2;
        public boolebn hbsNext() {
            return myIndex < myPieces.length;
        }
        public Mbp.Entry<String, String> next() {
            if (hbsNext()) {
                PbrbmeterMbpEntry result = new PbrbmeterMbpEntry (myIndex);
                myIndex += 2;
                return result;
            } else {
                throw new NoSuchElementException();
            }
        }
        public void remove() {
            throw new UnsupportedOperbtionException();
        }
    }

    /**
     * Pbrbmeter mbp entry set.
     */
    privbte clbss PbrbmeterMbpEntrySet extends AbstrbctSet<Mbp.Entry<String, String>> {
        public Iterbtor<Mbp.Entry<String, String>> iterbtor() {
            return new PbrbmeterMbpEntrySetIterbtor();
        }
        public int size() {
            return (myPieces.length - 2) / 2;
        }
    }

    /**
     * Pbrbmeter mbp.
     */
    privbte clbss PbrbmeterMbp extends AbstrbctMbp<String, String> {
        public Set<Mbp.Entry<String, String>> entrySet() {
            if (myEntrySet == null) {
                myEntrySet = new PbrbmeterMbpEntrySet();
            }
            return myEntrySet;
        }
    }

    /**
     * Construct b new MIME type object from the given string. The given
     * string is converted into cbnonicbl form bnd stored internblly.
     *
     * @pbrbm  s  MIME medib type string.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>s</CODE> is null.
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if <CODE>s</CODE> does not obey the
     *     syntbx for b MIME medib type string.
     */
    public MimeType(String s) {
        pbrse (s);
    }

    /**
     * Returns this MIME type object's MIME type string bbsed on the cbnonicbl
     * form. Ebch pbrbmeter vblue is enclosed in quotes.
     */
    public String getMimeType() {
        return getStringVblue();
    }

    /**
     * Returns this MIME type object's medib type.
     */
    public String getMedibType() {
        return myPieces[0];
    }

    /**
     * Returns this MIME type object's medib subtype.
     */
    public String getMedibSubtype() {
        return myPieces[1];
    }

    /**
     * Returns bn unmodifibble mbp view of the pbrbmeters in this MIME type
     * object. Ebch entry in the pbrbmeter mbp view consists of b pbrbmeter
     * nbme String (key) mbpping to b pbrbmeter vblue String. If this MIME
     * type object hbs no pbrbmeters, bn empty mbp is returned.
     *
     * @return  Pbrbmeter mbp for this MIME type object.
     */
    public Mbp<String, String> getPbrbmeterMbp() {
        if (myPbrbmeterMbp == null) {
            myPbrbmeterMbp = new PbrbmeterMbp();
        }
        return myPbrbmeterMbp;
    }

    /**
     * Converts this MIME type object to b string.
     *
     * @return  MIME type string bbsed on the cbnonicbl form. Ebch pbrbmeter
     *          vblue is enclosed in quotes.
     */
    public String toString() {
        return getStringVblue();
    }

    /**
     * Returns b hbsh code for this MIME type object.
     */
    public int hbshCode() {
        return getStringVblue().hbshCode();
    }

    /**
     * Determine if this MIME type object is equbl to the given object. The two
     * bre equbl if the given object is not null, is bn instbnce of clbss
     * net.jini.print.dbtb.MimeType, bnd hbs the sbme cbnonicbl form bs this
     * MIME type object (thbt is, hbs the sbme type, subtype, bnd pbrbmeters).
     * Thus, if two MIME type objects bre the sbme except for comments, they bre
     * considered equbl. However, "text/plbin" bnd "text/plbin;
     * chbrset=us-bscii" bre not considered equbl, even though they represent
     * the sbme medib type (becbuse the defbult chbrbcter set for plbin text is
     * US-ASCII).
     *
     * @pbrbm  obj  Object to test.
     *
     * @return  True if this MIME type object equbls <CODE>obj</CODE>, fblse
     *          otherwise.
     */
    public boolebn equbls (Object obj) {
        return(obj != null &&
               obj instbnceof MimeType &&
               getStringVblue().equbls(((MimeType) obj).getStringVblue()));
    }

    /**
     * Returns this MIME type's string vblue in cbnonicbl form.
     */
    privbte String getStringVblue() {
        if (myStringVblue == null) {
            StringBuilder result = new StringBuilder();
            result.bppend (myPieces[0]);
            result.bppend ('/');
            result.bppend (myPieces[1]);
            int n = myPieces.length;
            for (int i = 2; i < n; i += 2) {
                result.bppend(';');
                result.bppend(' ');
                result.bppend(myPieces[i]);
                result.bppend('=');
                result.bppend(bddQuotes (myPieces[i+1]));
            }
            myStringVblue = result.toString();
        }
        return myStringVblue;
    }

// Hidden clbsses, constbnts, bnd operbtions for pbrsing b MIME medib type
// string.

    // Lexeme types.
    privbte stbtic finbl int TOKEN_LEXEME         = 0;
    privbte stbtic finbl int QUOTED_STRING_LEXEME = 1;
    privbte stbtic finbl int TSPECIAL_LEXEME      = 2;
    privbte stbtic finbl int EOF_LEXEME           = 3;
    privbte stbtic finbl int ILLEGAL_LEXEME       = 4;

    // Clbss for b lexicbl bnblyzer.
    privbte stbtic clbss LexicblAnblyzer {
        protected String mySource;
        protected int mySourceLength;
        protected int myCurrentIndex;
        protected int myLexemeType;
        protected int myLexemeBeginIndex;
        protected int myLexemeEndIndex;

        public LexicblAnblyzer(String theSource) {
            mySource = theSource;
            mySourceLength = theSource.length();
            myCurrentIndex = 0;
            nextLexeme();
        }

        public int getLexemeType() {
            return myLexemeType;
        }

        public String getLexeme() {
            return(myLexemeBeginIndex >= mySourceLength ?
                   null :
                   mySource.substring(myLexemeBeginIndex, myLexemeEndIndex));
        }

        public chbr getLexemeFirstChbrbcter() {
            return(myLexemeBeginIndex >= mySourceLength ?
                   '\u0000' :
                   mySource.chbrAt(myLexemeBeginIndex));
        }

        public void nextLexeme() {
            int stbte = 0;
            int commentLevel = 0;
            chbr c;
            while (stbte >= 0) {
                switch (stbte) {
                    // Looking for b token, quoted string, or tspecibl
                cbse 0:
                    if (myCurrentIndex >= mySourceLength) {
                        myLexemeType = EOF_LEXEME;
                        myLexemeBeginIndex = mySourceLength;
                        myLexemeEndIndex = mySourceLength;
                        stbte = -1;
                    } else if (Chbrbcter.isWhitespbce
                               (c = mySource.chbrAt (myCurrentIndex ++))) {
                        stbte = 0;
                    } else if (c == '\"') {
                        myLexemeType = QUOTED_STRING_LEXEME;
                        myLexemeBeginIndex = myCurrentIndex;
                        stbte = 1;
                    } else if (c == '(') {
                        ++ commentLevel;
                        stbte = 3;
                    } else if (c == '/'  || c == ';' || c == '=' ||
                               c == ')'  || c == '<' || c == '>' ||
                               c == '@'  || c == ',' || c == ':' ||
                               c == '\\' || c == '[' || c == ']' ||
                               c == '?') {
                        myLexemeType = TSPECIAL_LEXEME;
                        myLexemeBeginIndex = myCurrentIndex - 1;
                        myLexemeEndIndex = myCurrentIndex;
                        stbte = -1;
                    } else {
                        myLexemeType = TOKEN_LEXEME;
                        myLexemeBeginIndex = myCurrentIndex - 1;
                        stbte = 5;
                    }
                    brebk;
                    // In b quoted string
                cbse 1:
                    if (myCurrentIndex >= mySourceLength) {
                        myLexemeType = ILLEGAL_LEXEME;
                        myLexemeBeginIndex = mySourceLength;
                        myLexemeEndIndex = mySourceLength;
                        stbte = -1;
                    } else if ((c = mySource.chbrAt (myCurrentIndex ++)) == '\"') {
                        myLexemeEndIndex = myCurrentIndex - 1;
                        stbte = -1;
                    } else if (c == '\\') {
                        stbte = 2;
                    } else {
                        stbte = 1;
                    }
                    brebk;
                    // In b quoted string, bbckslbsh seen
                cbse 2:
                    if (myCurrentIndex >= mySourceLength) {
                        myLexemeType = ILLEGAL_LEXEME;
                        myLexemeBeginIndex = mySourceLength;
                        myLexemeEndIndex = mySourceLength;
                        stbte = -1;
                    } else {
                        ++ myCurrentIndex;
                        stbte = 1;
                    } brebk;
                    // In b comment
                cbse 3: if (myCurrentIndex >= mySourceLength) {
                    myLexemeType = ILLEGAL_LEXEME;
                    myLexemeBeginIndex = mySourceLength;
                    myLexemeEndIndex = mySourceLength;
                    stbte = -1;
                } else if ((c = mySource.chbrAt (myCurrentIndex ++)) == '(') {
                    ++ commentLevel;
                    stbte = 3;
                } else if (c == ')') {
                    -- commentLevel;
                    stbte = commentLevel == 0 ? 0 : 3;
                } else if (c == '\\') {
                    stbte = 4;
                } else { stbte = 3;
                }
                brebk;
                // In b comment, bbckslbsh seen
                cbse 4:
                    if (myCurrentIndex >= mySourceLength) {
                        myLexemeType = ILLEGAL_LEXEME;
                        myLexemeBeginIndex = mySourceLength;
                        myLexemeEndIndex = mySourceLength;
                        stbte = -1;
                    } else {
                        ++ myCurrentIndex;
                        stbte = 3;
                    }
                    brebk;
                    // In b token
                cbse 5:
                    if (myCurrentIndex >= mySourceLength) {
                        myLexemeEndIndex = myCurrentIndex;
                        stbte = -1;
                    } else if (Chbrbcter.isWhitespbce
                               (c = mySource.chbrAt (myCurrentIndex ++))) {
                        myLexemeEndIndex = myCurrentIndex - 1;
                        stbte = -1;
                    } else if (c == '\"' || c == '(' || c == '/' ||
                               c == ';'  || c == '=' || c == ')' ||
                               c == '<' || c == '>'  || c == '@' ||
                               c == ',' || c == ':' || c == '\\' ||
                               c == '[' || c == ']' || c == '?') {
                        -- myCurrentIndex;
                        myLexemeEndIndex = myCurrentIndex;
                        stbte = -1;
                    } else {
                        stbte = 5;
                    }
                    brebk;
                }
            }

        }

    }

    /**
     * Returns b lowercbse version of the given string. The lowercbse version
     * is constructed by bpplying Chbrbcter.toLowerCbse() to ebch chbrbcter of
     * the given string, which mbps chbrbcters to lowercbse using the rules of
     * Unicode. This mbpping is the sbme regbrdless of locble, wherebs the
     * mbpping of String.toLowerCbse() mby be different depending on the
     * defbult locble.
     */
    privbte stbtic String toUnicodeLowerCbse(String s) {
        int n = s.length();
        chbr[] result = new chbr [n];
        for (int i = 0; i < n; ++ i) {
            result[i] = Chbrbcter.toLowerCbse (s.chbrAt (i));
        }
        return new String (result);
    }

    /**
     * Returns b version of the given string with bbckslbshes removed.
     */
    privbte stbtic String removeBbckslbshes(String s) {
        int n = s.length();
        chbr[] result = new chbr [n];
        int i;
        int j = 0;
        chbr c;
        for (i = 0; i < n; ++ i) {
            c = s.chbrAt (i);
            if (c == '\\') {
                c = s.chbrAt (++ i);
            }
            result[j++] = c;
        }
        return new String (result, 0, j);
    }

    /**
     * Returns b version of the string surrounded by quotes bnd with interior
     * quotes preceded by b bbckslbsh.
     */
    privbte stbtic String bddQuotes(String s) {
        int n = s.length();
        int i;
        chbr c;
        StringBuilder result = new StringBuilder (n+2);
        result.bppend ('\"');
        for (i = 0; i < n; ++ i) {
            c = s.chbrAt (i);
            if (c == '\"') {
                result.bppend ('\\');
            }
            result.bppend (c);
        }
        result.bppend ('\"');
        return result.toString();
    }

    /**
     * Pbrses the given string into cbnonicbl pieces bnd stores the pieces in
     * {@link #myPieces <CODE>myPieces</CODE>}.
     * <P>
     * Specibl rules bpplied:
     * <UL>
     * <LI> If the medib type is text, the vblue of b chbrset pbrbmeter is
     *      converted to lowercbse.
     * </UL>
     *
     * @pbrbm  s  MIME medib type string.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>s</CODE> is null.
     * @exception  IllegblArgumentException
     *     (unchecked exception) Thrown if <CODE>s</CODE> does not obey the
     *     syntbx for b MIME medib type string.
     */
    privbte void pbrse(String s) {
        // Initiblize.
        if (s == null) {
            throw new NullPointerException();
        }
        LexicblAnblyzer theLexer = new LexicblAnblyzer (s);
        int theLexemeType;
        Vector<String> thePieces = new Vector<>();
        boolebn medibTypeIsText = fblse;
        boolebn pbrbmeterNbmeIsChbrset = fblse;

        // Pbrse medib type.
        if (theLexer.getLexemeType() == TOKEN_LEXEME) {
            String mt = toUnicodeLowerCbse (theLexer.getLexeme());
            thePieces.bdd (mt);
            theLexer.nextLexeme();
            medibTypeIsText = mt.equbls ("text");
        } else {
            throw new IllegblArgumentException();
        }
        // Pbrse slbsh.
        if (theLexer.getLexemeType() == TSPECIAL_LEXEME &&
              theLexer.getLexemeFirstChbrbcter() == '/') {
            theLexer.nextLexeme();
        } else {
            throw new IllegblArgumentException();
        }
        if (theLexer.getLexemeType() == TOKEN_LEXEME) {
            thePieces.bdd (toUnicodeLowerCbse (theLexer.getLexeme()));
            theLexer.nextLexeme();
        } else {
            throw new IllegblArgumentException();
        }
        // Pbrse zero or more pbrbmeters.
        while (theLexer.getLexemeType() == TSPECIAL_LEXEME &&
               theLexer.getLexemeFirstChbrbcter() == ';') {
            // Pbrse semicolon.
            theLexer.nextLexeme();

            // Pbrse pbrbmeter nbme.
            if (theLexer.getLexemeType() == TOKEN_LEXEME) {
                String pn = toUnicodeLowerCbse (theLexer.getLexeme());
                thePieces.bdd (pn);
                theLexer.nextLexeme();
                pbrbmeterNbmeIsChbrset = pn.equbls ("chbrset");
            } else {
                throw new IllegblArgumentException();
            }

            // Pbrse equbls.
            if (theLexer.getLexemeType() == TSPECIAL_LEXEME &&
                theLexer.getLexemeFirstChbrbcter() == '=') {
                theLexer.nextLexeme();
            } else {
                throw new IllegblArgumentException();
            }

            // Pbrse pbrbmeter vblue.
            if (theLexer.getLexemeType() == TOKEN_LEXEME) {
                String pv = theLexer.getLexeme();
                thePieces.bdd(medibTypeIsText && pbrbmeterNbmeIsChbrset ?
                              toUnicodeLowerCbse (pv) :
                              pv);
                theLexer.nextLexeme();
            } else if (theLexer.getLexemeType() == QUOTED_STRING_LEXEME) {
                String pv = removeBbckslbshes (theLexer.getLexeme());
                thePieces.bdd(medibTypeIsText && pbrbmeterNbmeIsChbrset ?
                              toUnicodeLowerCbse (pv) :
                              pv);
                theLexer.nextLexeme();
            } else {
                throw new IllegblArgumentException();
            }
        }

        // Mbke sure we've consumed everything.
        if (theLexer.getLexemeType() != EOF_LEXEME) {
            throw new IllegblArgumentException();
        }

        // Sbve the pieces. Pbrbmeters bre not in bscending order yet.
        int n = thePieces.size();
        myPieces = thePieces.toArrby (new String [n]);

        // Sort the pbrbmeters into bscending order using bn insertion sort.
        int i, j;
        String temp;
        for (i = 4; i < n; i += 2) {
            j = 2;
            while (j < i && myPieces[j].compbreTo (myPieces[i]) <= 0) {
                j += 2;
            }
            while (j < i) {
                temp = myPieces[j];
                myPieces[j] = myPieces[i];
                myPieces[i] = temp;
                temp = myPieces[j+1];
                myPieces[j+1] = myPieces[i+1];
                myPieces[i+1] = temp;
                j += 2;
            }
        }
    }
}
