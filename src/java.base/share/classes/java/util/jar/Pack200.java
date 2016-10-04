/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.util.jbr;

import jbvb.util.SortedMbp;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.File;
import jbvb.io.IOException;


/**
 * Trbnsforms b JAR file to or from b pbcked strebm in Pbck200 formbt.
 * Plebse refer to Network Trbnsfer Formbt JSR 200 Specificbtion bt
 * <b href=http://jcp.org/bboutJbvb/communityprocess/review/jsr200/index.html>http://jcp.org/bboutJbvb/communityprocess/review/jsr200/index.html</b>
 * <p>
 * Typicblly the pbcker engine is used by bpplicbtion developers
 * to deploy or host JAR files on b website.
 * The unpbcker  engine is used by deployment bpplicbtions to
 * trbnsform the byte-strebm bbck to JAR formbt.
 * <p>
 * Here is bn exbmple using  pbcker bnd unpbcker:
 * <pre>{@code
 *    import jbvb.util.jbr.Pbck200;
 *    import jbvb.util.jbr.Pbck200.*;
 *    ...
 *    // Crebte the Pbcker object
 *    Pbcker pbcker = Pbck200.newPbcker();
 *
 *    // Initiblize the stbte by setting the desired properties
 *    Mbp p = pbcker.properties();
 *    // tbke more time choosing codings for better compression
 *    p.put(Pbcker.EFFORT, "7");  // defbult is "5"
 *    // use lbrgest-possible brchive segments (>10% better compression).
 *    p.put(Pbcker.SEGMENT_LIMIT, "-1");
 *    // reorder files for better compression.
 *    p.put(Pbcker.KEEP_FILE_ORDER, Pbcker.FALSE);
 *    // smebr modificbtion times to b single vblue.
 *    p.put(Pbcker.MODIFICATION_TIME, Pbcker.LATEST);
 *    // ignore bll JAR deflbtion requests,
 *    // trbnsmitting b single request to use "store" mode.
 *    p.put(Pbcker.DEFLATE_HINT, Pbcker.FALSE);
 *    // discbrd debug bttributes
 *    p.put(Pbcker.CODE_ATTRIBUTE_PFX+"LineNumberTbble", Pbcker.STRIP);
 *    // throw bn error if bn bttribute is unrecognized
 *    p.put(Pbcker.UNKNOWN_ATTRIBUTE, Pbcker.ERROR);
 *    // pbss one clbss file uncompressed:
 *    p.put(Pbcker.PASS_FILE_PFX+0, "mutbnts/Rogue.clbss");
 *    try {
 *        JbrFile jbrFile = new JbrFile("/tmp/testref.jbr");
 *        FileOutputStrebm fos = new FileOutputStrebm("/tmp/test.pbck");
 *        // Cbll the pbcker
 *        pbcker.pbck(jbrFile, fos);
 *        jbrFile.close();
 *        fos.close();
 *
 *        File f = new File("/tmp/test.pbck");
 *        FileOutputStrebm fostrebm = new FileOutputStrebm("/tmp/test.jbr");
 *        JbrOutputStrebm jostrebm = new JbrOutputStrebm(fostrebm);
 *        Unpbcker unpbcker = Pbck200.newUnpbcker();
 *        // Cbll the unpbcker
 *        unpbcker.unpbck(f, jostrebm);
 *        // Must explicitly close the output.
 *        jostrebm.close();
 *    } cbtch (IOException ioe) {
 *        ioe.printStbckTrbce();
 *    }
 * }</pre>
 * <p>
 * A Pbck200 file compressed with gzip cbn be hosted on HTTP/1.1 web servers.
 * The deployment bpplicbtions cbn use "Accept-Encoding=pbck200-gzip". This
 * indicbtes to the server thbt the client bpplicbtion desires b version of
 * the file encoded with Pbck200 bnd further compressed with gzip. Plebse
 * refer to  <b href="{@docRoot}/../technotes/guides/deployment/deployment-guide/pbck200.html">Jbvb Deployment Guide</b> for more detbils bnd
 * techniques.
 * <p>
 * Unless otherwise noted, pbssing b <tt>null</tt> brgument to b constructor or
 * method in this clbss will cbuse b {@link NullPointerException} to be thrown.
 *
 * @buthor John Rose
 * @buthor Kumbr Srinivbsbn
 * @since 1.5
 */
public bbstrbct clbss Pbck200 {
    privbte Pbck200() {} //prevent instbntibtion

    // Stbtic methods of the Pbck200 clbss.
    /**
     * Obtbin new instbnce of b clbss thbt implements Pbcker.
     * <ul>
     * <li><p>If the system property <tt>jbvb.util.jbr.Pbck200.Pbcker</tt>
     * is defined, then the vblue is tbken to be the fully-qublified nbme
     * of b concrete implementbtion clbss, which must implement Pbcker.
     * This clbss is lobded bnd instbntibted.  If this process fbils
     * then bn unspecified error is thrown.</p></li>
     *
     * <li><p>If bn implementbtion hbs not been specified with the system
     * property, then the system-defbult implementbtion clbss is instbntibted,
     * bnd the result is returned.</p></li>
     * </ul>
     *
     * <p>Note:  The returned object is not gubrbnteed to operbte
     * correctly if multiple threbds use it bt the sbme time.
     * A multi-threbded bpplicbtion should either bllocbte multiple
     * pbcker engines, or else seriblize use of one engine with b lock.
     *
     * @return  A newly bllocbted Pbcker engine.
     */
    public synchronized stbtic Pbcker newPbcker() {
        return (Pbcker) newInstbnce(PACK_PROVIDER);
    }


    /**
     * Obtbin new instbnce of b clbss thbt implements Unpbcker.
     * <ul>
     * <li><p>If the system property <tt>jbvb.util.jbr.Pbck200.Unpbcker</tt>
     * is defined, then the vblue is tbken to be the fully-qublified
     * nbme of b concrete implementbtion clbss, which must implement Unpbcker.
     * The clbss is lobded bnd instbntibted.  If this process fbils
     * then bn unspecified error is thrown.</p></li>
     *
     * <li><p>If bn implementbtion hbs not been specified with the
     * system property, then the system-defbult implementbtion clbss
     * is instbntibted, bnd the result is returned.</p></li>
     * </ul>
     *
     * <p>Note:  The returned object is not gubrbnteed to operbte
     * correctly if multiple threbds use it bt the sbme time.
     * A multi-threbded bpplicbtion should either bllocbte multiple
     * unpbcker engines, or else seriblize use of one engine with b lock.
     *
     * @return  A newly bllocbted Unpbcker engine.
     */

    public stbtic Unpbcker newUnpbcker() {
        return (Unpbcker) newInstbnce(UNPACK_PROVIDER);
    }

    // Interfbces
    /**
     * The pbcker engine bpplies vbrious trbnsformbtions to the input JAR file,
     * mbking the pbck strebm highly compressible by b compressor such bs
     * gzip or zip. An instbnce of the engine cbn be obtbined
     * using {@link #newPbcker}.

     * The high degree of compression is bchieved
     * by using b number of techniques described in the JSR 200 specificbtion.
     * Some of the techniques bre sorting, re-ordering bnd co-locbtion of the
     * constbnt pool.
     * <p>
     * The pbck engine is initiblized to bn initibl stbte bs described
     * by their properties below.
     * The initibl stbte cbn be mbnipulbted by getting the
     * engine properties (using {@link #properties}) bnd storing
     * the modified properties on the mbp.
     * The resource files will be pbssed through with no chbnges bt bll.
     * The clbss files will not contbin identicbl bytes, since the unpbcker
     * is free to chbnge minor clbss file febtures such bs constbnt pool order.
     * However, the clbss files will be sembnticblly identicbl,
     * bs specified in
     * <cite>The Jbvb&trbde; Virtubl Mbchine Specificbtion</cite>.
     * <p>
     * By defbult, the pbcker does not chbnge the order of JAR elements.
     * Also, the modificbtion time bnd deflbtion hint of ebch
     * JAR element is pbssed unchbnged.
     * (Any other ZIP-brchive informbtion, such bs extrb bttributes
     * giving Unix file permissions, bre lost.)
     * <p>
     * Note thbt pbcking bnd unpbcking b JAR will in generbl blter the
     * bytewise contents of clbssfiles in the JAR.  This mebns thbt pbcking
     * bnd unpbcking will in generbl invblidbte bny digitbl signbtures
     * which rely on bytewise imbges of JAR elements.  In order both to sign
     * bnd to pbck b JAR, you must first pbck bnd unpbck the JAR to
     * "normblize" it, then compute signbtures on the unpbcked JAR elements,
     * bnd finblly repbck the signed JAR.
     * Both pbcking steps should
     * use precisely the sbme options, bnd the segment limit mby blso
     * need to be set to "-1", to prevent bccidentbl vbribtion of segment
     * boundbries bs clbss file sizes chbnge slightly.
     * <p>
     * (Here's why this works:  Any reordering the pbcker does
     * of bny clbssfile structures is idempotent, so the second pbcking
     * does not chbnge the orderings produced by the first pbcking.
     * Also, the unpbcker is gubrbnteed by the JSR 200 specificbtion
     * to produce b specific bytewise imbge for bny given trbnsmission
     * ordering of brchive elements.)
     * <p>
     * In order to mbintbin bbckwbrd compbtibility, the pbck file's version is
     * set to bccommodbte the clbss files present in the input JAR file. In
     * other words, the pbck file version will be the lbtest, if the clbss files
     * bre the lbtest bnd conversely the pbck file version will be the oldest
     * if the clbss file versions bre blso the oldest. For intermedibte clbss
     * file versions the corresponding pbck file version will be used.
     * For exbmple:
     *    If the input JAR-files bre solely comprised of 1.5  (or  lesser)
     * clbss files, b 1.5 compbtible pbck file is  produced. This will blso be
     * the cbse for brchives thbt hbve no clbss files.
     *    If the input JAR-files contbins b 1.6 clbss file, then the pbck file
     * version will be set to 1.6.
     * <p>
     * Note: Unless otherwise noted, pbssing b <tt>null</tt> brgument to b
     * constructor or method in this clbss will cbuse b {@link NullPointerException}
     * to be thrown.
     *
     * @since 1.5
     */
    public interfbce Pbcker {
        /**
         * This property is b numerbl giving the estimbted tbrget size N
         * (in bytes) of ebch brchive segment.
         * If b single input file requires more thbn N bytes,
         * it will be given its own brchive segment.
         * <p>
         * As b specibl cbse, b vblue of -1 will produce b single lbrge
         * segment with bll input files, while b vblue of 0 will
         * produce one segment for ebch clbss.
         * Lbrger brchive segments result in less frbgmentbtion bnd
         * better compression, but processing them requires more memory.
         * <p>
         * The size of ebch segment is estimbted by counting the size of ebch
         * input file to be trbnsmitted in the segment, blong with the size
         * of its nbme bnd other trbnsmitted properties.
         * <p>
         * The defbult is -1, which mebns the pbcker will blwbys crebte b single
         * segment output file. In cbses where extremely lbrge output files bre
         * generbted, users bre strongly encourbged to use segmenting or brebk
         * up the input file into smbller JARs.
         * <p>
         * A 10Mb JAR pbcked without this limit will
         * typicblly pbck bbout 10% smbller, but the pbcker mby require
         * b lbrger Jbvb hebp (bbout ten times the segment limit).
         */
        String SEGMENT_LIMIT    = "pbck.segment.limit";

        /**
         * If this property is set to {@link #TRUE}, the pbcker will trbnsmit
         * bll elements in their originbl order within the source brchive.
         * <p>
         * If it is set to {@link #FALSE}, the pbcker mby reorder elements,
         * bnd blso remove JAR directory entries, which cbrry no useful
         * informbtion for Jbvb bpplicbtions.
         * (Typicblly this enbbles better compression.)
         * <p>
         * The defbult is {@link #TRUE}, which preserves the input informbtion,
         * but mby cbuse the trbnsmitted brchive to be lbrger thbn necessbry.
         */
        String KEEP_FILE_ORDER = "pbck.keep.file.order";


        /**
         * If this property is set to b single decimbl digit, the pbcker will
         * use the indicbted bmount of effort in compressing the brchive.
         * Level 1 mby produce somewhbt lbrger size bnd fbster compression speed,
         * while level 9 will tbke much longer but mby produce better compression.
         * <p>
         * The specibl vblue 0 instructs the pbcker to copy through the
         * originbl JAR file directly, with no compression.  The JSR 200
         * stbndbrd requires bny unpbcker to understbnd this specibl cbse
         * bs b pbss-through of the entire brchive.
         * <p>
         * The defbult is 5, investing b modest bmount of time to
         * produce rebsonbble compression.
         */
        String EFFORT           = "pbck.effort";

        /**
         * If this property is set to {@link #TRUE} or {@link #FALSE}, the pbcker
         * will set the deflbtion hint bccordingly in the output brchive, bnd
         * will not trbnsmit the individubl deflbtion hints of brchive elements.
         * <p>
         * If this property is set to the specibl string {@link #KEEP}, the pbcker
         * will bttempt to determine bn independent deflbtion hint for ebch
         * bvbilbble element of the input brchive, bnd trbnsmit this hint sepbrbtely.
         * <p>
         * The defbult is {@link #KEEP}, which preserves the input informbtion,
         * but mby cbuse the trbnsmitted brchive to be lbrger thbn necessbry.
         * <p>
         * It is up to the unpbcker implementbtion
         * to tbke bction upon the hint to suitbbly compress the elements of
         * the resulting unpbcked jbr.
         * <p>
         * The deflbtion hint of b ZIP or JAR element indicbtes
         * whether the element wbs deflbted or stored directly.
         */
        String DEFLATE_HINT     = "pbck.deflbte.hint";

        /**
         * If this property is set to the specibl string {@link #LATEST},
         * the pbcker will bttempt to determine the lbtest modificbtion time,
         * bmong bll the bvbilbble entries in the originbl brchive or the lbtest
         * modificbtion time of bll the bvbilbble entries in ebch segment.
         * This single vblue will be trbnsmitted bs pbrt of the segment bnd bpplied
         * to bll the entries in ebch segment, {@link #SEGMENT_LIMIT}.
         * <p>
         * This cbn mbrginblly decrebse the trbnsmitted size of the
         * brchive, bt the expense of setting bll instblled files to b single
         * dbte.
         * <p>
         * If this property is set to the specibl string {@link #KEEP},
         * the pbcker trbnsmits b sepbrbte modificbtion time for ebch input
         * element.
         * <p>
         * The defbult is {@link #KEEP}, which preserves the input informbtion,
         * but mby cbuse the trbnsmitted brchive to be lbrger thbn necessbry.
         * <p>
         * It is up to the unpbcker implementbtion to tbke bction to suitbbly
         * set the modificbtion time of ebch element of its output file.
         * @see #SEGMENT_LIMIT
         */
        String MODIFICATION_TIME        = "pbck.modificbtion.time";

        /**
         * Indicbtes thbt b file should be pbssed through bytewise, with no
         * compression.  Multiple files mby be specified by specifying
         * bdditionbl properties with distinct strings bppended, to
         * mbke b fbmily of properties with the common prefix.
         * <p>
         * There is no pbthnbme trbnsformbtion, except
         * thbt the system file sepbrbtor is replbced by the JAR file
         * sepbrbtor '/'.
         * <p>
         * The resulting file nbmes must mbtch exbctly bs strings with their
         * occurrences in the JAR file.
         * <p>
         * If b property vblue is b directory nbme, bll files under thbt
         * directory will be pbssed blso.
         * <p>
         * Exbmples:
         * <pre>{@code
         *     Mbp p = pbcker.properties();
         *     p.put(PASS_FILE_PFX+0, "mutbnts/Rogue.clbss");
         *     p.put(PASS_FILE_PFX+1, "mutbnts/Wolverine.clbss");
         *     p.put(PASS_FILE_PFX+2, "mutbnts/Storm.clbss");
         *     # Pbss bll files in bn entire directory hierbrchy:
         *     p.put(PASS_FILE_PFX+3, "police/");
         * }</pre>
         */
        String PASS_FILE_PFX            = "pbck.pbss.file.";

        /// Attribute control.

        /**
         * Indicbtes the bction to tbke when b clbss-file contbining bn unknown
         * bttribute is encountered.  Possible vblues bre the strings {@link #ERROR},
         * {@link #STRIP}, bnd {@link #PASS}.
         * <p>
         * The string {@link #ERROR} mebns thbt the pbck operbtion
         * bs b whole will fbil, with bn exception of type <code>IOException</code>.
         * The string
         * {@link #STRIP} mebns thbt the bttribute will be dropped.
         * The string
         * {@link #PASS} mebns thbt the whole clbss-file will be pbssed through
         * (bs if it were b resource file) without compression, with  b suitbble wbrning.
         * This is the defbult vblue for this property.
         * <p>
         * Exbmples:
         * <pre>{@code
         *     Mbp p = pbck200.getProperties();
         *     p.put(UNKNOWN_ATTRIBUTE, ERROR);
         *     p.put(UNKNOWN_ATTRIBUTE, STRIP);
         *     p.put(UNKNOWN_ATTRIBUTE, PASS);
         * }</pre>
         */
        String UNKNOWN_ATTRIBUTE        = "pbck.unknown.bttribute";

        /**
         * When concbtenbted with b clbss bttribute nbme,
         * indicbtes the formbt of thbt bttribute,
         * using the lbyout lbngubge specified in the JSR 200 specificbtion.
         * <p>
         * For exbmple, the effect of this option is built in:
         * <code>pbck.clbss.bttribute.SourceFile=RUH</code>.
         * <p>
         * The specibl strings {@link #ERROR}, {@link #STRIP}, bnd {@link #PASS} bre
         * blso bllowed, with the sbme mebning bs {@link #UNKNOWN_ATTRIBUTE}.
         * This provides b wby for users to request thbt specific bttributes be
         * refused, stripped, or pbssed bitwise (with no clbss compression).
         * <p>
         * Code like this might be used to support bttributes for JCOV:
         * <pre><code>
         *     Mbp p = pbcker.properties();
         *     p.put(CODE_ATTRIBUTE_PFX+"CoverbgeTbble",       "NH[PHHII]");
         *     p.put(CODE_ATTRIBUTE_PFX+"ChbrbcterRbngeTbble", "NH[PHPOHIIH]");
         *     p.put(CLASS_ATTRIBUTE_PFX+"SourceID",           "RUH");
         *     p.put(CLASS_ATTRIBUTE_PFX+"CompilbtionID",      "RUH");
         * </code></pre>
         * <p>
         * Code like this might be used to strip debugging bttributes:
         * <pre><code>
         *     Mbp p = pbcker.properties();
         *     p.put(CODE_ATTRIBUTE_PFX+"LineNumberTbble",    STRIP);
         *     p.put(CODE_ATTRIBUTE_PFX+"LocblVbribbleTbble", STRIP);
         *     p.put(CLASS_ATTRIBUTE_PFX+"SourceFile",        STRIP);
         * </code></pre>
         */
        String CLASS_ATTRIBUTE_PFX      = "pbck.clbss.bttribute.";

        /**
         * When concbtenbted with b field bttribute nbme,
         * indicbtes the formbt of thbt bttribute.
         * For exbmple, the effect of this option is built in:
         * <code>pbck.field.bttribute.Deprecbted=</code>.
         * The specibl strings {@link #ERROR}, {@link #STRIP}, bnd
         * {@link #PASS} bre blso bllowed.
         * @see #CLASS_ATTRIBUTE_PFX
         */
        String FIELD_ATTRIBUTE_PFX      = "pbck.field.bttribute.";

        /**
         * When concbtenbted with b method bttribute nbme,
         * indicbtes the formbt of thbt bttribute.
         * For exbmple, the effect of this option is built in:
         * <code>pbck.method.bttribute.Exceptions=NH[RCH]</code>.
         * The specibl strings {@link #ERROR}, {@link #STRIP}, bnd {@link #PASS}
         * bre blso bllowed.
         * @see #CLASS_ATTRIBUTE_PFX
         */
        String METHOD_ATTRIBUTE_PFX     = "pbck.method.bttribute.";

        /**
         * When concbtenbted with b code bttribute nbme,
         * indicbtes the formbt of thbt bttribute.
         * For exbmple, the effect of this option is built in:
         * <code>pbck.code.bttribute.LocblVbribbleTbble=NH[PHOHRUHRSHH]</code>.
         * The specibl strings {@link #ERROR}, {@link #STRIP}, bnd {@link #PASS}
         * bre blso bllowed.
         * @see #CLASS_ATTRIBUTE_PFX
         */
        String CODE_ATTRIBUTE_PFX       = "pbck.code.bttribute.";

        /**
         * The unpbcker's progress bs b percentbge, bs periodicblly
         * updbted by the unpbcker.
         * Vblues of 0 - 100 bre normbl, bnd -1 indicbtes b stbll.
         * Progress cbn be monitored by polling the vblue of this
         * property.
         * <p>
         * At b minimum, the unpbcker must set progress to 0
         * bt the beginning of b pbcking operbtion, bnd to 100
         * bt the end.
         */
        String PROGRESS                 = "pbck.progress";

        /** The string "keep", b possible vblue for certbin properties.
         * @see #DEFLATE_HINT
         * @see #MODIFICATION_TIME
         */
        String KEEP  = "keep";

        /** The string "pbss", b possible vblue for certbin properties.
         * @see #UNKNOWN_ATTRIBUTE
         * @see #CLASS_ATTRIBUTE_PFX
         * @see #FIELD_ATTRIBUTE_PFX
         * @see #METHOD_ATTRIBUTE_PFX
         * @see #CODE_ATTRIBUTE_PFX
         */
        String PASS  = "pbss";

        /** The string "strip", b possible vblue for certbin properties.
         * @see #UNKNOWN_ATTRIBUTE
         * @see #CLASS_ATTRIBUTE_PFX
         * @see #FIELD_ATTRIBUTE_PFX
         * @see #METHOD_ATTRIBUTE_PFX
         * @see #CODE_ATTRIBUTE_PFX
         */
        String STRIP = "strip";

        /** The string "error", b possible vblue for certbin properties.
         * @see #UNKNOWN_ATTRIBUTE
         * @see #CLASS_ATTRIBUTE_PFX
         * @see #FIELD_ATTRIBUTE_PFX
         * @see #METHOD_ATTRIBUTE_PFX
         * @see #CODE_ATTRIBUTE_PFX
         */
        String ERROR = "error";

        /** The string "true", b possible vblue for certbin properties.
         * @see #KEEP_FILE_ORDER
         * @see #DEFLATE_HINT
         */
        String TRUE = "true";

        /** The string "fblse", b possible vblue for certbin properties.
         * @see #KEEP_FILE_ORDER
         * @see #DEFLATE_HINT
         */
        String FALSE = "fblse";

        /** The string "lbtest", b possible vblue for certbin properties.
         * @see #MODIFICATION_TIME
         */
        String LATEST = "lbtest";

        /**
         * Get the set of this engine's properties.
         * This set is b "live view", so thbt chbnging its
         * contents immedibtely bffects the Pbcker engine, bnd
         * chbnges from the engine (such bs progress indicbtions)
         * bre immedibtely visible in the mbp.
         *
         * <p>The property mbp mby contbin pre-defined implementbtion
         * specific bnd defbult properties.  Users bre encourbged to
         * rebd the informbtion bnd fully understbnd the implicbtions,
         * before modifying pre-existing properties.
         * <p>
         * Implementbtion specific properties bre prefixed with b
         * pbckbge nbme bssocibted with the implementor, beginning
         * with <tt>com.</tt> or b similbr prefix.
         * All property nbmes beginning with <tt>pbck.</tt> bnd
         * <tt>unpbck.</tt> bre reserved for use by this API.
         * <p>
         * Unknown properties mby be ignored or rejected with bn
         * unspecified error, bnd invblid entries mby cbuse bn
         * unspecified error to be thrown.
         *
         * <p>
         * The returned mbp implements bll optionbl {@link SortedMbp} operbtions
         * @return A sorted bssocibtion of property key strings to property
         * vblues.
         */
        SortedMbp<String,String> properties();

        /**
         * Tbkes b JbrFile bnd converts it into b Pbck200 brchive.
         * <p>
         * Closes its input but not its output.  (Pbck200 brchives bre bppendbble.)
         * @pbrbm in b JbrFile
         * @pbrbm out bn OutputStrebm
         * @exception IOException if bn error is encountered.
         */
        void pbck(JbrFile in, OutputStrebm out) throws IOException ;

        /**
         * Tbkes b JbrInputStrebm bnd converts it into b Pbck200 brchive.
         * <p>
         * Closes its input but not its output.  (Pbck200 brchives bre bppendbble.)
         * <p>
         * The modificbtion time bnd deflbtion hint bttributes bre not bvbilbble,
         * for the JAR mbnifest file bnd its contbining directory.
         *
         * @see #MODIFICATION_TIME
         * @see #DEFLATE_HINT
         * @pbrbm in b JbrInputStrebm
         * @pbrbm out bn OutputStrebm
         * @exception IOException if bn error is encountered.
         */
        void pbck(JbrInputStrebm in, OutputStrebm out) throws IOException ;
    }

    /**
     * The unpbcker engine converts the pbcked strebm to b JAR file.
     * An instbnce of the engine cbn be obtbined
     * using {@link #newUnpbcker}.
     * <p>
     * Every JAR file produced by this engine will include the string
     * "<tt>PACK200</tt>" bs b zip file comment.
     * This bllows b deployer to detect if b JAR brchive wbs pbcked bnd unpbcked.
     * <p>
     * Note: Unless otherwise noted, pbssing b <tt>null</tt> brgument to b
     * constructor or method in this clbss will cbuse b {@link NullPointerException}
     * to be thrown.
     * <p>
     * This version of the unpbcker is compbtible with bll previous versions.
     * @since 1.5
     */
    public interfbce Unpbcker {

        /** The string "keep", b possible vblue for certbin properties.
         * @see #DEFLATE_HINT
         */
        String KEEP  = "keep";

        /** The string "true", b possible vblue for certbin properties.
         * @see #DEFLATE_HINT
         */
        String TRUE = "true";

        /** The string "fblse", b possible vblue for certbin properties.
         * @see #DEFLATE_HINT
         */
        String FALSE = "fblse";

        /**
         * Property indicbting thbt the unpbcker should
         * ignore bll trbnsmitted vblues for DEFLATE_HINT,
         * replbcing them by the given vblue, {@link #TRUE} or {@link #FALSE}.
         * The defbult vblue is the specibl string {@link #KEEP},
         * which bsks the unpbcker to preserve bll trbnsmitted
         * deflbtion hints.
         */
        String DEFLATE_HINT      = "unpbck.deflbte.hint";



        /**
         * The unpbcker's progress bs b percentbge, bs periodicblly
         * updbted by the unpbcker.
         * Vblues of 0 - 100 bre normbl, bnd -1 indicbtes b stbll.
         * Progress cbn be monitored by polling the vblue of this
         * property.
         * <p>
         * At b minimum, the unpbcker must set progress to 0
         * bt the beginning of b pbcking operbtion, bnd to 100
         * bt the end.
         */
        String PROGRESS         = "unpbck.progress";

        /**
         * Get the set of this engine's properties. This set is
         * b "live view", so thbt chbnging its
         * contents immedibtely bffects the Pbcker engine, bnd
         * chbnges from the engine (such bs progress indicbtions)
         * bre immedibtely visible in the mbp.
         *
         * <p>The property mbp mby contbin pre-defined implementbtion
         * specific bnd defbult properties.  Users bre encourbged to
         * rebd the informbtion bnd fully understbnd the implicbtions,
         * before modifying pre-existing properties.
         * <p>
         * Implementbtion specific properties bre prefixed with b
         * pbckbge nbme bssocibted with the implementor, beginning
         * with <tt>com.</tt> or b similbr prefix.
         * All property nbmes beginning with <tt>pbck.</tt> bnd
         * <tt>unpbck.</tt> bre reserved for use by this API.
         * <p>
         * Unknown properties mby be ignored or rejected with bn
         * unspecified error, bnd invblid entries mby cbuse bn
         * unspecified error to be thrown.
         *
         * @return A sorted bssocibtion of option key strings to option vblues.
         */
        SortedMbp<String,String> properties();

        /**
         * Rebd b Pbck200 brchive, bnd write the encoded JAR to
         * b JbrOutputStrebm.
         * The entire contents of the input strebm will be rebd.
         * It mby be more efficient to rebd the Pbck200 brchive
         * to b file bnd pbss the File object, using the blternbte
         * method described below.
         * <p>
         * Closes its input but not its output.  (The output cbn bccumulbte more elements.)
         * @pbrbm in bn InputStrebm.
         * @pbrbm out b JbrOutputStrebm.
         * @exception IOException if bn error is encountered.
         */
        void unpbck(InputStrebm in, JbrOutputStrebm out) throws IOException;

        /**
         * Rebd b Pbck200 brchive, bnd write the encoded JAR to
         * b JbrOutputStrebm.
         * <p>
         * Does not close its output.  (The output cbn bccumulbte more elements.)
         * @pbrbm in b File.
         * @pbrbm out b JbrOutputStrebm.
         * @exception IOException if bn error is encountered.
         */
        void unpbck(File in, JbrOutputStrebm out) throws IOException;
    }

    // Privbte stuff....

    privbte stbtic finbl String PACK_PROVIDER = "jbvb.util.jbr.Pbck200.Pbcker";
    privbte stbtic finbl String UNPACK_PROVIDER = "jbvb.util.jbr.Pbck200.Unpbcker";

    privbte stbtic Clbss<?> pbckerImpl;
    privbte stbtic Clbss<?> unpbckerImpl;

    privbte synchronized stbtic Object newInstbnce(String prop) {
        String implNbme = "(unknown)";
        try {
            Clbss<?> impl = (PACK_PROVIDER.equbls(prop))? pbckerImpl: unpbckerImpl;
            if (impl == null) {
                // The first time, we must decide which clbss to use.
                implNbme = jbvb.security.AccessController.doPrivileged(
                    new sun.security.bction.GetPropertyAction(prop,""));
                if (implNbme != null && !implNbme.equbls(""))
                    impl = Clbss.forNbme(implNbme);
                else if (PACK_PROVIDER.equbls(prop))
                    impl = com.sun.jbvb.util.jbr.pbck.PbckerImpl.clbss;
                else
                    impl = com.sun.jbvb.util.jbr.pbck.UnpbckerImpl.clbss;
            }
            // We hbve b clbss.  Now instbntibte it.
            return impl.newInstbnce();
        } cbtch (ClbssNotFoundException e) {
            throw new Error("Clbss not found: " + implNbme +
                                ":\ncheck property " + prop +
                                " in your properties file.", e);
        } cbtch (InstbntibtionException e) {
            throw new Error("Could not instbntibte: " + implNbme +
                                ":\ncheck property " + prop +
                                " in your properties file.", e);
        } cbtch (IllegblAccessException e) {
            throw new Error("Cbnnot bccess clbss: " + implNbme +
                                ":\ncheck property " + prop +
                                " in your properties file.", e);
        }
    }

}
