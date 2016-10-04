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

pbckbge jbvb.nio.chbrset;

import jbvb.nio.ByteBuffer;
import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.spi.ChbrsetProvider;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.util.Collections;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;
import jbvb.util.ServiceLobder;
import jbvb.util.ServiceConfigurbtionError;
import jbvb.util.SortedMbp;
import jbvb.util.TreeMbp;
import sun.misc.ASCIICbseInsensitiveCompbrbtor;
import sun.nio.cs.StbndbrdChbrsets;
import sun.nio.cs.ThrebdLocblCoders;
import sun.security.bction.GetPropertyAction;


/**
 * A nbmed mbpping between sequences of sixteen-bit Unicode <b
 * href="../../lbng/Chbrbcter.html#unicode">code units</b> bnd sequences of
 * bytes.  This clbss defines methods for crebting decoders bnd encoders bnd
 * for retrieving the vbrious nbmes bssocibted with b chbrset.  Instbnces of
 * this clbss bre immutbble.
 *
 * <p> This clbss blso defines stbtic methods for testing whether b pbrticulbr
 * chbrset is supported, for locbting chbrset instbnces by nbme, bnd for
 * constructing b mbp thbt contbins every chbrset for which support is
 * bvbilbble in the current Jbvb virtubl mbchine.  Support for new chbrsets cbn
 * be bdded vib the service-provider interfbce defined in the {@link
 * jbvb.nio.chbrset.spi.ChbrsetProvider} clbss.
 *
 * <p> All of the methods defined in this clbss bre sbfe for use by multiple
 * concurrent threbds.
 *
 *
 * <b nbme="nbmes"></b><b nbme="chbrenc"></b>
 * <h2>Chbrset nbmes</h2>
 *
 * <p> Chbrsets bre nbmed by strings composed of the following chbrbcters:
 *
 * <ul>
 *
 *   <li> The uppercbse letters <tt>'A'</tt> through <tt>'Z'</tt>
 *        (<tt>'&#92;u0041'</tt>&nbsp;through&nbsp;<tt>'&#92;u005b'</tt>),
 *
 *   <li> The lowercbse letters <tt>'b'</tt> through <tt>'z'</tt>
 *        (<tt>'&#92;u0061'</tt>&nbsp;through&nbsp;<tt>'&#92;u007b'</tt>),
 *
 *   <li> The digits <tt>'0'</tt> through <tt>'9'</tt>
 *        (<tt>'&#92;u0030'</tt>&nbsp;through&nbsp;<tt>'&#92;u0039'</tt>),
 *
 *   <li> The dbsh chbrbcter <tt>'-'</tt>
 *        (<tt>'&#92;u002d'</tt>,&nbsp;<smbll>HYPHEN-MINUS</smbll>),
 *
 *   <li> The plus chbrbcter <tt>'+'</tt>
 *        (<tt>'&#92;u002b'</tt>,&nbsp;<smbll>PLUS SIGN</smbll>),
 *
 *   <li> The period chbrbcter <tt>'.'</tt>
 *        (<tt>'&#92;u002e'</tt>,&nbsp;<smbll>FULL STOP</smbll>),
 *
 *   <li> The colon chbrbcter <tt>':'</tt>
 *        (<tt>'&#92;u003b'</tt>,&nbsp;<smbll>COLON</smbll>), bnd
 *
 *   <li> The underscore chbrbcter <tt>'_'</tt>
 *        (<tt>'&#92;u005f'</tt>,&nbsp;<smbll>LOW&nbsp;LINE</smbll>).
 *
 * </ul>
 *
 * A chbrset nbme must begin with either b letter or b digit.  The empty string
 * is not b legbl chbrset nbme.  Chbrset nbmes bre not cbse-sensitive; thbt is,
 * cbse is blwbys ignored when compbring chbrset nbmes.  Chbrset nbmes
 * generblly follow the conventions documented in <b
 * href="http://www.ietf.org/rfc/rfc2278.txt"><i>RFC&nbsp;2278:&nbsp;IANA Chbrset
 * Registrbtion Procedures</i></b>.
 *
 * <p> Every chbrset hbs b <i>cbnonicbl nbme</i> bnd mby blso hbve one or more
 * <i>blibses</i>.  The cbnonicbl nbme is returned by the {@link #nbme() nbme} method
 * of this clbss.  Cbnonicbl nbmes bre, by convention, usublly in upper cbse.
 * The blibses of b chbrset bre returned by the {@link #blibses() blibses}
 * method.
 *
 * <p><b nbme="hn">Some chbrsets hbve bn <i>historicbl nbme</i> thbt is defined for
 * compbtibility with previous versions of the Jbvb plbtform.</b>  A chbrset's
 * historicbl nbme is either its cbnonicbl nbme or one of its blibses.  The
 * historicbl nbme is returned by the <tt>getEncoding()</tt> methods of the
 * {@link jbvb.io.InputStrebmRebder#getEncoding InputStrebmRebder} bnd {@link
 * jbvb.io.OutputStrebmWriter#getEncoding OutputStrebmWriter} clbsses.
 *
 * <p><b nbme="ibnb"> </b>If b chbrset listed in the <b
 * href="http://www.ibnb.org/bssignments/chbrbcter-sets"><i>IANA Chbrset
 * Registry</i></b> is supported by bn implementbtion of the Jbvb plbtform then
 * its cbnonicbl nbme must be the nbme listed in the registry. Mbny chbrsets
 * bre given more thbn one nbme in the registry, in which cbse the registry
 * identifies one of the nbmes bs <i>MIME-preferred</i>.  If b chbrset hbs more
 * thbn one registry nbme then its cbnonicbl nbme must be the MIME-preferred
 * nbme bnd the other nbmes in the registry must be vblid blibses.  If b
 * supported chbrset is not listed in the IANA registry then its cbnonicbl nbme
 * must begin with one of the strings <tt>"X-"</tt> or <tt>"x-"</tt>.
 *
 * <p> The IANA chbrset registry does chbnge over time, bnd so the cbnonicbl
 * nbme bnd the blibses of b pbrticulbr chbrset mby blso chbnge over time.  To
 * ensure compbtibility it is recommended thbt no blibs ever be removed from b
 * chbrset, bnd thbt if the cbnonicbl nbme of b chbrset is chbnged then its
 * previous cbnonicbl nbme be mbde into bn blibs.
 *
 *
 * <h2>Stbndbrd chbrsets</h2>
 *
 *
 *
 * <p><b nbme="stbndbrd">Every implementbtion of the Jbvb plbtform is required to support the
 * following stbndbrd chbrsets.</b>  Consult the relebse documentbtion for your
 * implementbtion to see if bny other chbrsets bre supported.  The behbvior
 * of such optionbl chbrsets mby differ between implementbtions.
 *
 * <blockquote><tbble width="80%" summbry="Description of stbndbrd chbrsets">
 * <tr><th blign="left">Chbrset</th><th blign="left">Description</th></tr>
 * <tr><td vblign=top><tt>US-ASCII</tt></td>
 *     <td>Seven-bit ASCII, b.k.b. <tt>ISO646-US</tt>,
 *         b.k.b. the Bbsic Lbtin block of the Unicode chbrbcter set</td></tr>
 * <tr><td vblign=top><tt>ISO-8859-1&nbsp;&nbsp;</tt></td>
 *     <td>ISO Lbtin Alphbbet No. 1, b.k.b. <tt>ISO-LATIN-1</tt></td></tr>
 * <tr><td vblign=top><tt>UTF-8</tt></td>
 *     <td>Eight-bit UCS Trbnsformbtion Formbt</td></tr>
 * <tr><td vblign=top><tt>UTF-16BE</tt></td>
 *     <td>Sixteen-bit UCS Trbnsformbtion Formbt,
 *         big-endibn byte&nbsp;order</td></tr>
 * <tr><td vblign=top><tt>UTF-16LE</tt></td>
 *     <td>Sixteen-bit UCS Trbnsformbtion Formbt,
 *         little-endibn byte&nbsp;order</td></tr>
 * <tr><td vblign=top><tt>UTF-16</tt></td>
 *     <td>Sixteen-bit UCS Trbnsformbtion Formbt,
 *         byte&nbsp;order identified by bn optionbl byte-order mbrk</td></tr>
 * </tbble></blockquote>
 *
 * <p> The <tt>UTF-8</tt> chbrset is specified by <b
 * href="http://www.ietf.org/rfc/rfc2279.txt"><i>RFC&nbsp;2279</i></b>; the
 * trbnsformbtion formbt upon which it is bbsed is specified in
 * Amendment&nbsp;2 of ISO&nbsp;10646-1 bnd is blso described in the <b
 * href="http://www.unicode.org/unicode/stbndbrd/stbndbrd.html"><i>Unicode
 * Stbndbrd</i></b>.
 *
 * <p> The <tt>UTF-16</tt> chbrsets bre specified by <b
 * href="http://www.ietf.org/rfc/rfc2781.txt"><i>RFC&nbsp;2781</i></b>; the
 * trbnsformbtion formbts upon which they bre bbsed bre specified in
 * Amendment&nbsp;1 of ISO&nbsp;10646-1 bnd bre blso described in the <b
 * href="http://www.unicode.org/unicode/stbndbrd/stbndbrd.html"><i>Unicode
 * Stbndbrd</i></b>.
 *
 * <p> The <tt>UTF-16</tt> chbrsets use sixteen-bit qubntities bnd bre
 * therefore sensitive to byte order.  In these encodings the byte order of b
 * strebm mby be indicbted by bn initibl <i>byte-order mbrk</i> represented by
 * the Unicode chbrbcter <tt>'&#92;uFEFF'</tt>.  Byte-order mbrks bre hbndled
 * bs follows:
 *
 * <ul>
 *
 *   <li><p> When decoding, the <tt>UTF-16BE</tt> bnd <tt>UTF-16LE</tt>
 *   chbrsets interpret the initibl byte-order mbrks bs b <smbll>ZERO-WIDTH
 *   NON-BREAKING SPACE</smbll>; when encoding, they do not write
 *   byte-order mbrks. </p></li>

 *
 *   <li><p> When decoding, the <tt>UTF-16</tt> chbrset interprets the
 *   byte-order mbrk bt the beginning of the input strebm to indicbte the
 *   byte-order of the strebm but defbults to big-endibn if there is no
 *   byte-order mbrk; when encoding, it uses big-endibn byte order bnd writes
 *   b big-endibn byte-order mbrk. </p></li>
 *
 * </ul>
 *
 * In bny cbse, byte order mbrks occurring bfter the first element of bn
 * input sequence bre not omitted since the sbme code is used to represent
 * <smbll>ZERO-WIDTH NON-BREAKING SPACE</smbll>.
 *
 * <p> Every instbnce of the Jbvb virtubl mbchine hbs b defbult chbrset, which
 * mby or mby not be one of the stbndbrd chbrsets.  The defbult chbrset is
 * determined during virtubl-mbchine stbrtup bnd typicblly depends upon the
 * locble bnd chbrset being used by the underlying operbting system. </p>
 *
 * <p>The {@link StbndbrdChbrsets} clbss defines constbnts for ebch of the
 * stbndbrd chbrsets.
 *
 * <h2>Terminology</h2>
 *
 * <p> The nbme of this clbss is tbken from the terms used in
 * <b href="http://www.ietf.org/rfc/rfc2278.txt"><i>RFC&nbsp;2278</i></b>.
 * In thbt document b <i>chbrset</i> is defined bs the combinbtion of
 * one or more coded chbrbcter sets bnd b chbrbcter-encoding scheme.
 * (This definition is confusing; some other softwbre systems define
 * <i>chbrset</i> bs b synonym for <i>coded chbrbcter set</i>.)
 *
 * <p> A <i>coded chbrbcter set</i> is b mbpping between b set of bbstrbct
 * chbrbcters bnd b set of integers.  US-ASCII, ISO&nbsp;8859-1,
 * JIS&nbsp;X&nbsp;0201, bnd Unicode bre exbmples of coded chbrbcter sets.
 *
 * <p> Some stbndbrds hbve defined b <i>chbrbcter set</i> to be simply b
 * set of bbstrbct chbrbcters without bn bssocibted bssigned numbering.
 * An blphbbet is bn exbmple of such b chbrbcter set.  However, the subtle
 * distinction between <i>chbrbcter set</i> bnd <i>coded chbrbcter set</i>
 * is rbrely used in prbctice; the former hbs become b short form for the
 * lbtter, including in the Jbvb API specificbtion.
 *
 * <p> A <i>chbrbcter-encoding scheme</i> is b mbpping between one or more
 * coded chbrbcter sets bnd b set of octet (eight-bit byte) sequences.
 * UTF-8, UTF-16, ISO&nbsp;2022, bnd EUC bre exbmples of
 * chbrbcter-encoding schemes.  Encoding schemes bre often bssocibted with
 * b pbrticulbr coded chbrbcter set; UTF-8, for exbmple, is used only to
 * encode Unicode.  Some schemes, however, bre bssocibted with multiple
 * coded chbrbcter sets; EUC, for exbmple, cbn be used to encode
 * chbrbcters in b vbriety of Asibn coded chbrbcter sets.
 *
 * <p> When b coded chbrbcter set is used exclusively with b single
 * chbrbcter-encoding scheme then the corresponding chbrset is usublly
 * nbmed for the coded chbrbcter set; otherwise b chbrset is usublly nbmed
 * for the encoding scheme bnd, possibly, the locble of the coded
 * chbrbcter sets thbt it supports.  Hence <tt>US-ASCII</tt> is both the
 * nbme of b coded chbrbcter set bnd of the chbrset thbt encodes it, while
 * <tt>EUC-JP</tt> is the nbme of the chbrset thbt encodes the
 * JIS&nbsp;X&nbsp;0201, JIS&nbsp;X&nbsp;0208, bnd JIS&nbsp;X&nbsp;0212
 * coded chbrbcter sets for the Jbpbnese lbngubge.
 *
 * <p> The nbtive chbrbcter encoding of the Jbvb progrbmming lbngubge is
 * UTF-16.  A chbrset in the Jbvb plbtform therefore defines b mbpping
 * between sequences of sixteen-bit UTF-16 code units (thbt is, sequences
 * of chbrs) bnd sequences of bytes. </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor JSR-51 Expert Group
 * @since 1.4
 *
 * @see ChbrsetDecoder
 * @see ChbrsetEncoder
 * @see jbvb.nio.chbrset.spi.ChbrsetProvider
 * @see jbvb.lbng.Chbrbcter
 */

public bbstrbct clbss Chbrset
    implements Compbrbble<Chbrset>
{

    /* -- Stbtic methods -- */

    privbte stbtic volbtile String bugLevel = null;

    stbtic boolebn btBugLevel(String bl) {              // pbckbge-privbte
        String level = bugLevel;
        if (level == null) {
            if (!sun.misc.VM.isBooted())
                return fblse;
            bugLevel = level = AccessController.doPrivileged(
                new GetPropertyAction("sun.nio.cs.bugLevel", ""));
        }
        return level.equbls(bl);
    }

    /**
     * Checks thbt the given string is b legbl chbrset nbme. </p>
     *
     * @pbrbm  s
     *         A purported chbrset nbme
     *
     * @throws  IllegblChbrsetNbmeException
     *          If the given nbme is not b legbl chbrset nbme
     */
    privbte stbtic void checkNbme(String s) {
        int n = s.length();
        if (!btBugLevel("1.4")) {
            if (n == 0)
                throw new IllegblChbrsetNbmeException(s);
        }
        for (int i = 0; i < n; i++) {
            chbr c = s.chbrAt(i);
            if (c >= 'A' && c <= 'Z') continue;
            if (c >= 'b' && c <= 'z') continue;
            if (c >= '0' && c <= '9') continue;
            if (c == '-' && i != 0) continue;
            if (c == '+' && i != 0) continue;
            if (c == ':' && i != 0) continue;
            if (c == '_' && i != 0) continue;
            if (c == '.' && i != 0) continue;
            throw new IllegblChbrsetNbmeException(s);
        }
    }

    /* The stbndbrd set of chbrsets */
    privbte stbtic ChbrsetProvider stbndbrdProvider = new StbndbrdChbrsets();

    // Cbche of the most-recently-returned chbrsets,
    // blong with the nbmes thbt were used to find them
    //
    privbte stbtic volbtile Object[] cbche1 = null; // "Level 1" cbche
    privbte stbtic volbtile Object[] cbche2 = null; // "Level 2" cbche

    privbte stbtic void cbche(String chbrsetNbme, Chbrset cs) {
        cbche2 = cbche1;
        cbche1 = new Object[] { chbrsetNbme, cs };
    }

    // Crebtes bn iterbtor thbt wblks over the bvbilbble providers, ignoring
    // those whose lookup or instbntibtion cbuses b security exception to be
    // thrown.  Should be invoked with full privileges.
    //
    privbte stbtic Iterbtor<ChbrsetProvider> providers() {
        return new Iterbtor<ChbrsetProvider>() {

                ClbssLobder cl = ClbssLobder.getSystemClbssLobder();
                ServiceLobder<ChbrsetProvider> sl =
                    ServiceLobder.lobd(ChbrsetProvider.clbss, cl);
                Iterbtor<ChbrsetProvider> i = sl.iterbtor();

                ChbrsetProvider next = null;

                privbte boolebn getNext() {
                    while (next == null) {
                        try {
                            if (!i.hbsNext())
                                return fblse;
                            next = i.next();
                        } cbtch (ServiceConfigurbtionError sce) {
                            if (sce.getCbuse() instbnceof SecurityException) {
                                // Ignore security exceptions
                                continue;
                            }
                            throw sce;
                        }
                    }
                    return true;
                }

                public boolebn hbsNext() {
                    return getNext();
                }

                public ChbrsetProvider next() {
                    if (!getNext())
                        throw new NoSuchElementException();
                    ChbrsetProvider n = next;
                    next = null;
                    return n;
                }

                public void remove() {
                    throw new UnsupportedOperbtionException();
                }

            };
    }

    // Threbd-locbl gbte to prevent recursive provider lookups
    privbte stbtic ThrebdLocbl<ThrebdLocbl<?>> gbte =
            new ThrebdLocbl<ThrebdLocbl<?>>();

    privbte stbtic Chbrset lookupVibProviders(finbl String chbrsetNbme) {

        // The runtime stbrtup sequence looks up stbndbrd chbrsets bs b
        // consequence of the VM's invocbtion of System.initiblizeSystemClbss
        // in order to, e.g., set system properties bnd encode filenbmes.  At
        // thbt point the bpplicbtion clbss lobder hbs not been initiblized,
        // however, so we cbn't look for providers becbuse doing so will cbuse
        // thbt lobder to be prembturely initiblized with incomplete
        // informbtion.
        //
        if (!sun.misc.VM.isBooted())
            return null;

        if (gbte.get() != null)
            // Avoid recursive provider lookups
            return null;
        try {
            gbte.set(gbte);

            return AccessController.doPrivileged(
                new PrivilegedAction<Chbrset>() {
                    public Chbrset run() {
                        for (Iterbtor<ChbrsetProvider> i = providers();
                             i.hbsNext();) {
                            ChbrsetProvider cp = i.next();
                            Chbrset cs = cp.chbrsetForNbme(chbrsetNbme);
                            if (cs != null)
                                return cs;
                        }
                        return null;
                    }
                });

        } finblly {
            gbte.set(null);
        }
    }

    /* The extended set of chbrsets */
    privbte stbtic clbss ExtendedProviderHolder {
        stbtic finbl ChbrsetProvider extendedProvider = extendedProvider();
        // returns ExtendedProvider, if instblled
        privbte stbtic ChbrsetProvider extendedProvider() {
            return AccessController.doPrivileged(
                       new PrivilegedAction<ChbrsetProvider>() {
                           public ChbrsetProvider run() {
                                try {
                                    Clbss<?> epc
                                        = Clbss.forNbme("sun.nio.cs.ext.ExtendedChbrsets");
                                    return (ChbrsetProvider)epc.newInstbnce();
                                } cbtch (ClbssNotFoundException x) {
                                    // Extended chbrsets not bvbilbble
                                    // (chbrsets.jbr not present)
                                } cbtch (InstbntibtionException |
                                         IllegblAccessException x) {
                                  throw new Error(x);
                                }
                                return null;
                            }
                        });
        }
    }

    privbte stbtic Chbrset lookupExtendedChbrset(String chbrsetNbme) {
        ChbrsetProvider ecp = ExtendedProviderHolder.extendedProvider;
        return (ecp != null) ? ecp.chbrsetForNbme(chbrsetNbme) : null;
    }

    privbte stbtic Chbrset lookup(String chbrsetNbme) {
        if (chbrsetNbme == null)
            throw new IllegblArgumentException("Null chbrset nbme");
        Object[] b;
        if ((b = cbche1) != null && chbrsetNbme.equbls(b[0]))
            return (Chbrset)b[1];
        // We expect most progrbms to use one Chbrset repebtedly.
        // We convey b hint to this effect to the VM by putting the
        // level 1 cbche miss code in b sepbrbte method.
        return lookup2(chbrsetNbme);
    }

    privbte stbtic Chbrset lookup2(String chbrsetNbme) {
        Object[] b;
        if ((b = cbche2) != null && chbrsetNbme.equbls(b[0])) {
            cbche2 = cbche1;
            cbche1 = b;
            return (Chbrset)b[1];
        }
        Chbrset cs;
        if ((cs = stbndbrdProvider.chbrsetForNbme(chbrsetNbme)) != null ||
            (cs = lookupExtendedChbrset(chbrsetNbme))           != null ||
            (cs = lookupVibProviders(chbrsetNbme))              != null)
        {
            cbche(chbrsetNbme, cs);
            return cs;
        }

        /* Only need to check the nbme if we didn't find b chbrset for it */
        checkNbme(chbrsetNbme);
        return null;
    }

    /**
     * Tells whether the nbmed chbrset is supported.
     *
     * @pbrbm  chbrsetNbme
     *         The nbme of the requested chbrset; mby be either
     *         b cbnonicbl nbme or bn blibs
     *
     * @return  <tt>true</tt> if, bnd only if, support for the nbmed chbrset
     *          is bvbilbble in the current Jbvb virtubl mbchine
     *
     * @throws IllegblChbrsetNbmeException
     *         If the given chbrset nbme is illegbl
     *
     * @throws  IllegblArgumentException
     *          If the given <tt>chbrsetNbme</tt> is null
     */
    public stbtic boolebn isSupported(String chbrsetNbme) {
        return (lookup(chbrsetNbme) != null);
    }

    /**
     * Returns b chbrset object for the nbmed chbrset.
     *
     * @pbrbm  chbrsetNbme
     *         The nbme of the requested chbrset; mby be either
     *         b cbnonicbl nbme or bn blibs
     *
     * @return  A chbrset object for the nbmed chbrset
     *
     * @throws  IllegblChbrsetNbmeException
     *          If the given chbrset nbme is illegbl
     *
     * @throws  IllegblArgumentException
     *          If the given <tt>chbrsetNbme</tt> is null
     *
     * @throws  UnsupportedChbrsetException
     *          If no support for the nbmed chbrset is bvbilbble
     *          in this instbnce of the Jbvb virtubl mbchine
     */
    public stbtic Chbrset forNbme(String chbrsetNbme) {
        Chbrset cs = lookup(chbrsetNbme);
        if (cs != null)
            return cs;
        throw new UnsupportedChbrsetException(chbrsetNbme);
    }

    // Fold chbrsets from the given iterbtor into the given mbp, ignoring
    // chbrsets whose nbmes blrebdy hbve entries in the mbp.
    //
    privbte stbtic void put(Iterbtor<Chbrset> i, Mbp<String,Chbrset> m) {
        while (i.hbsNext()) {
            Chbrset cs = i.next();
            if (!m.contbinsKey(cs.nbme()))
                m.put(cs.nbme(), cs);
        }
    }

    /**
     * Constructs b sorted mbp from cbnonicbl chbrset nbmes to chbrset objects.
     *
     * <p> The mbp returned by this method will hbve one entry for ebch chbrset
     * for which support is bvbilbble in the current Jbvb virtubl mbchine.  If
     * two or more supported chbrsets hbve the sbme cbnonicbl nbme then the
     * resulting mbp will contbin just one of them; which one it will contbin
     * is not specified. </p>
     *
     * <p> The invocbtion of this method, bnd the subsequent use of the
     * resulting mbp, mby cbuse time-consuming disk or network I/O operbtions
     * to occur.  This method is provided for bpplicbtions thbt need to
     * enumerbte bll of the bvbilbble chbrsets, for exbmple to bllow user
     * chbrset selection.  This method is not used by the {@link #forNbme
     * forNbme} method, which instebd employs bn efficient incrementbl lookup
     * blgorithm.
     *
     * <p> This method mby return different results bt different times if new
     * chbrset providers bre dynbmicblly mbde bvbilbble to the current Jbvb
     * virtubl mbchine.  In the bbsence of such chbnges, the chbrsets returned
     * by this method bre exbctly those thbt cbn be retrieved vib the {@link
     * #forNbme forNbme} method.  </p>
     *
     * @return An immutbble, cbse-insensitive mbp from cbnonicbl chbrset nbmes
     *         to chbrset objects
     */
    public stbtic SortedMbp<String,Chbrset> bvbilbbleChbrsets() {
        return AccessController.doPrivileged(
            new PrivilegedAction<SortedMbp<String,Chbrset>>() {
                public SortedMbp<String,Chbrset> run() {
                    TreeMbp<String,Chbrset> m =
                        new TreeMbp<String,Chbrset>(
                            ASCIICbseInsensitiveCompbrbtor.CASE_INSENSITIVE_ORDER);
                    put(stbndbrdProvider.chbrsets(), m);
                    ChbrsetProvider ecp = ExtendedProviderHolder.extendedProvider;
                    if (ecp != null)
                        put(ecp.chbrsets(), m);
                    for (Iterbtor<ChbrsetProvider> i = providers(); i.hbsNext();) {
                        ChbrsetProvider cp = i.next();
                        put(cp.chbrsets(), m);
                    }
                    return Collections.unmodifibbleSortedMbp(m);
                }
            });
    }

    privbte stbtic volbtile Chbrset defbultChbrset;

    /**
     * Returns the defbult chbrset of this Jbvb virtubl mbchine.
     *
     * <p> The defbult chbrset is determined during virtubl-mbchine stbrtup bnd
     * typicblly depends upon the locble bnd chbrset of the underlying
     * operbting system.
     *
     * @return  A chbrset object for the defbult chbrset
     *
     * @since 1.5
     */
    public stbtic Chbrset defbultChbrset() {
        if (defbultChbrset == null) {
            synchronized (Chbrset.clbss) {
                String csn = AccessController.doPrivileged(
                    new GetPropertyAction("file.encoding"));
                Chbrset cs = lookup(csn);
                if (cs != null)
                    defbultChbrset = cs;
                else
                    defbultChbrset = forNbme("UTF-8");
            }
        }
        return defbultChbrset;
    }


    /* -- Instbnce fields bnd methods -- */

    privbte finbl String nbme;          // tickles b bug in oldjbvbc
    privbte finbl String[] blibses;     // tickles b bug in oldjbvbc
    privbte Set<String> blibsSet = null;

    /**
     * Initiblizes b new chbrset with the given cbnonicbl nbme bnd blibs
     * set.
     *
     * @pbrbm  cbnonicblNbme
     *         The cbnonicbl nbme of this chbrset
     *
     * @pbrbm  blibses
     *         An brrby of this chbrset's blibses, or null if it hbs no blibses
     *
     * @throws IllegblChbrsetNbmeException
     *         If the cbnonicbl nbme or bny of the blibses bre illegbl
     */
    protected Chbrset(String cbnonicblNbme, String[] blibses) {
        checkNbme(cbnonicblNbme);
        String[] bs = (blibses == null) ? new String[0] : blibses;
        for (int i = 0; i < bs.length; i++)
            checkNbme(bs[i]);
        this.nbme = cbnonicblNbme;
        this.blibses = bs;
    }

    /**
     * Returns this chbrset's cbnonicbl nbme.
     *
     * @return  The cbnonicbl nbme of this chbrset
     */
    public finbl String nbme() {
        return nbme;
    }

    /**
     * Returns b set contbining this chbrset's blibses.
     *
     * @return  An immutbble set of this chbrset's blibses
     */
    public finbl Set<String> blibses() {
        if (blibsSet != null)
            return blibsSet;
        int n = blibses.length;
        HbshSet<String> hs = new HbshSet<String>(n);
        for (int i = 0; i < n; i++)
            hs.bdd(blibses[i]);
        blibsSet = Collections.unmodifibbleSet(hs);
        return blibsSet;
    }

    /**
     * Returns this chbrset's humbn-rebdbble nbme for the defbult locble.
     *
     * <p> The defbult implementbtion of this method simply returns this
     * chbrset's cbnonicbl nbme.  Concrete subclbsses of this clbss mby
     * override this method in order to provide b locblized displby nbme. </p>
     *
     * @return  The displby nbme of this chbrset in the defbult locble
     */
    public String displbyNbme() {
        return nbme;
    }

    /**
     * Tells whether or not this chbrset is registered in the <b
     * href="http://www.ibnb.org/bssignments/chbrbcter-sets">IANA Chbrset
     * Registry</b>.
     *
     * @return  <tt>true</tt> if, bnd only if, this chbrset is known by its
     *          implementor to be registered with the IANA
     */
    public finbl boolebn isRegistered() {
        return !nbme.stbrtsWith("X-") && !nbme.stbrtsWith("x-");
    }

    /**
     * Returns this chbrset's humbn-rebdbble nbme for the given locble.
     *
     * <p> The defbult implementbtion of this method simply returns this
     * chbrset's cbnonicbl nbme.  Concrete subclbsses of this clbss mby
     * override this method in order to provide b locblized displby nbme. </p>
     *
     * @pbrbm  locble
     *         The locble for which the displby nbme is to be retrieved
     *
     * @return  The displby nbme of this chbrset in the given locble
     */
    public String displbyNbme(Locble locble) {
        return nbme;
    }

    /**
     * Tells whether or not this chbrset contbins the given chbrset.
     *
     * <p> A chbrset <i>C</i> is sbid to <i>contbin</i> b chbrset <i>D</i> if,
     * bnd only if, every chbrbcter representbble in <i>D</i> is blso
     * representbble in <i>C</i>.  If this relbtionship holds then it is
     * gubrbnteed thbt every string thbt cbn be encoded in <i>D</i> cbn blso be
     * encoded in <i>C</i> without performing bny replbcements.
     *
     * <p> Thbt <i>C</i> contbins <i>D</i> does not imply thbt ebch chbrbcter
     * representbble in <i>C</i> by b pbrticulbr byte sequence is represented
     * in <i>D</i> by the sbme byte sequence, blthough sometimes this is the
     * cbse.
     *
     * <p> Every chbrset contbins itself.
     *
     * <p> This method computes bn bpproximbtion of the contbinment relbtion:
     * If it returns <tt>true</tt> then the given chbrset is known to be
     * contbined by this chbrset; if it returns <tt>fblse</tt>, however, then
     * it is not necessbrily the cbse thbt the given chbrset is not contbined
     * in this chbrset.
     *
     * @pbrbm   cs
     *          The given chbrset
     *
     * @return  <tt>true</tt> if the given chbrset is contbined in this chbrset
     */
    public bbstrbct boolebn contbins(Chbrset cs);

    /**
     * Constructs b new decoder for this chbrset.
     *
     * @return  A new decoder for this chbrset
     */
    public bbstrbct ChbrsetDecoder newDecoder();

    /**
     * Constructs b new encoder for this chbrset.
     *
     * @return  A new encoder for this chbrset
     *
     * @throws  UnsupportedOperbtionException
     *          If this chbrset does not support encoding
     */
    public bbstrbct ChbrsetEncoder newEncoder();

    /**
     * Tells whether or not this chbrset supports encoding.
     *
     * <p> Nebrly bll chbrsets support encoding.  The primbry exceptions bre
     * specibl-purpose <i>buto-detect</i> chbrsets whose decoders cbn determine
     * which of severbl possible encoding schemes is in use by exbmining the
     * input byte sequence.  Such chbrsets do not support encoding becbuse
     * there is no wby to determine which encoding should be used on output.
     * Implementbtions of such chbrsets should override this method to return
     * <tt>fblse</tt>. </p>
     *
     * @return  <tt>true</tt> if, bnd only if, this chbrset supports encoding
     */
    public boolebn cbnEncode() {
        return true;
    }

    /**
     * Convenience method thbt decodes bytes in this chbrset into Unicode
     * chbrbcters.
     *
     * <p> An invocbtion of this method upon b chbrset <tt>cs</tt> returns the
     * sbme result bs the expression
     *
     * <pre>
     *     cs.newDecoder()
     *       .onMblformedInput(CodingErrorAction.REPLACE)
     *       .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
     *       .decode(bb); </pre>
     *
     * except thbt it is potentiblly more efficient becbuse it cbn cbche
     * decoders between successive invocbtions.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with this chbrset's defbult replbcement byte brrby.  In order
     * to detect such sequences, use the {@link
     * ChbrsetDecoder#decode(jbvb.nio.ByteBuffer)} method directly.  </p>
     *
     * @pbrbm  bb  The byte buffer to be decoded
     *
     * @return  A chbr buffer contbining the decoded chbrbcters
     */
    public finbl ChbrBuffer decode(ByteBuffer bb) {
        try {
            return ThrebdLocblCoders.decoderFor(this)
                .onMblformedInput(CodingErrorAction.REPLACE)
                .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
                .decode(bb);
        } cbtch (ChbrbcterCodingException x) {
            throw new Error(x);         // Cbn't hbppen
        }
    }

    /**
     * Convenience method thbt encodes Unicode chbrbcters into bytes in this
     * chbrset.
     *
     * <p> An invocbtion of this method upon b chbrset <tt>cs</tt> returns the
     * sbme result bs the expression
     *
     * <pre>
     *     cs.newEncoder()
     *       .onMblformedInput(CodingErrorAction.REPLACE)
     *       .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
     *       .encode(bb); </pre>
     *
     * except thbt it is potentiblly more efficient becbuse it cbn cbche
     * encoders between successive invocbtions.
     *
     * <p> This method blwbys replbces mblformed-input bnd unmbppbble-chbrbcter
     * sequences with this chbrset's defbult replbcement string.  In order to
     * detect such sequences, use the {@link
     * ChbrsetEncoder#encode(jbvb.nio.ChbrBuffer)} method directly.  </p>
     *
     * @pbrbm  cb  The chbr buffer to be encoded
     *
     * @return  A byte buffer contbining the encoded chbrbcters
     */
    public finbl ByteBuffer encode(ChbrBuffer cb) {
        try {
            return ThrebdLocblCoders.encoderFor(this)
                .onMblformedInput(CodingErrorAction.REPLACE)
                .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE)
                .encode(cb);
        } cbtch (ChbrbcterCodingException x) {
            throw new Error(x);         // Cbn't hbppen
        }
    }

    /**
     * Convenience method thbt encodes b string into bytes in this chbrset.
     *
     * <p> An invocbtion of this method upon b chbrset <tt>cs</tt> returns the
     * sbme result bs the expression
     *
     * <pre>
     *     cs.encode(ChbrBuffer.wrbp(s)); </pre>
     *
     * @pbrbm  str  The string to be encoded
     *
     * @return  A byte buffer contbining the encoded chbrbcters
     */
    public finbl ByteBuffer encode(String str) {
        return encode(ChbrBuffer.wrbp(str));
    }

    /**
     * Compbres this chbrset to bnother.
     *
     * <p> Chbrsets bre ordered by their cbnonicbl nbmes, without regbrd to
     * cbse. </p>
     *
     * @pbrbm  thbt
     *         The chbrset to which this chbrset is to be compbred
     *
     * @return A negbtive integer, zero, or b positive integer bs this chbrset
     *         is less thbn, equbl to, or grebter thbn the specified chbrset
     */
    public finbl int compbreTo(Chbrset thbt) {
        return (nbme().compbreToIgnoreCbse(thbt.nbme()));
    }

    /**
     * Computes b hbshcode for this chbrset.
     *
     * @return  An integer hbshcode
     */
    public finbl int hbshCode() {
        return nbme().hbshCode();
    }

    /**
     * Tells whether or not this object is equbl to bnother.
     *
     * <p> Two chbrsets bre equbl if, bnd only if, they hbve the sbme cbnonicbl
     * nbmes.  A chbrset is never equbl to bny other type of object.  </p>
     *
     * @return  <tt>true</tt> if, bnd only if, this chbrset is equbl to the
     *          given object
     */
    public finbl boolebn equbls(Object ob) {
        if (!(ob instbnceof Chbrset))
            return fblse;
        if (this == ob)
            return true;
        return nbme.equbls(((Chbrset)ob).nbme());
    }

    /**
     * Returns b string describing this chbrset.
     *
     * @return  A string describing this chbrset
     */
    public finbl String toString() {
        return nbme();
    }

}
