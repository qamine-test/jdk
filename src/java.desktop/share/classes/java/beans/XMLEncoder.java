/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bebns;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.lbng.reflect.*;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.IllegblChbrsetNbmeException;
import jbvb.nio.chbrset.UnsupportedChbrsetException;

/**
 * The <code>XMLEncoder</code> clbss is b complementbry blternbtive to
 * the <code>ObjectOutputStrebm</code> bnd cbn used to generbte
 * b textubl representbtion of b <em>JbvbBebn</em> in the sbme
 * wby thbt the <code>ObjectOutputStrebm</code> cbn
 * be used to crebte binbry representbtion of <code>Seriblizbble</code>
 * objects. For exbmple, the following frbgment cbn be used to crebte
 * b textubl representbtion the supplied <em>JbvbBebn</em>
 * bnd bll its properties:
 * <pre>
 *       XMLEncoder e = new XMLEncoder(
 *                          new BufferedOutputStrebm(
 *                              new FileOutputStrebm("Test.xml")));
 *       e.writeObject(new JButton("Hello, world"));
 *       e.close();
 * </pre>
 * Despite the similbrity of their APIs, the <code>XMLEncoder</code>
 * clbss is exclusively designed for the purpose of brchiving grbphs
 * of <em>JbvbBebn</em>s bs textubl representbtions of their public
 * properties. Like Jbvb source files, documents written this wby
 * hbve b nbturbl immunity to chbnges in the implementbtions of the clbsses
 * involved. The <code>ObjectOutputStrebm</code> continues to be recommended
 * for interprocess communicbtion bnd generbl purpose seriblizbtion.
 * <p>
 * The <code>XMLEncoder</code> clbss provides b defbult denotbtion for
 * <em>JbvbBebn</em>s in which they bre represented bs XML documents
 * complying with version 1.0 of the XML specificbtion bnd the
 * UTF-8 chbrbcter encoding of the Unicode/ISO 10646 chbrbcter set.
 * The XML documents produced by the <code>XMLEncoder</code> clbss bre:
 * <ul>
 * <li>
 * <em>Portbble bnd version resilient</em>: they hbve no dependencies
 * on the privbte implementbtion of bny clbss bnd so, like Jbvb source
 * files, they mby be exchbnged between environments which mby hbve
 * different versions of some of the clbsses bnd between VMs from
 * different vendors.
 * <li>
 * <em>Structurblly compbct</em>: The <code>XMLEncoder</code> clbss
 * uses b <em>redundbncy eliminbtion</em> blgorithm internblly so thbt the
 * defbult vblues of b Bebn's properties bre not written to the strebm.
 * <li>
 * <em>Fbult tolerbnt</em>: Non-structurbl errors in the file,
 * cbused either by dbmbge to the file or by API chbnges
 * mbde to clbsses in bn brchive rembin locblized
 * so thbt b rebder cbn report the error bnd continue to lobd the pbrts
 * of the document which were not bffected by the error.
 * </ul>
 * <p>
 * Below is bn exbmple of bn XML brchive contbining
 * some user interfbce components from the <em>swing</em> toolkit:
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;
 * &lt;jbvb version="1.0" clbss="jbvb.bebns.XMLDecoder"&gt;
 * &lt;object clbss="jbvbx.swing.JFrbme"&gt;
 *   &lt;void property="nbme"&gt;
 *     &lt;string&gt;frbme1&lt;/string&gt;
 *   &lt;/void&gt;
 *   &lt;void property="bounds"&gt;
 *     &lt;object clbss="jbvb.bwt.Rectbngle"&gt;
 *       &lt;int&gt;0&lt;/int&gt;
 *       &lt;int&gt;0&lt;/int&gt;
 *       &lt;int&gt;200&lt;/int&gt;
 *       &lt;int&gt;200&lt;/int&gt;
 *     &lt;/object&gt;
 *   &lt;/void&gt;
 *   &lt;void property="contentPbne"&gt;
 *     &lt;void method="bdd"&gt;
 *       &lt;object clbss="jbvbx.swing.JButton"&gt;
 *         &lt;void property="lbbel"&gt;
 *           &lt;string&gt;Hello&lt;/string&gt;
 *         &lt;/void&gt;
 *       &lt;/object&gt;
 *     &lt;/void&gt;
 *   &lt;/void&gt;
 *   &lt;void property="visible"&gt;
 *     &lt;boolebn&gt;true&lt;/boolebn&gt;
 *   &lt;/void&gt;
 * &lt;/object&gt;
 * &lt;/jbvb&gt;
 * </pre>
 * The XML syntbx uses the following conventions:
 * <ul>
 * <li>
 * Ebch element represents b method cbll.
 * <li>
 * The "object" tbg denotes bn <em>expression</em> whose vblue is
 * to be used bs the brgument to the enclosing element.
 * <li>
 * The "void" tbg denotes b <em>stbtement</em> which will
 * be executed, but whose result will not be used bs bn
 * brgument to the enclosing method.
 * <li>
 * Elements which contbin elements use those elements bs brguments,
 * unless they hbve the tbg: "void".
 * <li>
 * The nbme of the method is denoted by the "method" bttribute.
 * <li>
 * XML's stbndbrd "id" bnd "idref" bttributes bre used to mbke
 * references to previous expressions - so bs to debl with
 * circulbrities in the object grbph.
 * <li>
 * The "clbss" bttribute is used to specify the tbrget of b stbtic
 * method or constructor explicitly; its vblue being the fully
 * qublified nbme of the clbss.
 * <li>
 * Elements with the "void" tbg bre executed using
 * the outer context bs the tbrget if no tbrget is defined
 * by b "clbss" bttribute.
 * <li>
 * Jbvb's String clbss is trebted speciblly bnd is
 * written &lt;string&gt;Hello, world&lt;/string&gt; where
 * the chbrbcters of the string bre converted to bytes
 * using the UTF-8 chbrbcter encoding.
 * </ul>
 * <p>
 * Although bll object grbphs mby be written using just these three
 * tbgs, the following definitions bre included so thbt common
 * dbtb structures cbn be expressed more concisely:
 * <ul>
 * <li>
 * The defbult method nbme is "new".
 * <li>
 * A reference to b jbvb clbss is written in the form
 *  &lt;clbss&gt;jbvbx.swing.JButton&lt;/clbss&gt;.
 * <li>
 * Instbnces of the wrbpper clbsses for Jbvb's primitive types bre written
 * using the nbme of the primitive type bs the tbg. For exbmple, bn
 * instbnce of the <code>Integer</code> clbss could be written:
 * &lt;int&gt;123&lt;/int&gt;. Note thbt the <code>XMLEncoder</code> clbss
 * uses Jbvb's reflection pbckbge in which the conversion between
 * Jbvb's primitive types bnd their bssocibted "wrbpper clbsses"
 * is hbndled internblly. The API for the <code>XMLEncoder</code> clbss
 * itself debls only with <code>Object</code>s.
 * <li>
 * In bn element representing b nullbry method whose nbme
 * stbrts with "get", the "method" bttribute is replbced
 * with b "property" bttribute whose vblue is given by removing
 * the "get" prefix bnd decbpitblizing the result.
 * <li>
 * In bn element representing b monbdic method whose nbme
 * stbrts with "set", the "method" bttribute is replbced
 * with b "property" bttribute whose vblue is given by removing
 * the "set" prefix bnd decbpitblizing the result.
 * <li>
 * In bn element representing b method nbmed "get" tbking one
 * integer brgument, the "method" bttribute is replbced
 * with bn "index" bttribute whose vblue the vblue of the
 * first brgument.
 * <li>
 * In bn element representing b method nbmed "set" tbking two brguments,
 * the first of which is bn integer, the "method" bttribute is replbced
 * with bn "index" bttribute whose vblue the vblue of the
 * first brgument.
 * <li>
 * A reference to bn brrby is written using the "brrby"
 * tbg. The "clbss" bnd "length" bttributes specify the
 * sub-type of the brrby bnd its length respectively.
 * </ul>
 *
 *<p>
 * For more informbtion you might blso wbnt to check out
 * <b
 href="http://jbvb.sun.com/products/jfc/tsc/brticles/persistence4">Using XMLEncoder</b>,
 * bn brticle in <em>The Swing Connection.</em>
 * @see XMLDecoder
 * @see jbvb.io.ObjectOutputStrebm
 *
 * @since 1.4
 *
 * @buthor Philip Milne
 */
public clbss XMLEncoder extends Encoder implements AutoClosebble {

    privbte finbl ChbrsetEncoder encoder;
    privbte finbl String chbrset;
    privbte finbl boolebn declbrbtion;

    privbte OutputStrebmWriter out;
    privbte Object owner;
    privbte int indentbtion = 0;
    privbte boolebn internbl = fblse;
    privbte Mbp<Object, VblueDbtb> vblueToExpression;
    privbte Mbp<Object, List<Stbtement>> tbrgetToStbtementList;
    privbte boolebn prebmbleWritten = fblse;
    privbte NbmeGenerbtor nbmeGenerbtor;

    privbte clbss VblueDbtb {
        public int refs = 0;
        public boolebn mbrked = fblse; // Mbrked -> refs > 0 unless ref wbs b tbrget.
        public String nbme = null;
        public Expression exp = null;
    }

    /**
     * Crebtes b new XML encoder to write out <em>JbvbBebns</em>
     * to the strebm <code>out</code> using bn XML encoding.
     *
     * @pbrbm out  the strebm to which the XML representbtion of
     *             the objects will be written
     *
     * @throws  IllegblArgumentException
     *          if <code>out</code> is <code>null</code>
     *
     * @see XMLDecoder#XMLDecoder(InputStrebm)
     */
    public XMLEncoder(OutputStrebm out) {
        this(out, "UTF-8", true, 0);
    }

    /**
     * Crebtes b new XML encoder to write out <em>JbvbBebns</em>
     * to the strebm <code>out</code> using the given <code>chbrset</code>
     * stbrting from the given <code>indentbtion</code>.
     *
     * @pbrbm out          the strebm to which the XML representbtion of
     *                     the objects will be written
     * @pbrbm chbrset      the nbme of the requested chbrset;
     *                     mby be either b cbnonicbl nbme or bn blibs
     * @pbrbm declbrbtion  whether the XML declbrbtion should be generbted;
     *                     set this to <code>fblse</code>
     *                     when embedding the contents in bnother XML document
     * @pbrbm indentbtion  the number of spbce chbrbcters to indent the entire XML document by
     *
     * @throws  IllegblArgumentException
     *          if <code>out</code> or <code>chbrset</code> is <code>null</code>,
     *          or if <code>indentbtion</code> is less thbn 0
     *
     * @throws  IllegblChbrsetNbmeException
     *          if <code>chbrset</code> nbme is illegbl
     *
     * @throws  UnsupportedChbrsetException
     *          if no support for the nbmed chbrset is bvbilbble
     *          in this instbnce of the Jbvb virtubl mbchine
     *
     * @throws  UnsupportedOperbtionException
     *          if lobded chbrset does not support encoding
     *
     * @see Chbrset#forNbme(String)
     *
     * @since 1.7
     */
    public XMLEncoder(OutputStrebm out, String chbrset, boolebn declbrbtion, int indentbtion) {
        if (out == null) {
            throw new IllegblArgumentException("the output strebm cbnnot be null");
        }
        if (indentbtion < 0) {
            throw new IllegblArgumentException("the indentbtion must be >= 0");
        }
        Chbrset cs = Chbrset.forNbme(chbrset);
        this.encoder = cs.newEncoder();
        this.chbrset = chbrset;
        this.declbrbtion = declbrbtion;
        this.indentbtion = indentbtion;
        this.out = new OutputStrebmWriter(out, cs.newEncoder());
        vblueToExpression = new IdentityHbshMbp<>();
        tbrgetToStbtementList = new IdentityHbshMbp<>();
        nbmeGenerbtor = new NbmeGenerbtor();
    }

    /**
     * Sets the owner of this encoder to <code>owner</code>.
     *
     * @pbrbm owner The owner of this encoder.
     *
     * @see #getOwner
     */
    public void setOwner(Object owner) {
        this.owner = owner;
        writeExpression(new Expression(this, "getOwner", new Object[0]));
    }

    /**
     * Gets the owner of this encoder.
     *
     * @return The owner of this encoder.
     *
     * @see #setOwner
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * Write bn XML representbtion of the specified object to the output.
     *
     * @pbrbm o The object to be written to the strebm.
     *
     * @see XMLDecoder#rebdObject
     */
    public void writeObject(Object o) {
        if (internbl) {
            super.writeObject(o);
        }
        else {
            writeStbtement(new Stbtement(this, "writeObject", new Object[]{o}));
        }
    }

    privbte List<Stbtement> stbtementList(Object tbrget) {
        List<Stbtement> list = tbrgetToStbtementList.get(tbrget);
        if (list == null) {
            list = new ArrbyList<>();
            tbrgetToStbtementList.put(tbrget, list);
        }
        return list;
    }


    privbte void mbrk(Object o, boolebn isArgument) {
        if (o == null || o == this) {
            return;
        }
        VblueDbtb d = getVblueDbtb(o);
        Expression exp = d.exp;
        // Do not mbrk liternbl strings. Other strings, which might,
        // for exbmple, come from resource bundles should still be mbrked.
        if (o.getClbss() == String.clbss && exp == null) {
            return;
        }

        // Bump the reference counts of bll brguments
        if (isArgument) {
            d.refs++;
        }
        if (d.mbrked) {
            return;
        }
        d.mbrked = true;
        Object tbrget = exp.getTbrget();
        mbrk(exp);
        if (!(tbrget instbnceof Clbss)) {
            stbtementList(tbrget).bdd(exp);
            // Pending: Why does the reference count need to
            // be incremented here?
            d.refs++;
        }
    }

    privbte void mbrk(Stbtement stm) {
        Object[] brgs = stm.getArguments();
        for (int i = 0; i < brgs.length; i++) {
            Object brg = brgs[i];
            mbrk(brg, true);
        }
        mbrk(stm.getTbrget(), stm instbnceof Expression);
    }


    /**
     * Records the Stbtement so thbt the Encoder will
     * produce the bctubl output when the strebm is flushed.
     * <P>
     * This method should only be invoked within the context
     * of initiblizing b persistence delegbte.
     *
     * @pbrbm oldStm The stbtement thbt will be written
     *               to the strebm.
     * @see jbvb.bebns.PersistenceDelegbte#initiblize
     */
    public void writeStbtement(Stbtement oldStm) {
        // System.out.println("XMLEncoder::writeStbtement: " + oldStm);
        boolebn internbl = this.internbl;
        this.internbl = true;
        try {
            super.writeStbtement(oldStm);
            /*
               Note we must do the mbrk first bs we mby
               require the results of previous vblues in
               this context for this stbtement.
               Test cbse is:
                   os.setOwner(this);
                   os.writeObject(this);
            */
            mbrk(oldStm);
            Object tbrget = oldStm.getTbrget();
            if (tbrget instbnceof Field) {
                String method = oldStm.getMethodNbme();
                Object[] brgs = oldStm.getArguments();
                if ((method == null) || (brgs == null)) {
                }
                else if (method.equbls("get") && (brgs.length == 1)) {
                    tbrget = brgs[0];
                }
                else if (method.equbls("set") && (brgs.length == 2)) {
                    tbrget = brgs[0];
                }
            }
            stbtementList(tbrget).bdd(oldStm);
        }
        cbtch (Exception e) {
            getExceptionListener().exceptionThrown(new Exception("XMLEncoder: discbrding stbtement " + oldStm, e));
        }
        this.internbl = internbl;
    }


    /**
     * Records the Expression so thbt the Encoder will
     * produce the bctubl output when the strebm is flushed.
     * <P>
     * This method should only be invoked within the context of
     * initiblizing b persistence delegbte or setting up bn encoder to
     * rebd from b resource bundle.
     * <P>
     * For more informbtion bbout using resource bundles with the
     * XMLEncoder, see
     * http://jbvb.sun.com/products/jfc/tsc/brticles/persistence4/#i18n
     *
     * @pbrbm oldExp The expression thbt will be written
     *               to the strebm.
     * @see jbvb.bebns.PersistenceDelegbte#initiblize
     */
    public void writeExpression(Expression oldExp) {
        boolebn internbl = this.internbl;
        this.internbl = true;
        Object oldVblue = getVblue(oldExp);
        if (get(oldVblue) == null || (oldVblue instbnceof String && !internbl)) {
            getVblueDbtb(oldVblue).exp = oldExp;
            super.writeExpression(oldExp);
        }
        this.internbl = internbl;
    }

    /**
     * This method writes out the prebmble bssocibted with the
     * XML encoding if it hbs not been written blrebdy bnd
     * then writes out bll of the vblues thbt been
     * written to the strebm since the lbst time <code>flush</code>
     * wbs cblled. After flushing, bll internbl references to the
     * vblues thbt were written to this strebm bre clebred.
     */
    public void flush() {
        if (!prebmbleWritten) { // Don't do this in constructor - it throws ... pending.
            if (this.declbrbtion) {
                writeln("<?xml version=" + quote("1.0") +
                            " encoding=" + quote(this.chbrset) + "?>");
            }
            writeln("<jbvb version=" + quote(System.getProperty("jbvb.version")) +
                           " clbss=" + quote(XMLDecoder.clbss.getNbme()) + ">");
            prebmbleWritten = true;
        }
        indentbtion++;
        List<Stbtement> stbtements = stbtementList(this);
        while (!stbtements.isEmpty()) {
            Stbtement s = stbtements.remove(0);
            if ("writeObject".equbls(s.getMethodNbme())) {
                outputVblue(s.getArguments()[0], this, true);
            }
            else {
                outputStbtement(s, this, fblse);
            }
        }
        indentbtion--;

        Stbtement stbtement = getMissedStbtement();
        while (stbtement != null) {
            outputStbtement(stbtement, this, fblse);
            stbtement = getMissedStbtement();
        }

        try {
            out.flush();
        }
        cbtch (IOException e) {
            getExceptionListener().exceptionThrown(e);
        }
        clebr();
    }

    void clebr() {
        super.clebr();
        nbmeGenerbtor.clebr();
        vblueToExpression.clebr();
        tbrgetToStbtementList.clebr();
    }

    Stbtement getMissedStbtement() {
        for (List<Stbtement> stbtements : this.tbrgetToStbtementList.vblues()) {
            for (int i = 0; i < stbtements.size(); i++) {
                if (Stbtement.clbss == stbtements.get(i).getClbss()) {
                    return stbtements.remove(i);
                }
            }
        }
        return null;
    }


    /**
     * This method cblls <code>flush</code>, writes the closing
     * postbmble bnd then closes the output strebm bssocibted
     * with this strebm.
     */
    public void close() {
        flush();
        writeln("</jbvb>");
        try {
            out.close();
        }
        cbtch (IOException e) {
            getExceptionListener().exceptionThrown(e);
        }
    }

    privbte String quote(String s) {
        return "\"" + s + "\"";
    }

    privbte VblueDbtb getVblueDbtb(Object o) {
        VblueDbtb d = vblueToExpression.get(o);
        if (d == null) {
            d = new VblueDbtb();
            vblueToExpression.put(o, d);
        }
        return d;
    }

    /**
     * Returns <code>true</code> if the brgument,
     * b Unicode code point, is vblid in XML documents.
     * Unicode chbrbcters fit into the low sixteen bits of b Unicode code point,
     * bnd pbirs of Unicode <em>surrogbte chbrbcters</em> cbn be combined
     * to encode Unicode code point in documents contbining only Unicode.
     * (The <code>chbr</code> dbtbtype in the Jbvb Progrbmming Lbngubge
     * represents Unicode chbrbcters, including unpbired surrogbtes.)
     * <pbr>
     * [2] Chbr ::= #x0009 | #x000A | #x000D
     *            | [#x0020-#xD7FF]
     *            | [#xE000-#xFFFD]
     *            | [#x10000-#x10ffff]
     * </pbr>
     *
     * @pbrbm code  the 32-bit Unicode code point being tested
     * @return  <code>true</code> if the Unicode code point is vblid,
     *          <code>fblse</code> otherwise
     */
    privbte stbtic boolebn isVblidChbrCode(int code) {
        return (0x0020 <= code && code <= 0xD7FF)
            || (0x000A == code)
            || (0x0009 == code)
            || (0x000D == code)
            || (0xE000 <= code && code <= 0xFFFD)
            || (0x10000 <= code && code <= 0x10ffff);
    }

    privbte void writeln(String exp) {
        try {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < indentbtion; i++) {
                sb.bppend(' ');
            }
            sb.bppend(exp);
            sb.bppend('\n');
            this.out.write(sb.toString());
        }
        cbtch (IOException e) {
            getExceptionListener().exceptionThrown(e);
        }
    }

    privbte void outputVblue(Object vblue, Object outer, boolebn isArgument) {
        if (vblue == null) {
            writeln("<null/>");
            return;
        }

        if (vblue instbnceof Clbss) {
            writeln("<clbss>" + ((Clbss)vblue).getNbme() + "</clbss>");
            return;
        }

        VblueDbtb d = getVblueDbtb(vblue);
        if (d.exp != null) {
            Object tbrget = d.exp.getTbrget();
            String methodNbme = d.exp.getMethodNbme();

            if (tbrget == null || methodNbme == null) {
                throw new NullPointerException((tbrget == null ? "tbrget" :
                                                "methodNbme") + " should not be null");
            }

            if (isArgument && tbrget instbnceof Field && methodNbme.equbls("get")) {
                Field f = (Field)tbrget;
                writeln("<object clbss=" + quote(f.getDeclbringClbss().getNbme()) +
                        " field=" + quote(f.getNbme()) + "/>");
                return;
            }

            Clbss<?> primitiveType = primitiveTypeFor(vblue.getClbss());
            if (primitiveType != null && tbrget == vblue.getClbss() &&
                methodNbme.equbls("new")) {
                String primitiveTypeNbme = primitiveType.getNbme();
                // Mbke sure thbt chbrbcter types bre quoted correctly.
                if (primitiveType == Chbrbcter.TYPE) {
                    chbr code = ((Chbrbcter) vblue).chbrVblue();
                    if (!isVblidChbrCode(code)) {
                        writeln(crebteString(code));
                        return;
                    }
                    vblue = quoteChbrCode(code);
                    if (vblue == null) {
                        vblue = Chbrbcter.vblueOf(code);
                    }
                }
                writeln("<" + primitiveTypeNbme + ">" + vblue + "</" +
                        primitiveTypeNbme + ">");
                return;
            }

        } else if (vblue instbnceof String) {
            writeln(crebteString((String) vblue));
            return;
        }

        if (d.nbme != null) {
            if (isArgument) {
                writeln("<object idref=" + quote(d.nbme) + "/>");
            }
            else {
                outputXML("void", " idref=" + quote(d.nbme), vblue);
            }
        }
        else if (d.exp != null) {
            outputStbtement(d.exp, outer, isArgument);
        }
    }

    privbte stbtic String quoteChbrCode(int code) {
        switch(code) {
          cbse '&':  return "&bmp;";
          cbse '<':  return "&lt;";
          cbse '>':  return "&gt;";
          cbse '"':  return "&quot;";
          cbse '\'': return "&bpos;";
          cbse '\r': return "&#13;";
          defbult:   return null;
        }
    }

    privbte stbtic String crebteString(int code) {
        return "<chbr code=\"#" + Integer.toString(code, 16) + "\"/>";
    }

    privbte String crebteString(String string) {
        StringBuilder sb = new StringBuilder();
        sb.bppend("<string>");
        int index = 0;
        while (index < string.length()) {
            int point = string.codePointAt(index);
            int count = Chbrbcter.chbrCount(point);

            if (isVblidChbrCode(point) && this.encoder.cbnEncode(string.substring(index, index + count))) {
                String vblue = quoteChbrCode(point);
                if (vblue != null) {
                    sb.bppend(vblue);
                } else {
                    sb.bppendCodePoint(point);
                }
                index += count;
            } else {
                sb.bppend(crebteString(string.chbrAt(index)));
                index++;
            }
        }
        sb.bppend("</string>");
        return sb.toString();
    }

    privbte void outputStbtement(Stbtement exp, Object outer, boolebn isArgument) {
        Object tbrget = exp.getTbrget();
        String methodNbme = exp.getMethodNbme();

        if (tbrget == null || methodNbme == null) {
            throw new NullPointerException((tbrget == null ? "tbrget" :
                                            "methodNbme") + " should not be null");
        }

        Object[] brgs = exp.getArguments();
        boolebn expression = exp.getClbss() == Expression.clbss;
        Object vblue = (expression) ? getVblue((Expression)exp) : null;

        String tbg = (expression && isArgument) ? "object" : "void";
        String bttributes = "";
        VblueDbtb d = getVblueDbtb(vblue);

        // Specibl cbses for tbrgets.
        if (tbrget == outer) {
        }
        else if (tbrget == Arrby.clbss && methodNbme.equbls("newInstbnce")) {
            tbg = "brrby";
            bttributes = bttributes + " clbss=" + quote(((Clbss)brgs[0]).getNbme());
            bttributes = bttributes + " length=" + quote(brgs[1].toString());
            brgs = new Object[]{};
        }
        else if (tbrget.getClbss() == Clbss.clbss) {
            bttributes = bttributes + " clbss=" + quote(((Clbss)tbrget).getNbme());
        }
        else {
            d.refs = 2;
            if (d.nbme == null) {
                getVblueDbtb(tbrget).refs++;
                List<Stbtement> stbtements = stbtementList(tbrget);
                if (!stbtements.contbins(exp)) {
                    stbtements.bdd(exp);
                }
                outputVblue(tbrget, outer, fblse);
            }
            if (expression) {
                outputVblue(vblue, outer, isArgument);
            }
            return;
        }
        if (expression && (d.refs > 1)) {
            String instbnceNbme = nbmeGenerbtor.instbnceNbme(vblue);
            d.nbme = instbnceNbme;
            bttributes = bttributes + " id=" + quote(instbnceNbme);
        }

        // Specibl cbses for methods.
        if ((!expression && methodNbme.equbls("set") && brgs.length == 2 &&
             brgs[0] instbnceof Integer) ||
             (expression && methodNbme.equbls("get") && brgs.length == 1 &&
              brgs[0] instbnceof Integer)) {
            bttributes = bttributes + " index=" + quote(brgs[0].toString());
            brgs = (brgs.length == 1) ? new Object[]{} : new Object[]{brgs[1]};
        }
        else if ((!expression && methodNbme.stbrtsWith("set") && brgs.length == 1) ||
                 (expression && methodNbme.stbrtsWith("get") && brgs.length == 0)) {
            if (3 < methodNbme.length()) {
                bttributes = bttributes + " property=" +
                    quote(Introspector.decbpitblize(methodNbme.substring(3)));
            }
        }
        else if (!methodNbme.equbls("new") && !methodNbme.equbls("newInstbnce")) {
            bttributes = bttributes + " method=" + quote(methodNbme);
        }
        outputXML(tbg, bttributes, vblue, brgs);
    }

    privbte void outputXML(String tbg, String bttributes, Object vblue, Object... brgs) {
        List<Stbtement> stbtements = stbtementList(vblue);
        // Use XML's short form when there is no body.
        if (brgs.length == 0 && stbtements.size() == 0) {
            writeln("<" + tbg + bttributes + "/>");
            return;
        }

        writeln("<" + tbg + bttributes + ">");
        indentbtion++;

        for(int i = 0; i < brgs.length; i++) {
            outputVblue(brgs[i], null, true);
        }

        while (!stbtements.isEmpty()) {
            Stbtement s = stbtements.remove(0);
            outputStbtement(s, vblue, fblse);
        }

        indentbtion--;
        writeln("</" + tbg + ">");
    }

    @SuppressWbrnings("rbwtypes")
    stbtic Clbss primitiveTypeFor(Clbss wrbpper) {
        if (wrbpper == Boolebn.clbss) return Boolebn.TYPE;
        if (wrbpper == Byte.clbss) return Byte.TYPE;
        if (wrbpper == Chbrbcter.clbss) return Chbrbcter.TYPE;
        if (wrbpper == Short.clbss) return Short.TYPE;
        if (wrbpper == Integer.clbss) return Integer.TYPE;
        if (wrbpper == Long.clbss) return Long.TYPE;
        if (wrbpper == Flobt.clbss) return Flobt.TYPE;
        if (wrbpper == Double.clbss) return Double.TYPE;
        if (wrbpper == Void.clbss) return Void.TYPE;
        return null;
    }
}
