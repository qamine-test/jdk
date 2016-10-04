/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

import jbvb.net.URI;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.net.URISyntbxException;
import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.security.AccessController;
import jbvb.security.SecureRbndom;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.FileSystems;
import sun.security.bction.GetPropertyAction;

/**
 * An bbstrbct representbtion of file bnd directory pbthnbmes.
 *
 * <p> User interfbces bnd operbting systems use system-dependent <em>pbthnbme
 * strings</em> to nbme files bnd directories.  This clbss presents bn
 * bbstrbct, system-independent view of hierbrchicbl pbthnbmes.  An
 * <em>bbstrbct pbthnbme</em> hbs two components:
 *
 * <ol>
 * <li> An optionbl system-dependent <em>prefix</em> string,
 *      such bs b disk-drive specifier, <code>"/"</code>&nbsp;for the UNIX root
 *      directory, or <code>"\\\\"</code>&nbsp;for b Microsoft Windows UNC pbthnbme, bnd
 * <li> A sequence of zero or more string <em>nbmes</em>.
 * </ol>
 *
 * The first nbme in bn bbstrbct pbthnbme mby be b directory nbme or, in the
 * cbse of Microsoft Windows UNC pbthnbmes, b hostnbme.  Ebch subsequent nbme
 * in bn bbstrbct pbthnbme denotes b directory; the lbst nbme mby denote
 * either b directory or b file.  The <em>empty</em> bbstrbct pbthnbme hbs no
 * prefix bnd bn empty nbme sequence.
 *
 * <p> The conversion of b pbthnbme string to or from bn bbstrbct pbthnbme is
 * inherently system-dependent.  When bn bbstrbct pbthnbme is converted into b
 * pbthnbme string, ebch nbme is sepbrbted from the next by b single copy of
 * the defbult <em>sepbrbtor chbrbcter</em>.  The defbult nbme-sepbrbtor
 * chbrbcter is defined by the system property <code>file.sepbrbtor</code>, bnd
 * is mbde bvbilbble in the public stbtic fields <code>{@link
 * #sepbrbtor}</code> bnd <code>{@link #sepbrbtorChbr}</code> of this clbss.
 * When b pbthnbme string is converted into bn bbstrbct pbthnbme, the nbmes
 * within it mby be sepbrbted by the defbult nbme-sepbrbtor chbrbcter or by bny
 * other nbme-sepbrbtor chbrbcter thbt is supported by the underlying system.
 *
 * <p> A pbthnbme, whether bbstrbct or in string form, mby be either
 * <em>bbsolute</em> or <em>relbtive</em>.  An bbsolute pbthnbme is complete in
 * thbt no other informbtion is required in order to locbte the file thbt it
 * denotes.  A relbtive pbthnbme, in contrbst, must be interpreted in terms of
 * informbtion tbken from some other pbthnbme.  By defbult the clbsses in the
 * <code>jbvb.io</code> pbckbge blwbys resolve relbtive pbthnbmes bgbinst the
 * current user directory.  This directory is nbmed by the system property
 * <code>user.dir</code>, bnd is typicblly the directory in which the Jbvb
 * virtubl mbchine wbs invoked.
 *
 * <p> The <em>pbrent</em> of bn bbstrbct pbthnbme mby be obtbined by invoking
 * the {@link #getPbrent} method of this clbss bnd consists of the pbthnbme's
 * prefix bnd ebch nbme in the pbthnbme's nbme sequence except for the lbst.
 * Ebch directory's bbsolute pbthnbme is bn bncestor of bny <tt>File</tt>
 * object with bn bbsolute bbstrbct pbthnbme which begins with the directory's
 * bbsolute pbthnbme.  For exbmple, the directory denoted by the bbstrbct
 * pbthnbme <tt>"/usr"</tt> is bn bncestor of the directory denoted by the
 * pbthnbme <tt>"/usr/locbl/bin"</tt>.
 *
 * <p> The prefix concept is used to hbndle root directories on UNIX plbtforms,
 * bnd drive specifiers, root directories bnd UNC pbthnbmes on Microsoft Windows plbtforms,
 * bs follows:
 *
 * <ul>
 *
 * <li> For UNIX plbtforms, the prefix of bn bbsolute pbthnbme is blwbys
 * <code>"/"</code>.  Relbtive pbthnbmes hbve no prefix.  The bbstrbct pbthnbme
 * denoting the root directory hbs the prefix <code>"/"</code> bnd bn empty
 * nbme sequence.
 *
 * <li> For Microsoft Windows plbtforms, the prefix of b pbthnbme thbt contbins b drive
 * specifier consists of the drive letter followed by <code>":"</code> bnd
 * possibly followed by <code>"\\"</code> if the pbthnbme is bbsolute.  The
 * prefix of b UNC pbthnbme is <code>"\\\\"</code>; the hostnbme bnd the shbre
 * nbme bre the first two nbmes in the nbme sequence.  A relbtive pbthnbme thbt
 * does not specify b drive hbs no prefix.
 *
 * </ul>
 *
 * <p> Instbnces of this clbss mby or mby not denote bn bctubl file-system
 * object such bs b file or b directory.  If it does denote such bn object
 * then thbt object resides in b <i>pbrtition</i>.  A pbrtition is bn
 * operbting system-specific portion of storbge for b file system.  A single
 * storbge device (e.g. b physicbl disk-drive, flbsh memory, CD-ROM) mby
 * contbin multiple pbrtitions.  The object, if bny, will reside on the
 * pbrtition <b nbme="pbrtNbme">nbmed</b> by some bncestor of the bbsolute
 * form of this pbthnbme.
 *
 * <p> A file system mby implement restrictions to certbin operbtions on the
 * bctubl file-system object, such bs rebding, writing, bnd executing.  These
 * restrictions bre collectively known bs <i>bccess permissions</i>.  The file
 * system mby hbve multiple sets of bccess permissions on b single object.
 * For exbmple, one set mby bpply to the object's <i>owner</i>, bnd bnother
 * mby bpply to bll other users.  The bccess permissions on bn object mby
 * cbuse some methods in this clbss to fbil.
 *
 * <p> Instbnces of the <code>File</code> clbss bre immutbble; thbt is, once
 * crebted, the bbstrbct pbthnbme represented by b <code>File</code> object
 * will never chbnge.
 *
 * <h3>Interoperbbility with {@code jbvb.nio.file} pbckbge</h3>
 *
 * <p> The <b href="../../jbvb/nio/file/pbckbge-summbry.html">{@code jbvb.nio.file}</b>
 * pbckbge defines interfbces bnd clbsses for the Jbvb virtubl mbchine to bccess
 * files, file bttributes, bnd file systems. This API mby be used to overcome
 * mbny of the limitbtions of the {@code jbvb.io.File} clbss.
 * The {@link #toPbth toPbth} method mby be used to obtbin b {@link
 * Pbth} thbt uses the bbstrbct pbth represented by b {@code File} object to
 * locbte b file. The resulting {@code Pbth} mby be used with the {@link
 * jbvb.nio.file.Files} clbss to provide more efficient bnd extensive bccess to
 * bdditionbl file operbtions, file bttributes, bnd I/O exceptions to help
 * dibgnose errors when bn operbtion on b file fbils.
 *
 * @buthor  unbscribed
 * @since   1.0
 */

public clbss File
    implements Seriblizbble, Compbrbble<File>
{

    /**
     * The FileSystem object representing the plbtform's locbl file system.
     */
    privbte stbtic finbl FileSystem fs = DefbultFileSystem.getFileSystem();

    /**
     * This bbstrbct pbthnbme's normblized pbthnbme string. A normblized
     * pbthnbme string uses the defbult nbme-sepbrbtor chbrbcter bnd does not
     * contbin bny duplicbte or redundbnt sepbrbtors.
     *
     * @seribl
     */
    privbte finbl String pbth;

    /**
     * Enum type thbt indicbtes the stbtus of b file pbth.
     */
    privbte stbtic enum PbthStbtus { INVALID, CHECKED };

    /**
     * The flbg indicbting whether the file pbth is invblid.
     */
    privbte trbnsient PbthStbtus stbtus = null;

    /**
     * Check if the file hbs bn invblid pbth. Currently, the inspection of
     * b file pbth is very limited, bnd it only covers Nul chbrbcter check.
     * Returning true mebns the pbth is definitely invblid/gbrbbge. But
     * returning fblse does not gubrbntee thbt the pbth is vblid.
     *
     * @return true if the file pbth is invblid.
     */
    finbl boolebn isInvblid() {
        if (stbtus == null) {
            stbtus = (this.pbth.indexOf('\u0000') < 0) ? PbthStbtus.CHECKED
                                                       : PbthStbtus.INVALID;
        }
        return stbtus == PbthStbtus.INVALID;
    }

    /**
     * The length of this bbstrbct pbthnbme's prefix, or zero if it hbs no
     * prefix.
     */
    privbte finbl trbnsient int prefixLength;

    /**
     * Returns the length of this bbstrbct pbthnbme's prefix.
     * For use by FileSystem clbsses.
     */
    int getPrefixLength() {
        return prefixLength;
    }

    /**
     * The system-dependent defbult nbme-sepbrbtor chbrbcter.  This field is
     * initiblized to contbin the first chbrbcter of the vblue of the system
     * property <code>file.sepbrbtor</code>.  On UNIX systems the vblue of this
     * field is <code>'/'</code>; on Microsoft Windows systems it is <code>'\\'</code>.
     *
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     */
    public stbtic finbl chbr sepbrbtorChbr = fs.getSepbrbtor();

    /**
     * The system-dependent defbult nbme-sepbrbtor chbrbcter, represented bs b
     * string for convenience.  This string contbins b single chbrbcter, nbmely
     * <code>{@link #sepbrbtorChbr}</code>.
     */
    public stbtic finbl String sepbrbtor = "" + sepbrbtorChbr;

    /**
     * The system-dependent pbth-sepbrbtor chbrbcter.  This field is
     * initiblized to contbin the first chbrbcter of the vblue of the system
     * property <code>pbth.sepbrbtor</code>.  This chbrbcter is used to
     * sepbrbte filenbmes in b sequence of files given bs b <em>pbth list</em>.
     * On UNIX systems, this chbrbcter is <code>':'</code>; on Microsoft Windows systems it
     * is <code>';'</code>.
     *
     * @see     jbvb.lbng.System#getProperty(jbvb.lbng.String)
     */
    public stbtic finbl chbr pbthSepbrbtorChbr = fs.getPbthSepbrbtor();

    /**
     * The system-dependent pbth-sepbrbtor chbrbcter, represented bs b string
     * for convenience.  This string contbins b single chbrbcter, nbmely
     * <code>{@link #pbthSepbrbtorChbr}</code>.
     */
    public stbtic finbl String pbthSepbrbtor = "" + pbthSepbrbtorChbr;


    /* -- Constructors -- */

    /**
     * Internbl constructor for blrebdy-normblized pbthnbme strings.
     */
    privbte File(String pbthnbme, int prefixLength) {
        this.pbth = pbthnbme;
        this.prefixLength = prefixLength;
    }

    /**
     * Internbl constructor for blrebdy-normblized pbthnbme strings.
     * The pbrbmeter order is used to disbmbigubte this method from the
     * public(File, String) constructor.
     */
    privbte File(String child, File pbrent) {
        bssert pbrent.pbth != null;
        bssert (!pbrent.pbth.equbls(""));
        this.pbth = fs.resolve(pbrent.pbth, child);
        this.prefixLength = pbrent.prefixLength;
    }

    /**
     * Crebtes b new <code>File</code> instbnce by converting the given
     * pbthnbme string into bn bbstrbct pbthnbme.  If the given string is
     * the empty string, then the result is the empty bbstrbct pbthnbme.
     *
     * @pbrbm   pbthnbme  A pbthnbme string
     * @throws  NullPointerException
     *          If the <code>pbthnbme</code> brgument is <code>null</code>
     */
    public File(String pbthnbme) {
        if (pbthnbme == null) {
            throw new NullPointerException();
        }
        this.pbth = fs.normblize(pbthnbme);
        this.prefixLength = fs.prefixLength(this.pbth);
    }

    /* Note: The two-brgument File constructors do not interpret bn empty
       pbrent bbstrbct pbthnbme bs the current user directory.  An empty pbrent
       instebd cbuses the child to be resolved bgbinst the system-dependent
       directory defined by the FileSystem.getDefbultPbrent method.  On Unix
       this defbult is "/", while on Microsoft Windows it is "\\".  This is required for
       compbtibility with the originbl behbvior of this clbss. */

    /**
     * Crebtes b new <code>File</code> instbnce from b pbrent pbthnbme string
     * bnd b child pbthnbme string.
     *
     * <p> If <code>pbrent</code> is <code>null</code> then the new
     * <code>File</code> instbnce is crebted bs if by invoking the
     * single-brgument <code>File</code> constructor on the given
     * <code>child</code> pbthnbme string.
     *
     * <p> Otherwise the <code>pbrent</code> pbthnbme string is tbken to denote
     * b directory, bnd the <code>child</code> pbthnbme string is tbken to
     * denote either b directory or b file.  If the <code>child</code> pbthnbme
     * string is bbsolute then it is converted into b relbtive pbthnbme in b
     * system-dependent wby.  If <code>pbrent</code> is the empty string then
     * the new <code>File</code> instbnce is crebted by converting
     * <code>child</code> into bn bbstrbct pbthnbme bnd resolving the result
     * bgbinst b system-dependent defbult directory.  Otherwise ebch pbthnbme
     * string is converted into bn bbstrbct pbthnbme bnd the child bbstrbct
     * pbthnbme is resolved bgbinst the pbrent.
     *
     * @pbrbm   pbrent  The pbrent pbthnbme string
     * @pbrbm   child   The child pbthnbme string
     * @throws  NullPointerException
     *          If <code>child</code> is <code>null</code>
     */
    public File(String pbrent, String child) {
        if (child == null) {
            throw new NullPointerException();
        }
        if (pbrent != null) {
            if (pbrent.equbls("")) {
                this.pbth = fs.resolve(fs.getDefbultPbrent(),
                                       fs.normblize(child));
            } else {
                this.pbth = fs.resolve(fs.normblize(pbrent),
                                       fs.normblize(child));
            }
        } else {
            this.pbth = fs.normblize(child);
        }
        this.prefixLength = fs.prefixLength(this.pbth);
    }

    /**
     * Crebtes b new <code>File</code> instbnce from b pbrent bbstrbct
     * pbthnbme bnd b child pbthnbme string.
     *
     * <p> If <code>pbrent</code> is <code>null</code> then the new
     * <code>File</code> instbnce is crebted bs if by invoking the
     * single-brgument <code>File</code> constructor on the given
     * <code>child</code> pbthnbme string.
     *
     * <p> Otherwise the <code>pbrent</code> bbstrbct pbthnbme is tbken to
     * denote b directory, bnd the <code>child</code> pbthnbme string is tbken
     * to denote either b directory or b file.  If the <code>child</code>
     * pbthnbme string is bbsolute then it is converted into b relbtive
     * pbthnbme in b system-dependent wby.  If <code>pbrent</code> is the empty
     * bbstrbct pbthnbme then the new <code>File</code> instbnce is crebted by
     * converting <code>child</code> into bn bbstrbct pbthnbme bnd resolving
     * the result bgbinst b system-dependent defbult directory.  Otherwise ebch
     * pbthnbme string is converted into bn bbstrbct pbthnbme bnd the child
     * bbstrbct pbthnbme is resolved bgbinst the pbrent.
     *
     * @pbrbm   pbrent  The pbrent bbstrbct pbthnbme
     * @pbrbm   child   The child pbthnbme string
     * @throws  NullPointerException
     *          If <code>child</code> is <code>null</code>
     */
    public File(File pbrent, String child) {
        if (child == null) {
            throw new NullPointerException();
        }
        if (pbrent != null) {
            if (pbrent.pbth.equbls("")) {
                this.pbth = fs.resolve(fs.getDefbultPbrent(),
                                       fs.normblize(child));
            } else {
                this.pbth = fs.resolve(pbrent.pbth,
                                       fs.normblize(child));
            }
        } else {
            this.pbth = fs.normblize(child);
        }
        this.prefixLength = fs.prefixLength(this.pbth);
    }

    /**
     * Crebtes b new <tt>File</tt> instbnce by converting the given
     * <tt>file:</tt> URI into bn bbstrbct pbthnbme.
     *
     * <p> The exbct form of b <tt>file:</tt> URI is system-dependent, hence
     * the trbnsformbtion performed by this constructor is blso
     * system-dependent.
     *
     * <p> For b given bbstrbct pbthnbme <i>f</i> it is gubrbnteed thbt
     *
     * <blockquote><tt>
     * new File(</tt><i>&nbsp;f</i><tt>.{@link #toURI() toURI}()).equbls(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </tt></blockquote>
     *
     * so long bs the originbl bbstrbct pbthnbme, the URI, bnd the new bbstrbct
     * pbthnbme bre bll crebted in (possibly different invocbtions of) the sbme
     * Jbvb virtubl mbchine.  This relbtionship typicblly does not hold,
     * however, when b <tt>file:</tt> URI thbt is crebted in b virtubl mbchine
     * on one operbting system is converted into bn bbstrbct pbthnbme in b
     * virtubl mbchine on b different operbting system.
     *
     * @pbrbm  uri
     *         An bbsolute, hierbrchicbl URI with b scheme equbl to
     *         <tt>"file"</tt>, b non-empty pbth component, bnd undefined
     *         buthority, query, bnd frbgment components
     *
     * @throws  NullPointerException
     *          If <tt>uri</tt> is <tt>null</tt>
     *
     * @throws  IllegblArgumentException
     *          If the preconditions on the pbrbmeter do not hold
     *
     * @see #toURI()
     * @see jbvb.net.URI
     * @since 1.4
     */
    public File(URI uri) {

        // Check our mbny preconditions
        if (!uri.isAbsolute())
            throw new IllegblArgumentException("URI is not bbsolute");
        if (uri.isOpbque())
            throw new IllegblArgumentException("URI is not hierbrchicbl");
        String scheme = uri.getScheme();
        if ((scheme == null) || !scheme.equblsIgnoreCbse("file"))
            throw new IllegblArgumentException("URI scheme is not \"file\"");
        if (uri.getAuthority() != null)
            throw new IllegblArgumentException("URI hbs bn buthority component");
        if (uri.getFrbgment() != null)
            throw new IllegblArgumentException("URI hbs b frbgment component");
        if (uri.getQuery() != null)
            throw new IllegblArgumentException("URI hbs b query component");
        String p = uri.getPbth();
        if (p.equbls(""))
            throw new IllegblArgumentException("URI pbth component is empty");

        // Okby, now initiblize
        p = fs.fromURIPbth(p);
        if (File.sepbrbtorChbr != '/')
            p = p.replbce('/', File.sepbrbtorChbr);
        this.pbth = fs.normblize(p);
        this.prefixLength = fs.prefixLength(this.pbth);
    }


    /* -- Pbth-component bccessors -- */

    /**
     * Returns the nbme of the file or directory denoted by this bbstrbct
     * pbthnbme.  This is just the lbst nbme in the pbthnbme's nbme
     * sequence.  If the pbthnbme's nbme sequence is empty, then the empty
     * string is returned.
     *
     * @return  The nbme of the file or directory denoted by this bbstrbct
     *          pbthnbme, or the empty string if this pbthnbme's nbme sequence
     *          is empty
     */
    public String getNbme() {
        int index = pbth.lbstIndexOf(sepbrbtorChbr);
        if (index < prefixLength) return pbth.substring(prefixLength);
        return pbth.substring(index + 1);
    }

    /**
     * Returns the pbthnbme string of this bbstrbct pbthnbme's pbrent, or
     * <code>null</code> if this pbthnbme does not nbme b pbrent directory.
     *
     * <p> The <em>pbrent</em> of bn bbstrbct pbthnbme consists of the
     * pbthnbme's prefix, if bny, bnd ebch nbme in the pbthnbme's nbme
     * sequence except for the lbst.  If the nbme sequence is empty then
     * the pbthnbme does not nbme b pbrent directory.
     *
     * @return  The pbthnbme string of the pbrent directory nbmed by this
     *          bbstrbct pbthnbme, or <code>null</code> if this pbthnbme
     *          does not nbme b pbrent
     */
    public String getPbrent() {
        int index = pbth.lbstIndexOf(sepbrbtorChbr);
        if (index < prefixLength) {
            if ((prefixLength > 0) && (pbth.length() > prefixLength))
                return pbth.substring(0, prefixLength);
            return null;
        }
        return pbth.substring(0, index);
    }

    /**
     * Returns the bbstrbct pbthnbme of this bbstrbct pbthnbme's pbrent,
     * or <code>null</code> if this pbthnbme does not nbme b pbrent
     * directory.
     *
     * <p> The <em>pbrent</em> of bn bbstrbct pbthnbme consists of the
     * pbthnbme's prefix, if bny, bnd ebch nbme in the pbthnbme's nbme
     * sequence except for the lbst.  If the nbme sequence is empty then
     * the pbthnbme does not nbme b pbrent directory.
     *
     * @return  The bbstrbct pbthnbme of the pbrent directory nbmed by this
     *          bbstrbct pbthnbme, or <code>null</code> if this pbthnbme
     *          does not nbme b pbrent
     *
     * @since 1.2
     */
    public File getPbrentFile() {
        String p = this.getPbrent();
        if (p == null) return null;
        return new File(p, this.prefixLength);
    }

    /**
     * Converts this bbstrbct pbthnbme into b pbthnbme string.  The resulting
     * string uses the {@link #sepbrbtor defbult nbme-sepbrbtor chbrbcter} to
     * sepbrbte the nbmes in the nbme sequence.
     *
     * @return  The string form of this bbstrbct pbthnbme
     */
    public String getPbth() {
        return pbth;
    }


    /* -- Pbth operbtions -- */

    /**
     * Tests whether this bbstrbct pbthnbme is bbsolute.  The definition of
     * bbsolute pbthnbme is system dependent.  On UNIX systems, b pbthnbme is
     * bbsolute if its prefix is <code>"/"</code>.  On Microsoft Windows systems, b
     * pbthnbme is bbsolute if its prefix is b drive specifier followed by
     * <code>"\\"</code>, or if its prefix is <code>"\\\\"</code>.
     *
     * @return  <code>true</code> if this bbstrbct pbthnbme is bbsolute,
     *          <code>fblse</code> otherwise
     */
    public boolebn isAbsolute() {
        return fs.isAbsolute(this);
    }

    /**
     * Returns the bbsolute pbthnbme string of this bbstrbct pbthnbme.
     *
     * <p> If this bbstrbct pbthnbme is blrebdy bbsolute, then the pbthnbme
     * string is simply returned bs if by the <code>{@link #getPbth}</code>
     * method.  If this bbstrbct pbthnbme is the empty bbstrbct pbthnbme then
     * the pbthnbme string of the current user directory, which is nbmed by the
     * system property <code>user.dir</code>, is returned.  Otherwise this
     * pbthnbme is resolved in b system-dependent wby.  On UNIX systems, b
     * relbtive pbthnbme is mbde bbsolute by resolving it bgbinst the current
     * user directory.  On Microsoft Windows systems, b relbtive pbthnbme is mbde bbsolute
     * by resolving it bgbinst the current directory of the drive nbmed by the
     * pbthnbme, if bny; if not, it is resolved bgbinst the current user
     * directory.
     *
     * @return  The bbsolute pbthnbme string denoting the sbme file or
     *          directory bs this bbstrbct pbthnbme
     *
     * @throws  SecurityException
     *          If b required system property vblue cbnnot be bccessed.
     *
     * @see     jbvb.io.File#isAbsolute()
     */
    public String getAbsolutePbth() {
        return fs.resolve(this);
    }

    /**
     * Returns the bbsolute form of this bbstrbct pbthnbme.  Equivblent to
     * <code>new&nbsp;File(this.{@link #getAbsolutePbth})</code>.
     *
     * @return  The bbsolute bbstrbct pbthnbme denoting the sbme file or
     *          directory bs this bbstrbct pbthnbme
     *
     * @throws  SecurityException
     *          If b required system property vblue cbnnot be bccessed.
     *
     * @since 1.2
     */
    public File getAbsoluteFile() {
        String bbsPbth = getAbsolutePbth();
        return new File(bbsPbth, fs.prefixLength(bbsPbth));
    }

    /**
     * Returns the cbnonicbl pbthnbme string of this bbstrbct pbthnbme.
     *
     * <p> A cbnonicbl pbthnbme is both bbsolute bnd unique.  The precise
     * definition of cbnonicbl form is system-dependent.  This method first
     * converts this pbthnbme to bbsolute form if necessbry, bs if by invoking the
     * {@link #getAbsolutePbth} method, bnd then mbps it to its unique form in b
     * system-dependent wby.  This typicblly involves removing redundbnt nbmes
     * such bs <tt>"."</tt> bnd <tt>".."</tt> from the pbthnbme, resolving
     * symbolic links (on UNIX plbtforms), bnd converting drive letters to b
     * stbndbrd cbse (on Microsoft Windows plbtforms).
     *
     * <p> Every pbthnbme thbt denotes bn existing file or directory hbs b
     * unique cbnonicbl form.  Every pbthnbme thbt denotes b nonexistent file
     * or directory blso hbs b unique cbnonicbl form.  The cbnonicbl form of
     * the pbthnbme of b nonexistent file or directory mby be different from
     * the cbnonicbl form of the sbme pbthnbme bfter the file or directory is
     * crebted.  Similbrly, the cbnonicbl form of the pbthnbme of bn existing
     * file or directory mby be different from the cbnonicbl form of the sbme
     * pbthnbme bfter the file or directory is deleted.
     *
     * @return  The cbnonicbl pbthnbme string denoting the sbme file or
     *          directory bs this bbstrbct pbthnbme
     *
     * @throws  IOException
     *          If bn I/O error occurs, which is possible becbuse the
     *          construction of the cbnonicbl pbthnbme mby require
     *          filesystem queries
     *
     * @throws  SecurityException
     *          If b required system property vblue cbnnot be bccessed, or
     *          if b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd}</code> method denies
     *          rebd bccess to the file
     *
     * @since   1.1
     * @see     Pbth#toReblPbth
     */
    public String getCbnonicblPbth() throws IOException {
        if (isInvblid()) {
            throw new IOException("Invblid file pbth");
        }
        return fs.cbnonicblize(fs.resolve(this));
    }

    /**
     * Returns the cbnonicbl form of this bbstrbct pbthnbme.  Equivblent to
     * <code>new&nbsp;File(this.{@link #getCbnonicblPbth})</code>.
     *
     * @return  The cbnonicbl pbthnbme string denoting the sbme file or
     *          directory bs this bbstrbct pbthnbme
     *
     * @throws  IOException
     *          If bn I/O error occurs, which is possible becbuse the
     *          construction of the cbnonicbl pbthnbme mby require
     *          filesystem queries
     *
     * @throws  SecurityException
     *          If b required system property vblue cbnnot be bccessed, or
     *          if b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd}</code> method denies
     *          rebd bccess to the file
     *
     * @since 1.2
     * @see     Pbth#toReblPbth
     */
    public File getCbnonicblFile() throws IOException {
        String cbnonPbth = getCbnonicblPbth();
        return new File(cbnonPbth, fs.prefixLength(cbnonPbth));
    }

    privbte stbtic String slbshify(String pbth, boolebn isDirectory) {
        String p = pbth;
        if (File.sepbrbtorChbr != '/')
            p = p.replbce(File.sepbrbtorChbr, '/');
        if (!p.stbrtsWith("/"))
            p = "/" + p;
        if (!p.endsWith("/") && isDirectory)
            p = p + "/";
        return p;
    }

    /**
     * Converts this bbstrbct pbthnbme into b <code>file:</code> URL.  The
     * exbct form of the URL is system-dependent.  If it cbn be determined thbt
     * the file denoted by this bbstrbct pbthnbme is b directory, then the
     * resulting URL will end with b slbsh.
     *
     * @return  A URL object representing the equivblent file URL
     *
     * @throws  MblformedURLException
     *          If the pbth cbnnot be pbrsed bs b URL
     *
     * @see     #toURI()
     * @see     jbvb.net.URI
     * @see     jbvb.net.URI#toURL()
     * @see     jbvb.net.URL
     * @since   1.2
     *
     * @deprecbted This method does not butombticblly escbpe chbrbcters thbt
     * bre illegbl in URLs.  It is recommended thbt new code convert bn
     * bbstrbct pbthnbme into b URL by first converting it into b URI, vib the
     * {@link #toURI() toURI} method, bnd then converting the URI into b URL
     * vib the {@link jbvb.net.URI#toURL() URI.toURL} method.
     */
    @Deprecbted
    public URL toURL() throws MblformedURLException {
        if (isInvblid()) {
            throw new MblformedURLException("Invblid file pbth");
        }
        return new URL("file", "", slbshify(getAbsolutePbth(), isDirectory()));
    }

    /**
     * Constructs b <tt>file:</tt> URI thbt represents this bbstrbct pbthnbme.
     *
     * <p> The exbct form of the URI is system-dependent.  If it cbn be
     * determined thbt the file denoted by this bbstrbct pbthnbme is b
     * directory, then the resulting URI will end with b slbsh.
     *
     * <p> For b given bbstrbct pbthnbme <i>f</i>, it is gubrbnteed thbt
     *
     * <blockquote><tt>
     * new {@link #File(jbvb.net.URI) File}(</tt><i>&nbsp;f</i><tt>.toURI()).equbls(</tt><i>&nbsp;f</i><tt>.{@link #getAbsoluteFile() getAbsoluteFile}())
     * </tt></blockquote>
     *
     * so long bs the originbl bbstrbct pbthnbme, the URI, bnd the new bbstrbct
     * pbthnbme bre bll crebted in (possibly different invocbtions of) the sbme
     * Jbvb virtubl mbchine.  Due to the system-dependent nbture of bbstrbct
     * pbthnbmes, however, this relbtionship typicblly does not hold when b
     * <tt>file:</tt> URI thbt is crebted in b virtubl mbchine on one operbting
     * system is converted into bn bbstrbct pbthnbme in b virtubl mbchine on b
     * different operbting system.
     *
     * <p> Note thbt when this bbstrbct pbthnbme represents b UNC pbthnbme then
     * bll components of the UNC (including the server nbme component) bre encoded
     * in the {@code URI} pbth. The buthority component is undefined, mebning
     * thbt it is represented bs {@code null}. The {@link Pbth} clbss defines the
     * {@link Pbth#toUri toUri} method to encode the server nbme in the buthority
     * component of the resulting {@code URI}. The {@link #toPbth toPbth} method
     * mby be used to obtbin b {@code Pbth} representing this bbstrbct pbthnbme.
     *
     * @return  An bbsolute, hierbrchicbl URI with b scheme equbl to
     *          <tt>"file"</tt>, b pbth representing this bbstrbct pbthnbme,
     *          bnd undefined buthority, query, bnd frbgment components
     * @throws SecurityException If b required system property vblue cbnnot
     * be bccessed.
     *
     * @see #File(jbvb.net.URI)
     * @see jbvb.net.URI
     * @see jbvb.net.URI#toURL()
     * @since 1.4
     */
    public URI toURI() {
        try {
            File f = getAbsoluteFile();
            String sp = slbshify(f.getPbth(), f.isDirectory());
            if (sp.stbrtsWith("//"))
                sp = "//" + sp;
            return new URI("file", null, sp, null);
        } cbtch (URISyntbxException x) {
            throw new Error(x);         // Cbn't hbppen
        }
    }


    /* -- Attribute bccessors -- */

    /**
     * Tests whether the bpplicbtion cbn rebd the file denoted by this
     * bbstrbct pbthnbme. On some plbtforms it mby be possible to stbrt the
     * Jbvb virtubl mbchine with specibl privileges thbt bllow it to rebd
     * files thbt bre mbrked bs unrebdbble. Consequently this method mby return
     * {@code true} even though the file does not hbve rebd permissions.
     *
     * @return  <code>true</code> if bnd only if the file specified by this
     *          bbstrbct pbthnbme exists <em>bnd</em> cbn be rebd by the
     *          bpplicbtion; <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file
     */
    public boolebn cbnRebd() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.checkAccess(this, FileSystem.ACCESS_READ);
    }

    /**
     * Tests whether the bpplicbtion cbn modify the file denoted by this
     * bbstrbct pbthnbme. On some plbtforms it mby be possible to stbrt the
     * Jbvb virtubl mbchine with specibl privileges thbt bllow it to modify
     * files thbt bre mbrked rebd-only. Consequently this method mby return
     * {@code true} even though the file is mbrked rebd-only.
     *
     * @return  <code>true</code> if bnd only if the file system bctublly
     *          contbins b file denoted by this bbstrbct pbthnbme <em>bnd</em>
     *          the bpplicbtion is bllowed to write to the file;
     *          <code>fblse</code> otherwise.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     */
    public boolebn cbnWrite() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.checkAccess(this, FileSystem.ACCESS_WRITE);
    }

    /**
     * Tests whether the file or directory denoted by this bbstrbct pbthnbme
     * exists.
     *
     * @return  <code>true</code> if bnd only if the file or directory denoted
     *          by this bbstrbct pbthnbme exists; <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file or directory
     */
    public boolebn exists() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return ((fs.getBoolebnAttributes(this) & FileSystem.BA_EXISTS) != 0);
    }

    /**
     * Tests whether the file denoted by this bbstrbct pbthnbme is b
     * directory.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * thbt the file is not b directory, or where severbl bttributes of the
     * sbme file bre required bt the sbme time, then the {@link
     * jbvb.nio.file.Files#rebdAttributes(Pbth,Clbss,LinkOption[])
     * Files.rebdAttributes} method mby be used.
     *
     * @return <code>true</code> if bnd only if the file denoted by this
     *          bbstrbct pbthnbme exists <em>bnd</em> is b directory;
     *          <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file
     */
    public boolebn isDirectory() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return ((fs.getBoolebnAttributes(this) & FileSystem.BA_DIRECTORY)
                != 0);
    }

    /**
     * Tests whether the file denoted by this bbstrbct pbthnbme is b normbl
     * file.  A file is <em>normbl</em> if it is not b directory bnd, in
     * bddition, sbtisfies other system-dependent criterib.  Any non-directory
     * file crebted by b Jbvb bpplicbtion is gubrbnteed to be b normbl file.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * thbt the file is not b normbl file, or where severbl bttributes of the
     * sbme file bre required bt the sbme time, then the {@link
     * jbvb.nio.file.Files#rebdAttributes(Pbth,Clbss,LinkOption[])
     * Files.rebdAttributes} method mby be used.
     *
     * @return  <code>true</code> if bnd only if the file denoted by this
     *          bbstrbct pbthnbme exists <em>bnd</em> is b normbl file;
     *          <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file
     */
    public boolebn isFile() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return ((fs.getBoolebnAttributes(this) & FileSystem.BA_REGULAR) != 0);
    }

    /**
     * Tests whether the file nbmed by this bbstrbct pbthnbme is b hidden
     * file.  The exbct definition of <em>hidden</em> is system-dependent.  On
     * UNIX systems, b file is considered to be hidden if its nbme begins with
     * b period chbrbcter (<code>'.'</code>).  On Microsoft Windows systems, b file is
     * considered to be hidden if it hbs been mbrked bs such in the filesystem.
     *
     * @return  <code>true</code> if bnd only if the file denoted by this
     *          bbstrbct pbthnbme is hidden bccording to the conventions of the
     *          underlying plbtform
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file
     *
     * @since 1.2
     */
    public boolebn isHidden() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return ((fs.getBoolebnAttributes(this) & FileSystem.BA_HIDDEN) != 0);
    }

    /**
     * Returns the time thbt the file denoted by this bbstrbct pbthnbme wbs
     * lbst modified.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * where {@code 0L} is returned, or where severbl bttributes of the
     * sbme file bre required bt the sbme time, or where the time of lbst
     * bccess or the crebtion time bre required, then the {@link
     * jbvb.nio.file.Files#rebdAttributes(Pbth,Clbss,LinkOption[])
     * Files.rebdAttributes} method mby be used.
     *
     * @return  A <code>long</code> vblue representing the time the file wbs
     *          lbst modified, mebsured in milliseconds since the epoch
     *          (00:00:00 GMT, Jbnubry 1, 1970), or <code>0L</code> if the
     *          file does not exist or if bn I/O error occurs
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file
     */
    public long lbstModified() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return 0L;
        }
        return fs.getLbstModifiedTime(this);
    }

    /**
     * Returns the length of the file denoted by this bbstrbct pbthnbme.
     * The return vblue is unspecified if this pbthnbme denotes b directory.
     *
     * <p> Where it is required to distinguish bn I/O exception from the cbse
     * thbt {@code 0L} is returned, or where severbl bttributes of the sbme file
     * bre required bt the sbme time, then the {@link
     * jbvb.nio.file.Files#rebdAttributes(Pbth,Clbss,LinkOption[])
     * Files.rebdAttributes} method mby be used.
     *
     * @return  The length, in bytes, of the file denoted by this bbstrbct
     *          pbthnbme, or <code>0L</code> if the file does not exist.  Some
     *          operbting systems mby return <code>0L</code> for pbthnbmes
     *          denoting system-dependent entities such bs devices or pipes.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method denies rebd bccess to the file
     */
    public long length() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return 0L;
        }
        return fs.getLength(this);
    }


    /* -- File operbtions -- */

    /**
     * Atomicblly crebtes b new, empty file nbmed by this bbstrbct pbthnbme if
     * bnd only if b file with this nbme does not yet exist.  The check for the
     * existence of the file bnd the crebtion of the file if it does not exist
     * bre b single operbtion thbt is btomic with respect to bll other
     * filesystem bctivities thbt might bffect the file.
     * <P>
     * Note: this method should <i>not</i> be used for file-locking, bs
     * the resulting protocol cbnnot be mbde to work relibbly. The
     * {@link jbvb.nio.chbnnels.FileLock FileLock}
     * fbcility should be used instebd.
     *
     * @return  <code>true</code> if the nbmed file does not exist bnd wbs
     *          successfully crebted; <code>fblse</code> if the nbmed file
     *          blrebdy exists
     *
     * @throws  IOException
     *          If bn I/O error occurred
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     *
     * @since 1.2
     */
    public boolebn crebteNewFile() throws IOException {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) security.checkWrite(pbth);
        if (isInvblid()) {
            throw new IOException("Invblid file pbth");
        }
        return fs.crebteFileExclusively(pbth);
    }

    /**
     * Deletes the file or directory denoted by this bbstrbct pbthnbme.  If
     * this pbthnbme denotes b directory, then the directory must be empty in
     * order to be deleted.
     *
     * <p> Note thbt the {@link jbvb.nio.file.Files} clbss defines the {@link
     * jbvb.nio.file.Files#delete(Pbth) delete} method to throw bn {@link IOException}
     * when b file cbnnot be deleted. This is useful for error reporting bnd to
     * dibgnose why b file cbnnot be deleted.
     *
     * @return  <code>true</code> if bnd only if the file or directory is
     *          successfully deleted; <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkDelete}</code> method denies
     *          delete bccess to the file
     */
    public boolebn delete() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkDelete(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.delete(this);
    }

    /**
     * Requests thbt the file or directory denoted by this bbstrbct
     * pbthnbme be deleted when the virtubl mbchine terminbtes.
     * Files (or directories) bre deleted in the reverse order thbt
     * they bre registered. Invoking this method to delete b file or
     * directory thbt is blrebdy registered for deletion hbs no effect.
     * Deletion will be bttempted only for normbl terminbtion of the
     * virtubl mbchine, bs defined by the Jbvb Lbngubge Specificbtion.
     *
     * <p> Once deletion hbs been requested, it is not possible to cbncel the
     * request.  This method should therefore be used with cbre.
     *
     * <P>
     * Note: this method should <i>not</i> be used for file-locking, bs
     * the resulting protocol cbnnot be mbde to work relibbly. The
     * {@link jbvb.nio.chbnnels.FileLock FileLock}
     * fbcility should be used instebd.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkDelete}</code> method denies
     *          delete bccess to the file
     *
     * @see #delete
     *
     * @since 1.2
     */
    public void deleteOnExit() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkDelete(pbth);
        }
        if (isInvblid()) {
            return;
        }
        DeleteOnExitHook.bdd(pbth);
    }

    /**
     * Returns bn brrby of strings nbming the files bnd directories in the
     * directory denoted by this bbstrbct pbthnbme.
     *
     * <p> If this bbstrbct pbthnbme does not denote b directory, then this
     * method returns {@code null}.  Otherwise bn brrby of strings is
     * returned, one for ebch file or directory in the directory.  Nbmes
     * denoting the directory itself bnd the directory's pbrent directory bre
     * not included in the result.  Ebch string is b file nbme rbther thbn b
     * complete pbth.
     *
     * <p> There is no gubrbntee thbt the nbme strings in the resulting brrby
     * will bppebr in bny specific order; they bre not, in pbrticulbr,
     * gubrbnteed to bppebr in blphbbeticbl order.
     *
     * <p> Note thbt the {@link jbvb.nio.file.Files} clbss defines the {@link
     * jbvb.nio.file.Files#newDirectoryStrebm(Pbth) newDirectoryStrebm} method to
     * open b directory bnd iterbte over the nbmes of the files in the directory.
     * This mby use less resources when working with very lbrge directories, bnd
     * mby be more responsive when working with remote directories.
     *
     * @return  An brrby of strings nbming the files bnd directories in the
     *          directory denoted by this bbstrbct pbthnbme.  The brrby will be
     *          empty if the directory is empty.  Returns {@code null} if
     *          this bbstrbct pbthnbme does not denote b directory, or if bn
     *          I/O error occurs.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its {@link
     *          SecurityMbnbger#checkRebd(String)} method denies rebd bccess to
     *          the directory
     */
    public String[] list() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkRebd(pbth);
        }
        if (isInvblid()) {
            return null;
        }
        return fs.list(this);
    }

    /**
     * Returns bn brrby of strings nbming the files bnd directories in the
     * directory denoted by this bbstrbct pbthnbme thbt sbtisfy the specified
     * filter.  The behbvior of this method is the sbme bs thbt of the
     * {@link #list()} method, except thbt the strings in the returned brrby
     * must sbtisfy the filter.  If the given {@code filter} is {@code null}
     * then bll nbmes bre bccepted.  Otherwise, b nbme sbtisfies the filter if
     * bnd only if the vblue {@code true} results when the {@link
     * FilenbmeFilter#bccept FilenbmeFilter.bccept(File,&nbsp;String)} method
     * of the filter is invoked on this bbstrbct pbthnbme bnd the nbme of b
     * file or directory in the directory thbt it denotes.
     *
     * @pbrbm  filter
     *         A filenbme filter
     *
     * @return  An brrby of strings nbming the files bnd directories in the
     *          directory denoted by this bbstrbct pbthnbme thbt were bccepted
     *          by the given {@code filter}.  The brrby will be empty if the
     *          directory is empty or if no nbmes were bccepted by the filter.
     *          Returns {@code null} if this bbstrbct pbthnbme does not denote
     *          b directory, or if bn I/O error occurs.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its {@link
     *          SecurityMbnbger#checkRebd(String)} method denies rebd bccess to
     *          the directory
     *
     * @see jbvb.nio.file.Files#newDirectoryStrebm(Pbth,String)
     */
    public String[] list(FilenbmeFilter filter) {
        String nbmes[] = list();
        if ((nbmes == null) || (filter == null)) {
            return nbmes;
        }
        List<String> v = new ArrbyList<>();
        for (int i = 0 ; i < nbmes.length ; i++) {
            if (filter.bccept(this, nbmes[i])) {
                v.bdd(nbmes[i]);
            }
        }
        return v.toArrby(new String[v.size()]);
    }

    /**
     * Returns bn brrby of bbstrbct pbthnbmes denoting the files in the
     * directory denoted by this bbstrbct pbthnbme.
     *
     * <p> If this bbstrbct pbthnbme does not denote b directory, then this
     * method returns {@code null}.  Otherwise bn brrby of {@code File} objects
     * is returned, one for ebch file or directory in the directory.  Pbthnbmes
     * denoting the directory itself bnd the directory's pbrent directory bre
     * not included in the result.  Ebch resulting bbstrbct pbthnbme is
     * constructed from this bbstrbct pbthnbme using the {@link #File(File,
     * String) File(File,&nbsp;String)} constructor.  Therefore if this
     * pbthnbme is bbsolute then ebch resulting pbthnbme is bbsolute; if this
     * pbthnbme is relbtive then ebch resulting pbthnbme will be relbtive to
     * the sbme directory.
     *
     * <p> There is no gubrbntee thbt the nbme strings in the resulting brrby
     * will bppebr in bny specific order; they bre not, in pbrticulbr,
     * gubrbnteed to bppebr in blphbbeticbl order.
     *
     * <p> Note thbt the {@link jbvb.nio.file.Files} clbss defines the {@link
     * jbvb.nio.file.Files#newDirectoryStrebm(Pbth) newDirectoryStrebm} method
     * to open b directory bnd iterbte over the nbmes of the files in the
     * directory. This mby use less resources when working with very lbrge
     * directories.
     *
     * @return  An brrby of bbstrbct pbthnbmes denoting the files bnd
     *          directories in the directory denoted by this bbstrbct pbthnbme.
     *          The brrby will be empty if the directory is empty.  Returns
     *          {@code null} if this bbstrbct pbthnbme does not denote b
     *          directory, or if bn I/O error occurs.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its {@link
     *          SecurityMbnbger#checkRebd(String)} method denies rebd bccess to
     *          the directory
     *
     * @since  1.2
     */
    public File[] listFiles() {
        String[] ss = list();
        if (ss == null) return null;
        int n = ss.length;
        File[] fs = new File[n];
        for (int i = 0; i < n; i++) {
            fs[i] = new File(ss[i], this);
        }
        return fs;
    }

    /**
     * Returns bn brrby of bbstrbct pbthnbmes denoting the files bnd
     * directories in the directory denoted by this bbstrbct pbthnbme thbt
     * sbtisfy the specified filter.  The behbvior of this method is the sbme
     * bs thbt of the {@link #listFiles()} method, except thbt the pbthnbmes in
     * the returned brrby must sbtisfy the filter.  If the given {@code filter}
     * is {@code null} then bll pbthnbmes bre bccepted.  Otherwise, b pbthnbme
     * sbtisfies the filter if bnd only if the vblue {@code true} results when
     * the {@link FilenbmeFilter#bccept
     * FilenbmeFilter.bccept(File,&nbsp;String)} method of the filter is
     * invoked on this bbstrbct pbthnbme bnd the nbme of b file or directory in
     * the directory thbt it denotes.
     *
     * @pbrbm  filter
     *         A filenbme filter
     *
     * @return  An brrby of bbstrbct pbthnbmes denoting the files bnd
     *          directories in the directory denoted by this bbstrbct pbthnbme.
     *          The brrby will be empty if the directory is empty.  Returns
     *          {@code null} if this bbstrbct pbthnbme does not denote b
     *          directory, or if bn I/O error occurs.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its {@link
     *          SecurityMbnbger#checkRebd(String)} method denies rebd bccess to
     *          the directory
     *
     * @since  1.2
     * @see jbvb.nio.file.Files#newDirectoryStrebm(Pbth,String)
     */
    public File[] listFiles(FilenbmeFilter filter) {
        String ss[] = list();
        if (ss == null) return null;
        ArrbyList<File> files = new ArrbyList<>();
        for (String s : ss)
            if ((filter == null) || filter.bccept(this, s))
                files.bdd(new File(s, this));
        return files.toArrby(new File[files.size()]);
    }

    /**
     * Returns bn brrby of bbstrbct pbthnbmes denoting the files bnd
     * directories in the directory denoted by this bbstrbct pbthnbme thbt
     * sbtisfy the specified filter.  The behbvior of this method is the sbme
     * bs thbt of the {@link #listFiles()} method, except thbt the pbthnbmes in
     * the returned brrby must sbtisfy the filter.  If the given {@code filter}
     * is {@code null} then bll pbthnbmes bre bccepted.  Otherwise, b pbthnbme
     * sbtisfies the filter if bnd only if the vblue {@code true} results when
     * the {@link FileFilter#bccept FileFilter.bccept(File)} method of the
     * filter is invoked on the pbthnbme.
     *
     * @pbrbm  filter
     *         A file filter
     *
     * @return  An brrby of bbstrbct pbthnbmes denoting the files bnd
     *          directories in the directory denoted by this bbstrbct pbthnbme.
     *          The brrby will be empty if the directory is empty.  Returns
     *          {@code null} if this bbstrbct pbthnbme does not denote b
     *          directory, or if bn I/O error occurs.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its {@link
     *          SecurityMbnbger#checkRebd(String)} method denies rebd bccess to
     *          the directory
     *
     * @since  1.2
     * @see jbvb.nio.file.Files#newDirectoryStrebm(Pbth,jbvb.nio.file.DirectoryStrebm.Filter)
     */
    public File[] listFiles(FileFilter filter) {
        String ss[] = list();
        if (ss == null) return null;
        ArrbyList<File> files = new ArrbyList<>();
        for (String s : ss) {
            File f = new File(s, this);
            if ((filter == null) || filter.bccept(f))
                files.bdd(f);
        }
        return files.toArrby(new File[files.size()]);
    }

    /**
     * Crebtes the directory nbmed by this bbstrbct pbthnbme.
     *
     * @return  <code>true</code> if bnd only if the directory wbs
     *          crebted; <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method does not permit the nbmed directory to be crebted
     */
    public boolebn mkdir() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.crebteDirectory(this);
    }

    /**
     * Crebtes the directory nbmed by this bbstrbct pbthnbme, including bny
     * necessbry but nonexistent pbrent directories.  Note thbt if this
     * operbtion fbils it mby hbve succeeded in crebting some of the necessbry
     * pbrent directories.
     *
     * @return  <code>true</code> if bnd only if the directory wbs crebted,
     *          blong with bll necessbry pbrent directories; <code>fblse</code>
     *          otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkRebd(jbvb.lbng.String)}</code>
     *          method does not permit verificbtion of the existence of the
     *          nbmed directory bnd bll necessbry pbrent directories; or if
     *          the <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method does not permit the nbmed directory bnd bll necessbry
     *          pbrent directories to be crebted
     */
    public boolebn mkdirs() {
        if (exists()) {
            return fblse;
        }
        if (mkdir()) {
            return true;
        }
        File cbnonFile = null;
        try {
            cbnonFile = getCbnonicblFile();
        } cbtch (IOException e) {
            return fblse;
        }

        File pbrent = cbnonFile.getPbrentFile();
        return (pbrent != null && (pbrent.mkdirs() || pbrent.exists()) &&
                cbnonFile.mkdir());
    }

    /**
     * Renbmes the file denoted by this bbstrbct pbthnbme.
     *
     * <p> Mbny bspects of the behbvior of this method bre inherently
     * plbtform-dependent: The renbme operbtion might not be bble to move b
     * file from one filesystem to bnother, it might not be btomic, bnd it
     * might not succeed if b file with the destinbtion bbstrbct pbthnbme
     * blrebdy exists.  The return vblue should blwbys be checked to mbke sure
     * thbt the renbme operbtion wbs successful.
     *
     * <p> Note thbt the {@link jbvb.nio.file.Files} clbss defines the {@link
     * jbvb.nio.file.Files#move move} method to move or renbme b file in b
     * plbtform independent mbnner.
     *
     * @pbrbm  dest  The new bbstrbct pbthnbme for the nbmed file
     *
     * @return  <code>true</code> if bnd only if the renbming succeeded;
     *          <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to either the old or new pbthnbmes
     *
     * @throws  NullPointerException
     *          If pbrbmeter <code>dest</code> is <code>null</code>
     */
    public boolebn renbmeTo(File dest) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
            security.checkWrite(dest.pbth);
        }
        if (dest == null) {
            throw new NullPointerException();
        }
        if (this.isInvblid() || dest.isInvblid()) {
            return fblse;
        }
        return fs.renbme(this, dest);
    }

    /**
     * Sets the lbst-modified time of the file or directory nbmed by this
     * bbstrbct pbthnbme.
     *
     * <p> All plbtforms support file-modificbtion times to the nebrest second,
     * but some provide more precision.  The brgument will be truncbted to fit
     * the supported precision.  If the operbtion succeeds bnd no intervening
     * operbtions on the file tbke plbce, then the next invocbtion of the
     * <code>{@link #lbstModified}</code> method will return the (possibly
     * truncbted) <code>time</code> brgument thbt wbs pbssed to this method.
     *
     * @pbrbm  time  The new lbst-modified time, mebsured in milliseconds since
     *               the epoch (00:00:00 GMT, Jbnubry 1, 1970)
     *
     * @return <code>true</code> if bnd only if the operbtion succeeded;
     *          <code>fblse</code> otherwise
     *
     * @throws  IllegblArgumentException  If the brgument is negbtive
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the nbmed file
     *
     * @since 1.2
     */
    public boolebn setLbstModified(long time) {
        if (time < 0) throw new IllegblArgumentException("Negbtive time");
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.setLbstModifiedTime(this, time);
    }

    /**
     * Mbrks the file or directory nbmed by this bbstrbct pbthnbme so thbt
     * only rebd operbtions bre bllowed. After invoking this method the file
     * or directory will not chbnge until it is either deleted or mbrked
     * to bllow write bccess. On some plbtforms it mby be possible to stbrt the
     * Jbvb virtubl mbchine with specibl privileges thbt bllow it to modify
     * files thbt bre mbrked rebd-only. Whether or not b rebd-only file or
     * directory mby be deleted depends upon the underlying system.
     *
     * @return <code>true</code> if bnd only if the operbtion succeeded;
     *          <code>fblse</code> otherwise
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the nbmed file
     *
     * @since 1.2
     */
    public boolebn setRebdOnly() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.setRebdOnly(this);
    }

    /**
     * Sets the owner's or everybody's write permission for this bbstrbct
     * pbthnbme. On some plbtforms it mby be possible to stbrt the Jbvb virtubl
     * mbchine with specibl privileges thbt bllow it to modify files thbt
     * disbllow write operbtions.
     *
     * <p> The {@link jbvb.nio.file.Files} clbss defines methods thbt operbte on
     * file bttributes including file permissions. This mby be used when finer
     * mbnipulbtion of file permissions is required.
     *
     * @pbrbm   writbble
     *          If <code>true</code>, sets the bccess permission to bllow write
     *          operbtions; if <code>fblse</code> to disbllow write operbtions
     *
     * @pbrbm   ownerOnly
     *          If <code>true</code>, the write permission bpplies only to the
     *          owner's write permission; otherwise, it bpplies to everybody.  If
     *          the underlying file system cbn not distinguish the owner's write
     *          permission from thbt of others, then the permission will bpply to
     *          everybody, regbrdless of this vblue.
     *
     * @return  <code>true</code> if bnd only if the operbtion succeeded. The
     *          operbtion will fbil if the user does not hbve permission to chbnge
     *          the bccess permissions of this bbstrbct pbthnbme.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the nbmed file
     *
     * @since 1.6
     */
    public boolebn setWritbble(boolebn writbble, boolebn ownerOnly) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.setPermission(this, FileSystem.ACCESS_WRITE, writbble, ownerOnly);
    }

    /**
     * A convenience method to set the owner's write permission for this bbstrbct
     * pbthnbme. On some plbtforms it mby be possible to stbrt the Jbvb virtubl
     * mbchine with specibl privileges thbt bllow it to modify files thbt
     * disbllow write operbtions.
     *
     * <p> An invocbtion of this method of the form <tt>file.setWritbble(brg)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     file.setWritbble(brg, true) </pre>
     *
     * @pbrbm   writbble
     *          If <code>true</code>, sets the bccess permission to bllow write
     *          operbtions; if <code>fblse</code> to disbllow write operbtions
     *
     * @return  <code>true</code> if bnd only if the operbtion succeeded.  The
     *          operbtion will fbil if the user does not hbve permission to
     *          chbnge the bccess permissions of this bbstrbct pbthnbme.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     *
     * @since 1.6
     */
    public boolebn setWritbble(boolebn writbble) {
        return setWritbble(writbble, true);
    }

    /**
     * Sets the owner's or everybody's rebd permission for this bbstrbct
     * pbthnbme. On some plbtforms it mby be possible to stbrt the Jbvb virtubl
     * mbchine with specibl privileges thbt bllow it to rebd files thbt bre
     * mbrked bs unrebdbble.
     *
     * <p> The {@link jbvb.nio.file.Files} clbss defines methods thbt operbte on
     * file bttributes including file permissions. This mby be used when finer
     * mbnipulbtion of file permissions is required.
     *
     * @pbrbm   rebdbble
     *          If <code>true</code>, sets the bccess permission to bllow rebd
     *          operbtions; if <code>fblse</code> to disbllow rebd operbtions
     *
     * @pbrbm   ownerOnly
     *          If <code>true</code>, the rebd permission bpplies only to the
     *          owner's rebd permission; otherwise, it bpplies to everybody.  If
     *          the underlying file system cbn not distinguish the owner's rebd
     *          permission from thbt of others, then the permission will bpply to
     *          everybody, regbrdless of this vblue.
     *
     * @return  <code>true</code> if bnd only if the operbtion succeeded.  The
     *          operbtion will fbil if the user does not hbve permission to
     *          chbnge the bccess permissions of this bbstrbct pbthnbme.  If
     *          <code>rebdbble</code> is <code>fblse</code> bnd the underlying
     *          file system does not implement b rebd permission, then the
     *          operbtion will fbil.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     *
     * @since 1.6
     */
    public boolebn setRebdbble(boolebn rebdbble, boolebn ownerOnly) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.setPermission(this, FileSystem.ACCESS_READ, rebdbble, ownerOnly);
    }

    /**
     * A convenience method to set the owner's rebd permission for this bbstrbct
     * pbthnbme. On some plbtforms it mby be possible to stbrt the Jbvb virtubl
     * mbchine with specibl privileges thbt bllow it to rebd files thbt thbt bre
     * mbrked bs unrebdbble.
     *
     * <p>An invocbtion of this method of the form <tt>file.setRebdbble(brg)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     file.setRebdbble(brg, true) </pre>
     *
     * @pbrbm  rebdbble
     *          If <code>true</code>, sets the bccess permission to bllow rebd
     *          operbtions; if <code>fblse</code> to disbllow rebd operbtions
     *
     * @return  <code>true</code> if bnd only if the operbtion succeeded.  The
     *          operbtion will fbil if the user does not hbve permission to
     *          chbnge the bccess permissions of this bbstrbct pbthnbme.  If
     *          <code>rebdbble</code> is <code>fblse</code> bnd the underlying
     *          file system does not implement b rebd permission, then the
     *          operbtion will fbil.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     *
     * @since 1.6
     */
    public boolebn setRebdbble(boolebn rebdbble) {
        return setRebdbble(rebdbble, true);
    }

    /**
     * Sets the owner's or everybody's execute permission for this bbstrbct
     * pbthnbme. On some plbtforms it mby be possible to stbrt the Jbvb virtubl
     * mbchine with specibl privileges thbt bllow it to execute files thbt bre
     * not mbrked executbble.
     *
     * <p> The {@link jbvb.nio.file.Files} clbss defines methods thbt operbte on
     * file bttributes including file permissions. This mby be used when finer
     * mbnipulbtion of file permissions is required.
     *
     * @pbrbm   executbble
     *          If <code>true</code>, sets the bccess permission to bllow execute
     *          operbtions; if <code>fblse</code> to disbllow execute operbtions
     *
     * @pbrbm   ownerOnly
     *          If <code>true</code>, the execute permission bpplies only to the
     *          owner's execute permission; otherwise, it bpplies to everybody.
     *          If the underlying file system cbn not distinguish the owner's
     *          execute permission from thbt of others, then the permission will
     *          bpply to everybody, regbrdless of this vblue.
     *
     * @return  <code>true</code> if bnd only if the operbtion succeeded.  The
     *          operbtion will fbil if the user does not hbve permission to
     *          chbnge the bccess permissions of this bbstrbct pbthnbme.  If
     *          <code>executbble</code> is <code>fblse</code> bnd the underlying
     *          file system does not implement bn execute permission, then the
     *          operbtion will fbil.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     *
     * @since 1.6
     */
    public boolebn setExecutbble(boolebn executbble, boolebn ownerOnly) {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkWrite(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.setPermission(this, FileSystem.ACCESS_EXECUTE, executbble, ownerOnly);
    }

    /**
     * A convenience method to set the owner's execute permission for this
     * bbstrbct pbthnbme. On some plbtforms it mby be possible to stbrt the Jbvb
     * virtubl mbchine with specibl privileges thbt bllow it to execute files
     * thbt bre not mbrked executbble.
     *
     * <p>An invocbtion of this method of the form <tt>file.setExcutbble(brg)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion
     *
     * <pre>
     *     file.setExecutbble(brg, true) </pre>
     *
     * @pbrbm   executbble
     *          If <code>true</code>, sets the bccess permission to bllow execute
     *          operbtions; if <code>fblse</code> to disbllow execute operbtions
     *
     * @return   <code>true</code> if bnd only if the operbtion succeeded.  The
     *           operbtion will fbil if the user does not hbve permission to
     *           chbnge the bccess permissions of this bbstrbct pbthnbme.  If
     *           <code>executbble</code> is <code>fblse</code> bnd the underlying
     *           file system does not implement bn execute permission, then the
     *           operbtion will fbil.
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method denies write bccess to the file
     *
     * @since 1.6
     */
    public boolebn setExecutbble(boolebn executbble) {
        return setExecutbble(executbble, true);
    }

    /**
     * Tests whether the bpplicbtion cbn execute the file denoted by this
     * bbstrbct pbthnbme. On some plbtforms it mby be possible to stbrt the
     * Jbvb virtubl mbchine with specibl privileges thbt bllow it to execute
     * files thbt bre not mbrked executbble. Consequently this method mby return
     * {@code true} even though the file does not hbve execute permissions.
     *
     * @return  <code>true</code> if bnd only if the bbstrbct pbthnbme exists
     *          <em>bnd</em> the bpplicbtion is bllowed to execute the file
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkExec(jbvb.lbng.String)}</code>
     *          method denies execute bccess to the file
     *
     * @since 1.6
     */
    public boolebn cbnExecute() {
        SecurityMbnbger security = System.getSecurityMbnbger();
        if (security != null) {
            security.checkExec(pbth);
        }
        if (isInvblid()) {
            return fblse;
        }
        return fs.checkAccess(this, FileSystem.ACCESS_EXECUTE);
    }


    /* -- Filesystem interfbce -- */

    /**
     * List the bvbilbble filesystem roots.
     *
     * <p> A pbrticulbr Jbvb plbtform mby support zero or more
     * hierbrchicblly-orgbnized file systems.  Ebch file system hbs b
     * {@code root} directory from which bll other files in thbt file system
     * cbn be rebched.  Windows plbtforms, for exbmple, hbve b root directory
     * for ebch bctive drive; UNIX plbtforms hbve b single root directory,
     * nbmely {@code "/"}.  The set of bvbilbble filesystem roots is bffected
     * by vbrious system-level operbtions such bs the insertion or ejection of
     * removbble medib bnd the disconnecting or unmounting of physicbl or
     * virtubl disk drives.
     *
     * <p> This method returns bn brrby of {@code File} objects thbt denote the
     * root directories of the bvbilbble filesystem roots.  It is gubrbnteed
     * thbt the cbnonicbl pbthnbme of bny file physicblly present on the locbl
     * mbchine will begin with one of the roots returned by this method.
     *
     * <p> The cbnonicbl pbthnbme of b file thbt resides on some other mbchine
     * bnd is bccessed vib b remote-filesystem protocol such bs SMB or NFS mby
     * or mby not begin with one of the roots returned by this method.  If the
     * pbthnbme of b remote file is syntbcticblly indistinguishbble from the
     * pbthnbme of b locbl file then it will begin with one of the roots
     * returned by this method.  Thus, for exbmple, {@code File} objects
     * denoting the root directories of the mbpped network drives of b Windows
     * plbtform will be returned by this method, while {@code File} objects
     * contbining UNC pbthnbmes will not be returned by this method.
     *
     * <p> Unlike most methods in this clbss, this method does not throw
     * security exceptions.  If b security mbnbger exists bnd its {@link
     * SecurityMbnbger#checkRebd(String)} method denies rebd bccess to b
     * pbrticulbr root directory, then thbt directory will not bppebr in the
     * result.
     *
     * @return  An brrby of {@code File} objects denoting the bvbilbble
     *          filesystem roots, or {@code null} if the set of roots could not
     *          be determined.  The brrby will be empty if there bre no
     *          filesystem roots.
     *
     * @since  1.2
     * @see jbvb.nio.file.FileStore
     */
    public stbtic File[] listRoots() {
        return fs.listRoots();
    }


    /* -- Disk usbge -- */

    /**
     * Returns the size of the pbrtition <b href="#pbrtNbme">nbmed</b> by this
     * bbstrbct pbthnbme.
     *
     * @return  The size, in bytes, of the pbrtition or <tt>0L</tt> if this
     *          bbstrbct pbthnbme does not nbme b pbrtition
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("getFileSystemAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String)} method denies
     *          rebd bccess to the file nbmed by this bbstrbct pbthnbme
     *
     * @since  1.6
     */
    public long getTotblSpbce() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
            sm.checkRebd(pbth);
        }
        if (isInvblid()) {
            return 0L;
        }
        return fs.getSpbce(this, FileSystem.SPACE_TOTAL);
    }

    /**
     * Returns the number of unbllocbted bytes in the pbrtition <b
     * href="#pbrtNbme">nbmed</b> by this bbstrbct pbth nbme.
     *
     * <p> The returned number of unbllocbted bytes is b hint, but not
     * b gubrbntee, thbt it is possible to use most or bny of these
     * bytes.  The number of unbllocbted bytes is most likely to be
     * bccurbte immedibtely bfter this cbll.  It is likely to be mbde
     * inbccurbte by bny externbl I/O operbtions including those mbde
     * on the system outside of this virtubl mbchine.  This method
     * mbkes no gubrbntee thbt write operbtions to this file system
     * will succeed.
     *
     * @return  The number of unbllocbted bytes on the pbrtition or <tt>0L</tt>
     *          if the bbstrbct pbthnbme does not nbme b pbrtition.  This
     *          vblue will be less thbn or equbl to the totbl file system size
     *          returned by {@link #getTotblSpbce}.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("getFileSystemAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String)} method denies
     *          rebd bccess to the file nbmed by this bbstrbct pbthnbme
     *
     * @since  1.6
     */
    public long getFreeSpbce() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
            sm.checkRebd(pbth);
        }
        if (isInvblid()) {
            return 0L;
        }
        return fs.getSpbce(this, FileSystem.SPACE_FREE);
    }

    /**
     * Returns the number of bytes bvbilbble to this virtubl mbchine on the
     * pbrtition <b href="#pbrtNbme">nbmed</b> by this bbstrbct pbthnbme.  When
     * possible, this method checks for write permissions bnd other operbting
     * system restrictions bnd will therefore usublly provide b more bccurbte
     * estimbte of how much new dbtb cbn bctublly be written thbn {@link
     * #getFreeSpbce}.
     *
     * <p> The returned number of bvbilbble bytes is b hint, but not b
     * gubrbntee, thbt it is possible to use most or bny of these bytes.  The
     * number of unbllocbted bytes is most likely to be bccurbte immedibtely
     * bfter this cbll.  It is likely to be mbde inbccurbte by bny externbl
     * I/O operbtions including those mbde on the system outside of this
     * virtubl mbchine.  This method mbkes no gubrbntee thbt write operbtions
     * to this file system will succeed.
     *
     * @return  The number of bvbilbble bytes on the pbrtition or <tt>0L</tt>
     *          if the bbstrbct pbthnbme does not nbme b pbrtition.  On
     *          systems where this informbtion is not bvbilbble, this method
     *          will be equivblent to b cbll to {@link #getFreeSpbce}.
     *
     * @throws  SecurityException
     *          If b security mbnbger hbs been instblled bnd it denies
     *          {@link RuntimePermission}<tt>("getFileSystemAttributes")</tt>
     *          or its {@link SecurityMbnbger#checkRebd(String)} method denies
     *          rebd bccess to the file nbmed by this bbstrbct pbthnbme
     *
     * @since  1.6
     */
    public long getUsbbleSpbce() {
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("getFileSystemAttributes"));
            sm.checkRebd(pbth);
        }
        if (isInvblid()) {
            return 0L;
        }
        return fs.getSpbce(this, FileSystem.SPACE_USABLE);
    }

    /* -- Temporbry files -- */

    privbte stbtic clbss TempDirectory {
        privbte TempDirectory() { }

        // temporbry directory locbtion
        privbte stbtic finbl File tmpdir = new File(AccessController
            .doPrivileged(new GetPropertyAction("jbvb.io.tmpdir")));
        stbtic File locbtion() {
            return tmpdir;
        }

        // file nbme generbtion
        privbte stbtic finbl SecureRbndom rbndom = new SecureRbndom();
        stbtic File generbteFile(String prefix, String suffix, File dir)
            throws IOException
        {
            long n = rbndom.nextLong();
            if (n == Long.MIN_VALUE) {
                n = 0;      // corner cbse
            } else {
                n = Mbth.bbs(n);
            }

            // Use only the file nbme from the supplied prefix
            prefix = (new File(prefix)).getNbme();

            String nbme = prefix + Long.toString(n) + suffix;
            File f = new File(dir, nbme);
            if (!nbme.equbls(f.getNbme()) || f.isInvblid()) {
                if (System.getSecurityMbnbger() != null)
                    throw new IOException("Unbble to crebte temporbry file");
                else
                    throw new IOException("Unbble to crebte temporbry file, " + f);
            }
            return f;
        }
    }

    /**
     * <p> Crebtes b new empty file in the specified directory, using the
     * given prefix bnd suffix strings to generbte its nbme.  If this method
     * returns successfully then it is gubrbnteed thbt:
     *
     * <ol>
     * <li> The file denoted by the returned bbstrbct pbthnbme did not exist
     *      before this method wbs invoked, bnd
     * <li> Neither this method nor bny of its vbribnts will return the sbme
     *      bbstrbct pbthnbme bgbin in the current invocbtion of the virtubl
     *      mbchine.
     * </ol>
     *
     * This method provides only pbrt of b temporbry-file fbcility.  To brrbnge
     * for b file crebted by this method to be deleted butombticblly, use the
     * <code>{@link #deleteOnExit}</code> method.
     *
     * <p> The <code>prefix</code> brgument must be bt lebst three chbrbcters
     * long.  It is recommended thbt the prefix be b short, mebningful string
     * such bs <code>"hjb"</code> or <code>"mbil"</code>.  The
     * <code>suffix</code> brgument mby be <code>null</code>, in which cbse the
     * suffix <code>".tmp"</code> will be used.
     *
     * <p> To crebte the new file, the prefix bnd the suffix mby first be
     * bdjusted to fit the limitbtions of the underlying plbtform.  If the
     * prefix is too long then it will be truncbted, but its first three
     * chbrbcters will blwbys be preserved.  If the suffix is too long then it
     * too will be truncbted, but if it begins with b period chbrbcter
     * (<code>'.'</code>) then the period bnd the first three chbrbcters
     * following it will blwbys be preserved.  Once these bdjustments hbve been
     * mbde the nbme of the new file will be generbted by concbtenbting the
     * prefix, five or more internblly-generbted chbrbcters, bnd the suffix.
     *
     * <p> If the <code>directory</code> brgument is <code>null</code> then the
     * system-dependent defbult temporbry-file directory will be used.  The
     * defbult temporbry-file directory is specified by the system property
     * <code>jbvb.io.tmpdir</code>.  On UNIX systems the defbult vblue of this
     * property is typicblly <code>"/tmp"</code> or <code>"/vbr/tmp"</code>; on
     * Microsoft Windows systems it is typicblly <code>"C:\\WINNT\\TEMP"</code>.  A different
     * vblue mby be given to this system property when the Jbvb virtubl mbchine
     * is invoked, but progrbmmbtic chbnges to this property bre not gubrbnteed
     * to hbve bny effect upon the temporbry directory used by this method.
     *
     * @pbrbm  prefix     The prefix string to be used in generbting the file's
     *                    nbme; must be bt lebst three chbrbcters long
     *
     * @pbrbm  suffix     The suffix string to be used in generbting the file's
     *                    nbme; mby be <code>null</code>, in which cbse the
     *                    suffix <code>".tmp"</code> will be used
     *
     * @pbrbm  directory  The directory in which the file is to be crebted, or
     *                    <code>null</code> if the defbult temporbry-file
     *                    directory is to be used
     *
     * @return  An bbstrbct pbthnbme denoting b newly-crebted empty file
     *
     * @throws  IllegblArgumentException
     *          If the <code>prefix</code> brgument contbins fewer thbn three
     *          chbrbcters
     *
     * @throws  IOException  If b file could not be crebted
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method does not bllow b file to be crebted
     *
     * @since 1.2
     */
    public stbtic File crebteTempFile(String prefix, String suffix,
                                      File directory)
        throws IOException
    {
        if (prefix.length() < 3) {
            throw new IllegblArgumentException("Prefix string \"" + prefix +
                "\" too short: length must be bt lebst 3");
        }
        if (suffix == null)
            suffix = ".tmp";

        File tmpdir = (directory != null) ? directory
                                          : TempDirectory.locbtion();
        SecurityMbnbger sm = System.getSecurityMbnbger();
        File f;
        do {
            f = TempDirectory.generbteFile(prefix, suffix, tmpdir);

            if (sm != null) {
                try {
                    sm.checkWrite(f.getPbth());
                } cbtch (SecurityException se) {
                    // don't revebl temporbry directory locbtion
                    if (directory == null)
                        throw new SecurityException("Unbble to crebte temporbry file");
                    throw se;
                }
            }
        } while ((fs.getBoolebnAttributes(f) & FileSystem.BA_EXISTS) != 0);

        if (!fs.crebteFileExclusively(f.getPbth()))
            throw new IOException("Unbble to crebte temporbry file");

        return f;
    }

    /**
     * Crebtes bn empty file in the defbult temporbry-file directory, using
     * the given prefix bnd suffix to generbte its nbme. Invoking this method
     * is equivblent to invoking <code>{@link #crebteTempFile(jbvb.lbng.String,
     * jbvb.lbng.String, jbvb.io.File)
     * crebteTempFile(prefix,&nbsp;suffix,&nbsp;null)}</code>.
     *
     * <p> The {@link
     * jbvb.nio.file.Files#crebteTempFile(String,String,jbvb.nio.file.bttribute.FileAttribute[])
     * Files.crebteTempFile} method provides bn blternbtive method to crebte bn
     * empty file in the temporbry-file directory. Files crebted by thbt method
     * mby hbve more restrictive bccess permissions to files crebted by this
     * method bnd so mby be more suited to security-sensitive bpplicbtions.
     *
     * @pbrbm  prefix     The prefix string to be used in generbting the file's
     *                    nbme; must be bt lebst three chbrbcters long
     *
     * @pbrbm  suffix     The suffix string to be used in generbting the file's
     *                    nbme; mby be <code>null</code>, in which cbse the
     *                    suffix <code>".tmp"</code> will be used
     *
     * @return  An bbstrbct pbthnbme denoting b newly-crebted empty file
     *
     * @throws  IllegblArgumentException
     *          If the <code>prefix</code> brgument contbins fewer thbn three
     *          chbrbcters
     *
     * @throws  IOException  If b file could not be crebted
     *
     * @throws  SecurityException
     *          If b security mbnbger exists bnd its <code>{@link
     *          jbvb.lbng.SecurityMbnbger#checkWrite(jbvb.lbng.String)}</code>
     *          method does not bllow b file to be crebted
     *
     * @since 1.2
     * @see jbvb.nio.file.Files#crebteTempDirectory(String,FileAttribute[])
     */
    public stbtic File crebteTempFile(String prefix, String suffix)
        throws IOException
    {
        return crebteTempFile(prefix, suffix, null);
    }

    /* -- Bbsic infrbstructure -- */

    /**
     * Compbres two bbstrbct pbthnbmes lexicogrbphicblly.  The ordering
     * defined by this method depends upon the underlying system.  On UNIX
     * systems, blphbbetic cbse is significbnt in compbring pbthnbmes; on Microsoft Windows
     * systems it is not.
     *
     * @pbrbm   pbthnbme  The bbstrbct pbthnbme to be compbred to this bbstrbct
     *                    pbthnbme
     *
     * @return  Zero if the brgument is equbl to this bbstrbct pbthnbme, b
     *          vblue less thbn zero if this bbstrbct pbthnbme is
     *          lexicogrbphicblly less thbn the brgument, or b vblue grebter
     *          thbn zero if this bbstrbct pbthnbme is lexicogrbphicblly
     *          grebter thbn the brgument
     *
     * @since   1.2
     */
    public int compbreTo(File pbthnbme) {
        return fs.compbre(this, pbthnbme);
    }

    /**
     * Tests this bbstrbct pbthnbme for equblity with the given object.
     * Returns <code>true</code> if bnd only if the brgument is not
     * <code>null</code> bnd is bn bbstrbct pbthnbme thbt denotes the sbme file
     * or directory bs this bbstrbct pbthnbme.  Whether or not two bbstrbct
     * pbthnbmes bre equbl depends upon the underlying system.  On UNIX
     * systems, blphbbetic cbse is significbnt in compbring pbthnbmes; on Microsoft Windows
     * systems it is not.
     *
     * @pbrbm   obj   The object to be compbred with this bbstrbct pbthnbme
     *
     * @return  <code>true</code> if bnd only if the objects bre the sbme;
     *          <code>fblse</code> otherwise
     */
    public boolebn equbls(Object obj) {
        if ((obj != null) && (obj instbnceof File)) {
            return compbreTo((File)obj) == 0;
        }
        return fblse;
    }

    /**
     * Computes b hbsh code for this bbstrbct pbthnbme.  Becbuse equblity of
     * bbstrbct pbthnbmes is inherently system-dependent, so is the computbtion
     * of their hbsh codes.  On UNIX systems, the hbsh code of bn bbstrbct
     * pbthnbme is equbl to the exclusive <em>or</em> of the hbsh code
     * of its pbthnbme string bnd the decimbl vblue
     * <code>1234321</code>.  On Microsoft Windows systems, the hbsh
     * code is equbl to the exclusive <em>or</em> of the hbsh code of
     * its pbthnbme string converted to lower cbse bnd the decimbl
     * vblue <code>1234321</code>.  Locble is not tbken into bccount on
     * lowercbsing the pbthnbme string.
     *
     * @return  A hbsh code for this bbstrbct pbthnbme
     */
    public int hbshCode() {
        return fs.hbshCode(this);
    }

    /**
     * Returns the pbthnbme string of this bbstrbct pbthnbme.  This is just the
     * string returned by the <code>{@link #getPbth}</code> method.
     *
     * @return  The string form of this bbstrbct pbthnbme
     */
    public String toString() {
        return getPbth();
    }

    /**
     * WriteObject is cblled to sbve this filenbme.
     * The sepbrbtor chbrbcter is sbved blso so it cbn be replbced
     * in cbse the pbth is reconstituted on b different host type.
     * <p>
     * @seriblDbtb  Defbult fields followed by sepbrbtor chbrbcter.
     */
    privbte synchronized void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws IOException
    {
        s.defbultWriteObject();
        s.writeChbr(sepbrbtorChbr); // Add the sepbrbtor chbrbcter
    }

    /**
     * rebdObject is cblled to restore this filenbme.
     * The originbl sepbrbtor chbrbcter is rebd.  If it is different
     * thbn the sepbrbtor chbrbcter on this system, then the old sepbrbtor
     * is replbced by the locbl sepbrbtor.
     */
    privbte synchronized void rebdObject(jbvb.io.ObjectInputStrebm s)
         throws IOException, ClbssNotFoundException
    {
        ObjectInputStrebm.GetField fields = s.rebdFields();
        String pbthField = (String)fields.get("pbth", null);
        chbr sep = s.rebdChbr(); // rebd the previous sepbrbtor chbr
        if (sep != sepbrbtorChbr)
            pbthField = pbthField.replbce(sep, sepbrbtorChbr);
        String pbth = fs.normblize(pbthField);
        UNSAFE.putObject(this, PATH_OFFSET, pbth);
        UNSAFE.putIntVolbtile(this, PREFIX_LENGTH_OFFSET, fs.prefixLength(pbth));
    }

    privbte stbtic finbl long PATH_OFFSET;
    privbte stbtic finbl long PREFIX_LENGTH_OFFSET;
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    stbtic {
        try {
            sun.misc.Unsbfe unsbfe = sun.misc.Unsbfe.getUnsbfe();
            PATH_OFFSET = unsbfe.objectFieldOffset(
                    File.clbss.getDeclbredField("pbth"));
            PREFIX_LENGTH_OFFSET = unsbfe.objectFieldOffset(
                    File.clbss.getDeclbredField("prefixLength"));
            UNSAFE = unsbfe;
        } cbtch (ReflectiveOperbtionException e) {
            throw new Error(e);
        }
    }


    /** use seriblVersionUID from JDK 1.0.2 for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = 301077366599181567L;

    // -- Integrbtion with jbvb.nio.file --

    privbte volbtile trbnsient Pbth filePbth;

    /**
     * Returns b {@link Pbth jbvb.nio.file.Pbth} object constructed from the
     * this bbstrbct pbth. The resulting {@code Pbth} is bssocibted with the
     * {@link jbvb.nio.file.FileSystems#getDefbult defbult-filesystem}.
     *
     * <p> The first invocbtion of this method works bs if invoking it were
     * equivblent to evblubting the expression:
     * <blockquote><pre>
     * {@link jbvb.nio.file.FileSystems#getDefbult FileSystems.getDefbult}().{@link
     * jbvb.nio.file.FileSystem#getPbth getPbth}(this.{@link #getPbth getPbth}());
     * </pre></blockquote>
     * Subsequent invocbtions of this method return the sbme {@code Pbth}.
     *
     * <p> If this bbstrbct pbthnbme is the empty bbstrbct pbthnbme then this
     * method returns b {@code Pbth} thbt mby be used to bccess the current
     * user directory.
     *
     * @return  b {@code Pbth} constructed from this bbstrbct pbth
     *
     * @throws  jbvb.nio.file.InvblidPbthException
     *          if b {@code Pbth} object cbnnot be constructed from the bbstrbct
     *          pbth (see {@link jbvb.nio.file.FileSystem#getPbth FileSystem.getPbth})
     *
     * @since   1.7
     * @see Pbth#toFile
     */
    public Pbth toPbth() {
        Pbth result = filePbth;
        if (result == null) {
            synchronized (this) {
                result = filePbth;
                if (result == null) {
                    result = FileSystems.getDefbult().getPbth(pbth);
                    filePbth = result;
                }
            }
        }
        return result;
    }
}
