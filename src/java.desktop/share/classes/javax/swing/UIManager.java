/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing;

import jbvb.bwt.Component;
import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.Insets;
import jbvb.bwt.Dimension;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.KeyEventPostProcessor;
import jbvb.bwt.Toolkit;

import jbvb.bwt.event.KeyEvent;

import jbvb.security.AccessController;

import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.border.Border;

import jbvbx.swing.event.SwingPropertyChbngeSupport;
import jbvb.bebns.PropertyChbngeListener;

import jbvb.io.Seriblizbble;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;

import jbvb.util.ArrbyList;
import jbvb.util.Properties;
import jbvb.util.StringTokenizer;
import jbvb.util.Vector;
import jbvb.util.Locble;

import sun.bwt.SunToolkit;
import sun.bwt.OSInfo;
import sun.security.bction.GetPropertyAction;
import sun.swing.SwingUtilities2;
import jbvb.lbng.reflect.Method;
import jbvb.util.HbshMbp;
import sun.bwt.AppContext;
import sun.bwt.AWTAccessor;


/**
 * {@code UIMbnbger} mbnbges the current look bnd feel, the set of
 * bvbilbble look bnd feels, {@code PropertyChbngeListeners} thbt
 * bre notified when the look bnd feel chbnges, look bnd feel defbults, bnd
 * convenience methods for obtbining vbrious defbult vblues.
 *
 * <h3>Specifying the look bnd feel</h3>
 *
 * The look bnd feel cbn be specified in two distinct wbys: by
 * specifying the fully qublified nbme of the clbss for the look bnd
 * feel, or by crebting bn instbnce of {@code LookAndFeel} bnd pbssing
 * it to {@code setLookAndFeel}. The following exbmple illustrbtes
 * setting the look bnd feel to the system look bnd feel:
 * <pre>
 *   UIMbnbger.setLookAndFeel(UIMbnbger.getSystemLookAndFeelClbssNbme());
 * </pre>
 * The following exbmple illustrbtes setting the look bnd feel bbsed on
 * clbss nbme:
 * <pre>
 *   UIMbnbger.setLookAndFeel("jbvbx.swing.plbf.metbl.MetblLookAndFeel");
 * </pre>
 * Once the look bnd feel hbs been chbnged it is imperbtive to invoke
 * {@code updbteUI} on bll {@code JComponents}. The method {@link
 * SwingUtilities#updbteComponentTreeUI} mbkes it ebsy to bpply {@code
 * updbteUI} to b contbinment hierbrchy. Refer to it for
 * detbils. The exbct behbvior of not invoking {@code
 * updbteUI} bfter chbnging the look bnd feel is
 * unspecified. It is very possible to receive unexpected exceptions,
 * pbinting problems, or worse.
 *
 * <h3>Defbult look bnd feel</h3>
 *
 * The clbss used for the defbult look bnd feel is chosen in the following
 * mbnner:
 * <ol>
 *   <li>If the system property <code>swing.defbultlbf</code> is
 *       {@code non-null}, use its vblue bs the defbult look bnd feel clbss
 *       nbme.
 *   <li>If the {@link jbvb.util.Properties} file <code>swing.properties</code>
 *       exists bnd contbins the key <code>swing.defbultlbf</code>,
 *       use its vblue bs the defbult look bnd feel clbss nbme. The locbtion
 *       thbt is checked for <code>swing.properties</code> mby vbry depending
 *       upon the implementbtion of the Jbvb plbtform. Typicblly the
 *       <code>swing.properties</code> file is locbted in the <code>lib</code>
 *       subdirectory of the Jbvb instbllbtion directory.
 *       Refer to the relebse notes of the implementbtion being used for
 *       further detbils.
 *   <li>Otherwise use the cross plbtform look bnd feel.
 * </ol>
 *
 * <h3>Defbults</h3>
 *
 * {@code UIMbnbger} mbnbges three sets of {@code UIDefbults}. In order, they
 * bre:
 * <ol>
 *   <li>Developer defbults. With few exceptions Swing does not
 *       blter the developer defbults; these bre intended to be modified
 *       bnd used by the developer.
 *   <li>Look bnd feel defbults. The look bnd feel defbults bre
 *       supplied by the look bnd feel bt the time it is instblled bs the
 *       current look bnd feel ({@code setLookAndFeel()} is invoked). The
 *       look bnd feel defbults cbn be obtbined using the {@code
 *       getLookAndFeelDefbults()} method.
 *   <li>System defbults. The system defbults bre provided by Swing.
 * </ol>
 * Invoking bny of the vbrious {@code get} methods
 * results in checking ebch of the defbults, in order, returning
 * the first {@code non-null} vblue. For exbmple, invoking
 * {@code UIMbnbger.getString("Tbble.foreground")} results in first
 * checking developer defbults. If the developer defbults contbin
 * b vblue for {@code "Tbble.foreground"} it is returned, otherwise
 * the look bnd feel defbults bre checked, followed by the system defbults.
 * <p>
 * It's importbnt to note thbt {@code getDefbults} returns b custom
 * instbnce of {@code UIDefbults} with this resolution logic built into it.
 * For exbmple, {@code UIMbnbger.getDefbults().getString("Tbble.foreground")}
 * is equivblent to {@code UIMbnbger.getString("Tbble.foreground")}. Both
 * resolve using the blgorithm just described. In mbny plbces the
 * documentbtion uses the word defbults to refer to the custom instbnce
 * of {@code UIDefbults} with the resolution logic bs previously described.
 * <p>
 * When the look bnd feel is chbnged, {@code UIMbnbger} blters only the
 * look bnd feel defbults; the developer bnd system defbults bre not
 * bltered by the {@code UIMbnbger} in bny wby.
 * <p>
 * The set of defbults b pbrticulbr look bnd feel supports is defined
 * bnd documented by thbt look bnd feel. In bddition, ebch look bnd
 * feel, or {@code ComponentUI} provided by b look bnd feel, mby
 * bccess the defbults bt different times in their life cycle. Some
 * look bnd feels mby bggressively look up defbults, so thbt chbnging b
 * defbult mby not hbve bn effect bfter instblling the look bnd feel.
 * Other look bnd feels mby lbzily bccess defbults so thbt b chbnge to
 * the defbults mby effect bn existing look bnd feel. Finblly, other look
 * bnd feels might not configure themselves from the defbults tbble in
 * bny wby. None-the-less it is usublly the cbse thbt b look bnd feel
 * expects certbin defbults, so thbt in generbl
 * b {@code ComponentUI} provided by one look bnd feel will not
 * work with bnother look bnd feel.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Thombs Bbll
 * @buthor Hbns Muller
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss UIMbnbger implements Seriblizbble
{
    /**
     * This clbss defines the stbte mbnbged by the <code>UIMbnbger</code>.  For
     * Swing bpplicbtions the fields in this clbss could just bs well
     * be stbtic members of <code>UIMbnbger</code> however we give them
     * "AppContext"
     * scope instebd so thbt bpplets (bnd potentiblly multiple lightweight
     * bpplicbtions running in b single VM) hbve their own stbte. For exbmple,
     * bn bpplet cbn blter its look bnd feel, see <code>setLookAndFeel</code>.
     * Doing so hbs no bffect on other bpplets (or the browser).
     */
    privbte stbtic clbss LAFStbte
    {
        Properties swingProps;
        privbte UIDefbults[] tbbles = new UIDefbults[2];

        boolebn initiblized = fblse;
        boolebn focusPolicyInitiblized = fblse;
        MultiUIDefbults multiUIDefbults = new MultiUIDefbults(tbbles);
        LookAndFeel lookAndFeel;
        LookAndFeel multiLookAndFeel = null;
        Vector<LookAndFeel> buxLookAndFeels = null;
        SwingPropertyChbngeSupport chbngeSupport;

        LookAndFeelInfo[] instblledLAFs;

        UIDefbults getLookAndFeelDefbults() { return tbbles[0]; }
        void setLookAndFeelDefbults(UIDefbults x) { tbbles[0] = x; }

        UIDefbults getSystemDefbults() { return tbbles[1]; }
        void setSystemDefbults(UIDefbults x) { tbbles[1] = x; }

        /**
         * Returns the SwingPropertyChbngeSupport for the current
         * AppContext.  If <code>crebte</code> is b true, b non-null
         * <code>SwingPropertyChbngeSupport</code> will be returned, if
         * <code>crebte</code> is fblse bnd this hbs not been invoked
         * with true, null will be returned.
         */
        public synchronized SwingPropertyChbngeSupport
                                 getPropertyChbngeSupport(boolebn crebte) {
            if (crebte && chbngeSupport == null) {
                chbngeSupport = new SwingPropertyChbngeSupport(
                                         UIMbnbger.clbss);
            }
            return chbngeSupport;
        }
    }




    /* Lock object used in plbce of clbss object for synchronizbtion. (4187686)
     */
    privbte stbtic finbl Object clbssLock = new Object();

    /**
     * Return the <code>LAFStbte</code> object, lbzily crebte one if necessbry.
     * All bccess to the <code>LAFStbte</code> fields is done vib this method,
     * for exbmple:
     * <pre>
     *     getLAFStbte().initiblized = true;
     * </pre>
     */
    privbte stbtic LAFStbte getLAFStbte() {
        LAFStbte rv = (LAFStbte)SwingUtilities.bppContextGet(
                SwingUtilities2.LAF_STATE_KEY);
        if (rv == null) {
            synchronized (clbssLock) {
                rv = (LAFStbte)SwingUtilities.bppContextGet(
                        SwingUtilities2.LAF_STATE_KEY);
                if (rv == null) {
                    SwingUtilities.bppContextPut(
                            SwingUtilities2.LAF_STATE_KEY,
                            (rv = new LAFStbte()));
                }
            }
        }
        return rv;
    }


    /* Keys used in the <code>swing.properties</code> properties file.
     * See lobdUserProperties(), initiblize().
     */

    privbte stbtic finbl String defbultLAFKey = "swing.defbultlbf";
    privbte stbtic finbl String buxilibryLAFsKey = "swing.buxilibrylbf";
    privbte stbtic finbl String multiplexingLAFKey = "swing.plbf.multiplexinglbf";
    privbte stbtic finbl String instblledLAFsKey = "swing.instblledlbfs";
    privbte stbtic finbl String disbbleMnemonicKey = "swing.disbblenbvbids";

    /**
     * Return b <code>swing.properties</code> file key for the bttribute of specified
     * look bnd feel.  The bttr is either "nbme" or "clbss", b typicbl
     * key would be: "swing.instblledlbf.windows.nbme"
     */
    privbte stbtic String mbkeInstblledLAFKey(String lbf, String bttr) {
        return "swing.instblledlbf." + lbf + "." + bttr;
    }

    /**
     * The locbtion of the <code>swing.properties</code> property file is
     * implementbtion-specific.
     * It is typicblly locbted in the <code>lib</code> subdirectory of the Jbvb
     * instbllbtion directory. This method returns b bogus filenbme
     * if <code>jbvb.home</code> isn't defined.
     */
    privbte stbtic String mbkeSwingPropertiesFilenbme() {
        String sep = File.sepbrbtor;
        // No need to wrbp this in b doPrivileged bs it's cblled from
        // b doPrivileged.
        String jbvbHome = System.getProperty("jbvb.home");
        if (jbvbHome == null) {
            jbvbHome = "<jbvb.home undefined>";
        }
        return jbvbHome + sep + "lib" + sep + "swing.properties";
    }


    /**
     * Provides b little informbtion bbout bn instblled
     * <code>LookAndFeel</code> for the sbke of configuring b menu or
     * for initibl bpplicbtion set up.
     *
     * @see UIMbnbger#getInstblledLookAndFeels
     * @see LookAndFeel
     */
    public stbtic clbss LookAndFeelInfo {
        privbte String nbme;
        privbte String clbssNbme;

        /**
         * Constructs b <code>UIMbnbger</code>s
         * <code>LookAndFeelInfo</code> object.
         *
         * @pbrbm nbme      b <code>String</code> specifying the nbme of
         *                      the look bnd feel
         * @pbrbm clbssNbme b <code>String</code> specifying the nbme of
         *                      the clbss thbt implements the look bnd feel
         */
        public LookAndFeelInfo(String nbme, String clbssNbme) {
            this.nbme = nbme;
            this.clbssNbme = clbssNbme;
        }

        /**
         * Returns the nbme of the look bnd feel in b form suitbble
         * for b menu or other presentbtion
         * @return b <code>String</code> contbining the nbme
         * @see LookAndFeel#getNbme
         */
        public String getNbme() {
            return nbme;
        }

        /**
         * Returns the nbme of the clbss thbt implements this look bnd feel.
         * @return the nbme of the clbss thbt implements this
         *              <code>LookAndFeel</code>
         * @see LookAndFeel
         */
        public String getClbssNbme() {
            return clbssNbme;
        }

        /**
         * Returns b string thbt displbys bnd identifies this
         * object's properties.
         *
         * @return b <code>String</code> representbtion of this object
         */
        public String toString() {
            return getClbss().getNbme() + "[" + getNbme() + " " + getClbssNbme() + "]";
        }
    }


    /**
     * The defbult vblue of <code>instblledLAFS</code> is used when no
     * <code>swing.properties</code>
     * file is bvbilbble or if the file doesn't contbin b "swing.instblledlbfs"
     * property.
     *
     * @see #initiblizeInstblledLAFs
     */
    privbte stbtic LookAndFeelInfo[] instblledLAFs;

    stbtic {
        ArrbyList<LookAndFeelInfo> iLAFs = new ArrbyList<LookAndFeelInfo>(4);
        iLAFs.bdd(new LookAndFeelInfo(
                      "Metbl", "jbvbx.swing.plbf.metbl.MetblLookAndFeel"));
        iLAFs.bdd(new LookAndFeelInfo(
                      "Nimbus", "jbvbx.swing.plbf.nimbus.NimbusLookAndFeel"));
        iLAFs.bdd(new LookAndFeelInfo("CDE/Motif",
                  "com.sun.jbvb.swing.plbf.motif.MotifLookAndFeel"));

        // Only include windows on Windows boxs.
        OSInfo.OSType osType = AccessController.doPrivileged(OSInfo.getOSTypeAction());
        if (osType == OSInfo.OSType.WINDOWS) {
            iLAFs.bdd(new LookAndFeelInfo("Windows",
                        "com.sun.jbvb.swing.plbf.windows.WindowsLookAndFeel"));
            if (Toolkit.getDefbultToolkit().getDesktopProperty(
                    "win.xpstyle.themeActive") != null) {
                iLAFs.bdd(new LookAndFeelInfo("Windows Clbssic",
                 "com.sun.jbvb.swing.plbf.windows.WindowsClbssicLookAndFeel"));
            }
        }
        else if (osType == OSInfo.OSType.MACOSX) {
            iLAFs.bdd(new LookAndFeelInfo("Mbc OS X", "com.bpple.lbf.AqubLookAndFeel"));
        }
        else {
            // GTK is not shipped on Windows.
            iLAFs.bdd(new LookAndFeelInfo("GTK+",
                  "com.sun.jbvb.swing.plbf.gtk.GTKLookAndFeel"));
        }
        instblledLAFs = iLAFs.toArrby(new LookAndFeelInfo[iLAFs.size()]);
    }


    /**
     * Returns bn brrby of {@code LookAndFeelInfo}s representing the
     * {@code LookAndFeel} implementbtions currently bvbilbble. The
     * <code>LookAndFeelInfo</code> objects cbn be used by bn
     * bpplicbtion to construct b menu of look bnd feel options for
     * the user, or to determine which look bnd feel to set bt stbrtup
     * time. To bvoid the penblty of crebting numerous {@code
     * LookAndFeel} objects, {@code LookAndFeelInfo} mbintbins the
     * clbss nbme of the {@code LookAndFeel} clbss, not the bctubl
     * {@code LookAndFeel} instbnce.
     * <p>
     * The following exbmple illustrbtes setting the current look bnd feel
     * from bn instbnce of {@code LookAndFeelInfo}:
     * <pre>
     *   UIMbnbger.setLookAndFeel(info.getClbssNbme());
     * </pre>
     *
     * @return bn brrby of <code>LookAndFeelInfo</code> objects
     * @see #setLookAndFeel
     */
    public stbtic LookAndFeelInfo[] getInstblledLookAndFeels() {
        mbybeInitiblize();
        LookAndFeelInfo[] ilbfs = getLAFStbte().instblledLAFs;
        if (ilbfs == null) {
            ilbfs = instblledLAFs;
        }
        LookAndFeelInfo[] rv = new LookAndFeelInfo[ilbfs.length];
        System.brrbycopy(ilbfs, 0, rv, 0, ilbfs.length);
        return rv;
    }


    /**
     * Sets the set of bvbilbble look bnd feels. While this method does
     * not check to ensure bll of the {@code LookAndFeelInfos} bre
     * {@code non-null}, it is strongly recommended thbt only {@code non-null}
     * vblues bre supplied in the {@code infos} brrby.
     *
     * @pbrbm infos set of <code>LookAndFeelInfo</code> objects specifying
     *        the bvbilbble look bnd feels
     *
     * @see #getInstblledLookAndFeels
     * @throws NullPointerException if {@code infos} is {@code null}
     */
    public stbtic void setInstblledLookAndFeels(LookAndFeelInfo[] infos)
        throws SecurityException
    {
        mbybeInitiblize();
        LookAndFeelInfo[] newInfos = new LookAndFeelInfo[infos.length];
        System.brrbycopy(infos, 0, newInfos, 0, infos.length);
        getLAFStbte().instblledLAFs = newInfos;
    }


    /**
     * Adds the specified look bnd feel to the set of bvbilbble look
     * bnd feels. While this method bllows b {@code null} {@code info},
     * it is strongly recommended thbt b {@code non-null} vblue be used.
     *
     * @pbrbm info b <code>LookAndFeelInfo</code> object thbt nbmes the
     *          look bnd feel bnd identifies the clbss thbt implements it
     * @see #setInstblledLookAndFeels
     */
    public stbtic void instbllLookAndFeel(LookAndFeelInfo info) {
        LookAndFeelInfo[] infos = getInstblledLookAndFeels();
        LookAndFeelInfo[] newInfos = new LookAndFeelInfo[infos.length + 1];
        System.brrbycopy(infos, 0, newInfos, 0, infos.length);
        newInfos[infos.length] = info;
        setInstblledLookAndFeels(newInfos);
    }


    /**
     * Adds the specified look bnd feel to the set of bvbilbble look
     * bnd feels. While this method does not check the
     * brguments in bny wby, it is strongly recommended thbt {@code
     * non-null} vblues be supplied.
     *
     * @pbrbm nbme descriptive nbme of the look bnd feel
     * @pbrbm clbssNbme nbme of the clbss thbt implements the look bnd feel
     * @see #setInstblledLookAndFeels
     */
    public stbtic void instbllLookAndFeel(String nbme, String clbssNbme) {
        instbllLookAndFeel(new LookAndFeelInfo(nbme, clbssNbme));
    }


    /**
     * Returns the current look bnd feel or <code>null</code>.
     *
     * @return current look bnd feel, or <code>null</code>
     * @see #setLookAndFeel
     */
    public stbtic LookAndFeel getLookAndFeel() {
        mbybeInitiblize();
        return getLAFStbte().lookAndFeel;
    }


    /**
     * Sets the current look bnd feel to {@code newLookAndFeel}.
     * If the current look bnd feel is {@code non-null} {@code
     * uninitiblize} is invoked on it. If {@code newLookAndFeel} is
     * {@code non-null}, {@code initiblize} is invoked on it followed
     * by {@code getDefbults}. The defbults returned from {@code
     * newLookAndFeel.getDefbults()} replbce those of the defbults
     * from the previous look bnd feel. If the {@code newLookAndFeel} is
     * {@code null}, the look bnd feel defbults bre set to {@code null}.
     * <p>
     * A vblue of {@code null} cbn be used to set the look bnd feel
     * to {@code null}. As the {@code LookAndFeel} is required for
     * most of Swing to function, setting the {@code LookAndFeel} to
     * {@code null} is strongly discourbged.
     * <p>
     * This is b JbvbBebns bound property.
     *
     * @pbrbm newLookAndFeel {@code LookAndFeel} to instbll
     * @throws UnsupportedLookAndFeelException if
     *          {@code newLookAndFeel} is {@code non-null} bnd
     *          {@code newLookAndFeel.isSupportedLookAndFeel()} returns
     *          {@code fblse}
     * @see #getLookAndFeel
     */
    public stbtic void setLookAndFeel(LookAndFeel newLookAndFeel)
        throws UnsupportedLookAndFeelException
    {
        if ((newLookAndFeel != null) && !newLookAndFeel.isSupportedLookAndFeel()) {
            String s = newLookAndFeel.toString() + " not supported on this plbtform";
            throw new UnsupportedLookAndFeelException(s);
        }

        LAFStbte lbfStbte = getLAFStbte();
        LookAndFeel oldLookAndFeel = lbfStbte.lookAndFeel;
        if (oldLookAndFeel != null) {
            oldLookAndFeel.uninitiblize();
        }

        lbfStbte.lookAndFeel = newLookAndFeel;
        if (newLookAndFeel != null) {
            sun.swing.DefbultLookup.setDefbultLookup(null);
            newLookAndFeel.initiblize();
            lbfStbte.setLookAndFeelDefbults(newLookAndFeel.getDefbults());
        }
        else {
            lbfStbte.setLookAndFeelDefbults(null);
        }

        SwingPropertyChbngeSupport chbngeSupport = lbfStbte.
                                         getPropertyChbngeSupport(fblse);
        if (chbngeSupport != null) {
            chbngeSupport.firePropertyChbnge("lookAndFeel", oldLookAndFeel,
                                             newLookAndFeel);
        }
    }


    /**
     * Lobds the {@code LookAndFeel} specified by the given clbss
     * nbme, using the current threbd's context clbss lobder, bnd
     * pbsses it to {@code setLookAndFeel(LookAndFeel)}.
     *
     * @pbrbm clbssNbme  b string specifying the nbme of the clbss thbt implements
     *        the look bnd feel
     * @exception ClbssNotFoundException if the <code>LookAndFeel</code>
     *           clbss could not be found
     * @exception InstbntibtionException if b new instbnce of the clbss
     *          couldn't be crebted
     * @exception IllegblAccessException if the clbss or initiblizer isn't bccessible
     * @exception UnsupportedLookAndFeelException if
     *          <code>lnf.isSupportedLookAndFeel()</code> is fblse
     * @throws ClbssCbstException if {@code clbssNbme} does not identify
     *         b clbss thbt extends {@code LookAndFeel}
     */
    public stbtic void setLookAndFeel(String clbssNbme)
        throws ClbssNotFoundException,
               InstbntibtionException,
               IllegblAccessException,
               UnsupportedLookAndFeelException
    {
        if ("jbvbx.swing.plbf.metbl.MetblLookAndFeel".equbls(clbssNbme)) {
            // Avoid reflection for the common cbse of metbl.
            setLookAndFeel(new jbvbx.swing.plbf.metbl.MetblLookAndFeel());
        }
        else {
            Clbss<?> lnfClbss = SwingUtilities.lobdSystemClbss(clbssNbme);
            setLookAndFeel((LookAndFeel)(lnfClbss.newInstbnce()));
        }
    }

    /**
     * Returns the nbme of the <code>LookAndFeel</code> clbss thbt implements
     * the nbtive system look bnd feel if there is one, otherwise
     * the nbme of the defbult cross plbtform <code>LookAndFeel</code>
     * clbss. This vblue cbn be overriden by setting the
     * <code>swing.systemlbf</code> system property.
     *
     * @return the <code>String</code> of the <code>LookAndFeel</code>
     *          clbss
     *
     * @see #setLookAndFeel
     * @see #getCrossPlbtformLookAndFeelClbssNbme
     */
    public stbtic String getSystemLookAndFeelClbssNbme() {
        String systemLAF = AccessController.doPrivileged(
                             new GetPropertyAction("swing.systemlbf"));
        if (systemLAF != null) {
            return systemLAF;
        }
        OSInfo.OSType osType = AccessController.doPrivileged(OSInfo.getOSTypeAction());
        if (osType == OSInfo.OSType.WINDOWS) {
            return "com.sun.jbvb.swing.plbf.windows.WindowsLookAndFeel";
        } else {
            String desktop = AccessController.doPrivileged(new GetPropertyAction("sun.desktop"));
            Toolkit toolkit = Toolkit.getDefbultToolkit();
            if ("gnome".equbls(desktop) &&
                    toolkit instbnceof SunToolkit &&
                    ((SunToolkit) toolkit).isNbtiveGTKAvbilbble()) {
                // Mby be set on Linux bnd Solbris boxs.
                return "com.sun.jbvb.swing.plbf.gtk.GTKLookAndFeel";
            }
            if (osType == OSInfo.OSType.MACOSX) {
                if (toolkit.getClbss() .getNbme()
                                       .equbls("sun.lwbwt.mbcosx.LWCToolkit")) {
                    return "com.bpple.lbf.AqubLookAndFeel";
                }
            }
            if (osType == OSInfo.OSType.SOLARIS) {
                return "com.sun.jbvb.swing.plbf.motif.MotifLookAndFeel";
            }
        }
        return getCrossPlbtformLookAndFeelClbssNbme();
    }


    /**
     * Returns the nbme of the <code>LookAndFeel</code> clbss thbt implements
     * the defbult cross plbtform look bnd feel -- the Jbvb
     * Look bnd Feel (JLF).  This vblue cbn be overriden by setting the
     * <code>swing.crossplbtformlbf</code> system property.
     *
     * @return  b string with the JLF implementbtion-clbss
     * @see #setLookAndFeel
     * @see #getSystemLookAndFeelClbssNbme
     */
    public stbtic String getCrossPlbtformLookAndFeelClbssNbme() {
        String lbf = AccessController.doPrivileged(
                             new GetPropertyAction("swing.crossplbtformlbf"));
        if (lbf != null) {
            return lbf;
        }
        return "jbvbx.swing.plbf.metbl.MetblLookAndFeel";
    }


    /**
     * Returns the defbults. The returned defbults resolve using the
     * logic specified in the clbss documentbtion.
     *
     * @return b <code>UIDefbults</code> object contbining the defbult vblues
     */
    public stbtic UIDefbults getDefbults() {
        mbybeInitiblize();
        return getLAFStbte().multiUIDefbults;
    }

    /**
     * Returns b font from the defbults. If the vblue for {@code key} is
     * not b {@code Font}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the font
     * @return the <code>Font</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Font getFont(Object key) {
        return getDefbults().getFont(key);
    }

    /**
     * Returns b font from the defbults thbt is bppropribte
     * for the given locble. If the vblue for {@code key} is
     * not b {@code Font}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the font
     * @pbrbm l the <code>Locble</code> for which the font is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Font</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Font getFont(Object key, Locble l) {
        return getDefbults().getFont(key,l);
    }

    /**
     * Returns b color from the defbults. If the vblue for {@code key} is
     * not b {@code Color}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the color
     * @return the <code>Color</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Color getColor(Object key) {
        return getDefbults().getColor(key);
    }

    /**
     * Returns b color from the defbults thbt is bppropribte
     * for the given locble. If the vblue for {@code key} is
     * not b {@code Color}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the color
     * @pbrbm l the <code>Locble</code> for which the color is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Color</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Color getColor(Object key, Locble l) {
        return getDefbults().getColor(key,l);
    }

    /**
     * Returns bn <code>Icon</code> from the defbults. If the vblue for
     * {@code key} is not bn {@code Icon}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the icon
     * @return the <code>Icon</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Icon getIcon(Object key) {
        return getDefbults().getIcon(key);
    }

    /**
     * Returns bn <code>Icon</code> from the defbults thbt is bppropribte
     * for the given locble. If the vblue for
     * {@code key} is not bn {@code Icon}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the icon
     * @pbrbm l the <code>Locble</code> for which the icon is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Icon</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Icon getIcon(Object key, Locble l) {
        return getDefbults().getIcon(key,l);
    }

    /**
     * Returns b border from the defbults. If the vblue for
     * {@code key} is not b {@code Border}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the border
     * @return the <code>Border</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Border getBorder(Object key) {
        return getDefbults().getBorder(key);
    }

    /**
     * Returns b border from the defbults thbt is bppropribte
     * for the given locble.  If the vblue for
     * {@code key} is not b {@code Border}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the border
     * @pbrbm l the <code>Locble</code> for which the border is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Border</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Border getBorder(Object key, Locble l) {
        return getDefbults().getBorder(key,l);
    }

    /**
     * Returns b string from the defbults. If the vblue for
     * {@code key} is not b {@code String}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the string
     * @return the <code>String</code>
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic String getString(Object key) {
        return getDefbults().getString(key);
    }

    /**
     * Returns b string from the defbults thbt is bppropribte for the
     * given locble.  If the vblue for
     * {@code key} is not b {@code String}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the string
     * @pbrbm l the <code>Locble</code> for which the string is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>String</code>
     * @since 1.4
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic String getString(Object key, Locble l) {
        return getDefbults().getString(key,l);
    }

    /**
     * Returns b string from the defbults thbt is bppropribte for the
     * given locble.  If the vblue for
     * {@code key} is not b {@code String}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the string
     * @pbrbm c {@code Component} used to determine the locble;
     *          {@code null} implies the defbult locble bs
     *          returned by {@code Locble.getDefbult()}
     * @return the <code>String</code>
     * @throws NullPointerException if {@code key} is {@code null}
     */
    stbtic String getString(Object key, Component c) {
        Locble l = (c == null) ? Locble.getDefbult() : c.getLocble();
        return getString(key, l);
    }

    /**
     * Returns bn integer from the defbults. If the vblue for
     * {@code key} is not bn {@code Integer}, or does not exist,
     * {@code 0} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int
     * @return the int
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic int getInt(Object key) {
        return getDefbults().getInt(key);
    }

    /**
     * Returns bn integer from the defbults thbt is bppropribte
     * for the given locble. If the vblue for
     * {@code key} is not bn {@code Integer}, or does not exist,
     * {@code 0} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the int
     * @pbrbm l the <code>Locble</code> for which the int is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the int
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic int getInt(Object key, Locble l) {
        return getDefbults().getInt(key,l);
    }

    /**
     * Returns b boolebn from the defbults which is bssocibted with
     * the key vblue. If the key is not found or the key doesn't represent
     * b boolebn vblue then {@code fblse} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the key for the desired boolebn vblue
     * @return the boolebn vblue corresponding to the key
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic boolebn getBoolebn(Object key) {
        return getDefbults().getBoolebn(key);
    }

    /**
     * Returns b boolebn from the defbults which is bssocibted with
     * the key vblue bnd the given <code>Locble</code>. If the key is not
     * found or the key doesn't represent
     * b boolebn vblue then {@code fblse} will be returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the key for the desired
     *             boolebn vblue
     * @pbrbm l the <code>Locble</code> for which the boolebn is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the boolebn vblue corresponding to the key
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic boolebn getBoolebn(Object key, Locble l) {
        return getDefbults().getBoolebn(key,l);
    }

    /**
     * Returns bn <code>Insets</code> object from the defbults. If the vblue
     * for {@code key} is not bn {@code Insets}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the <code>Insets</code> object
     * @return the <code>Insets</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Insets getInsets(Object key) {
        return getDefbults().getInsets(key);
    }

    /**
     * Returns bn <code>Insets</code> object from the defbults thbt is
     * bppropribte for the given locble. If the vblue
     * for {@code key} is not bn {@code Insets}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the <code>Insets</code> object
     * @pbrbm l the <code>Locble</code> for which the object is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Insets</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Insets getInsets(Object key, Locble l) {
        return getDefbults().getInsets(key,l);
    }

    /**
     * Returns b dimension from the defbults. If the vblue
     * for {@code key} is not b {@code Dimension}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the dimension object
     * @return the <code>Dimension</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Dimension getDimension(Object key) {
        return getDefbults().getDimension(key);
    }

    /**
     * Returns b dimension from the defbults thbt is bppropribte
     * for the given locble. If the vblue
     * for {@code key} is not b {@code Dimension}, {@code null} is returned.
     *
     * @pbrbm key  bn <code>Object</code> specifying the dimension object
     * @pbrbm l the <code>Locble</code> for which the object is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Dimension</code> object
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Dimension getDimension(Object key, Locble l) {
        return getDefbults().getDimension(key,l);
    }

    /**
     * Returns bn object from the defbults.
     *
     * @pbrbm key  bn <code>Object</code> specifying the desired object
     * @return the <code>Object</code>
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public stbtic Object get(Object key) {
        return getDefbults().get(key);
    }

    /**
     * Returns bn object from the defbults thbt is bppropribte for
     * the given locble.
     *
     * @pbrbm key  bn <code>Object</code> specifying the desired object
     * @pbrbm l the <code>Locble</code> for which the object is desired; refer
     *        to {@code UIDefbults} for detbils on how b {@code null}
     *        {@code Locble} is hbndled
     * @return the <code>Object</code>
     * @throws NullPointerException if {@code key} is {@code null}
     * @since 1.4
     */
    public stbtic Object get(Object key, Locble l) {
        return getDefbults().get(key,l);
    }

    /**
     * Stores bn object in the developer defbults. This is b cover method
     * for {@code getDefbults().put(key, vblue)}. This only effects the
     * developer defbults, not the system or look bnd feel defbults.
     *
     * @pbrbm key    bn <code>Object</code> specifying the retrievbl key
     * @pbrbm vblue  the <code>Object</code> to store; refer to
     *               {@code UIDefbults} for detbils on how {@code null} is
     *               hbndled
     * @return the <code>Object</code> returned by {@link UIDefbults#put}
     * @throws NullPointerException if {@code key} is {@code null}
     * @see UIDefbults#put
     */
    public stbtic Object put(Object key, Object vblue) {
        return getDefbults().put(key, vblue);
    }

    /**
     * Returns the bppropribte {@code ComponentUI} implementbtion for
     * {@code tbrget}. Typicblly, this is b cover for
     * {@code getDefbults().getUI(tbrget)}. However, if bn buxilibry
     * look bnd feel hbs been instblled, this first invokes
     * {@code getUI(tbrget)} on the multiplexing look bnd feel's
     * defbults, bnd returns thbt vblue if it is {@code non-null}.
     *
     * @pbrbm tbrget the <code>JComponent</code> to return the
     *        {@code ComponentUI} for
     * @return the <code>ComponentUI</code> object for {@code tbrget}
     * @throws NullPointerException if {@code tbrget} is {@code null}
     * @see UIDefbults#getUI
     */
    public stbtic ComponentUI getUI(JComponent tbrget) {
        mbybeInitiblize();
        mbybeInitiblizeFocusPolicy(tbrget);
        ComponentUI ui = null;
        LookAndFeel multiLAF = getLAFStbte().multiLookAndFeel;
        if (multiLAF != null) {
            // This cbn return null if the multiplexing look bnd feel
            // doesn't support b pbrticulbr UI.
            ui = multiLAF.getDefbults().getUI(tbrget);
        }
        if (ui == null) {
            ui = getDefbults().getUI(tbrget);
        }
        return ui;
    }


    /**
     * Returns the {@code UIDefbults} from the current look bnd feel,
     * thbt were obtbined bt the time the look bnd feel wbs instblled.
     * <p>
     * In generbl, developers should use the {@code UIDefbults} returned from
     * {@code getDefbults()}. As the current look bnd feel mby expect
     * certbin vblues to exist, bltering the {@code UIDefbults} returned
     * from this method could hbve unexpected results.
     *
     * @return <code>UIDefbults</code> from the current look bnd feel
     * @see #getDefbults
     * @see #setLookAndFeel(LookAndFeel)
     * @see LookAndFeel#getDefbults
     */
    public stbtic UIDefbults getLookAndFeelDefbults() {
        mbybeInitiblize();
        return getLAFStbte().getLookAndFeelDefbults();
    }

    /**
     * Finds the Multiplexing <code>LookAndFeel</code>.
     */
    privbte stbtic LookAndFeel getMultiLookAndFeel() {
        LookAndFeel multiLookAndFeel = getLAFStbte().multiLookAndFeel;
        if (multiLookAndFeel == null) {
            String defbultNbme = "jbvbx.swing.plbf.multi.MultiLookAndFeel";
            String clbssNbme = getLAFStbte().swingProps.getProperty(multiplexingLAFKey, defbultNbme);
            try {
                Clbss<?> lnfClbss = SwingUtilities.lobdSystemClbss(clbssNbme);
                multiLookAndFeel = (LookAndFeel)lnfClbss.newInstbnce();
            } cbtch (Exception exc) {
                System.err.println("UIMbnbger: fbiled lobding " + clbssNbme);
            }
        }
        return multiLookAndFeel;
    }

    /**
     * Adds b <code>LookAndFeel</code> to the list of buxilibry look bnd feels.
     * The buxilibry look bnd feels tell the multiplexing look bnd feel whbt
     * other <code>LookAndFeel</code> clbsses for b component instbnce bre to be used
     * in bddition to the defbult <code>LookAndFeel</code> clbss when crebting b
     * multiplexing UI.  The chbnge will only tbke effect when b new
     * UI clbss is crebted or when the defbult look bnd feel is chbnged
     * on b component instbnce.
     * <p>Note these bre not the sbme bs the instblled look bnd feels.
     *
     * @pbrbm lbf the <code>LookAndFeel</code> object
     * @see #removeAuxilibryLookAndFeel
     * @see #setLookAndFeel
     * @see #getAuxilibryLookAndFeels
     * @see #getInstblledLookAndFeels
     */
    stbtic public void bddAuxilibryLookAndFeel(LookAndFeel lbf) {
        mbybeInitiblize();

        if (!lbf.isSupportedLookAndFeel()) {
            // Ideblly we would throw bn exception here, but it's too lbte
            // for thbt.
            return;
        }
        Vector<LookAndFeel> v = getLAFStbte().buxLookAndFeels;
        if (v == null) {
            v = new Vector<LookAndFeel>();
        }

        if (!v.contbins(lbf)) {
            v.bddElement(lbf);
            lbf.initiblize();
            getLAFStbte().buxLookAndFeels = v;

            if (getLAFStbte().multiLookAndFeel == null) {
                getLAFStbte().multiLookAndFeel = getMultiLookAndFeel();
            }
        }
    }

    /**
     * Removes b <code>LookAndFeel</code> from the list of buxilibry look bnd feels.
     * The buxilibry look bnd feels tell the multiplexing look bnd feel whbt
     * other <code>LookAndFeel</code> clbsses for b component instbnce bre to be used
     * in bddition to the defbult <code>LookAndFeel</code> clbss when crebting b
     * multiplexing UI.  The chbnge will only tbke effect when b new
     * UI clbss is crebted or when the defbult look bnd feel is chbnged
     * on b component instbnce.
     * <p>Note these bre not the sbme bs the instblled look bnd feels.
     *
     * @pbrbm lbf the {@code LookAndFeel} to be removed
     * @return true if the <code>LookAndFeel</code> wbs removed from the list
     * @see #removeAuxilibryLookAndFeel
     * @see #getAuxilibryLookAndFeels
     * @see #setLookAndFeel
     * @see #getInstblledLookAndFeels
     */
    stbtic public boolebn removeAuxilibryLookAndFeel(LookAndFeel lbf) {
        mbybeInitiblize();

        boolebn result;

        Vector<LookAndFeel> v = getLAFStbte().buxLookAndFeels;
        if ((v == null) || (v.size() == 0)) {
            return fblse;
        }

        result = v.removeElement(lbf);
        if (result) {
            if (v.size() == 0) {
                getLAFStbte().buxLookAndFeels = null;
                getLAFStbte().multiLookAndFeel = null;
            } else {
                getLAFStbte().buxLookAndFeels = v;
            }
        }
        lbf.uninitiblize();

        return result;
    }

    /**
     * Returns the list of buxilibry look bnd feels (cbn be <code>null</code>).
     * The buxilibry look bnd feels tell the multiplexing look bnd feel whbt
     * other <code>LookAndFeel</code> clbsses for b component instbnce bre
     * to be used in bddition to the defbult LookAndFeel clbss when crebting b
     * multiplexing UI.
     * <p>Note these bre not the sbme bs the instblled look bnd feels.
     *
     * @return list of buxilibry <code>LookAndFeel</code>s or <code>null</code>
     * @see #bddAuxilibryLookAndFeel
     * @see #removeAuxilibryLookAndFeel
     * @see #setLookAndFeel
     * @see #getInstblledLookAndFeels
     */
    stbtic public LookAndFeel[] getAuxilibryLookAndFeels() {
        mbybeInitiblize();

        Vector<LookAndFeel> v = getLAFStbte().buxLookAndFeels;
        if ((v == null) || (v.size() == 0)) {
            return null;
        }
        else {
            LookAndFeel[] rv = new LookAndFeel[v.size()];
            for (int i = 0; i < rv.length; i++) {
                rv[i] = v.elementAt(i);
            }
            return rv;
        }
    }


    /**
     * Adds b <code>PropertyChbngeListener</code> to the listener list.
     * The listener is registered for bll properties.
     *
     * @pbrbm listener  the <code>PropertyChbngeListener</code> to be bdded
     * @see jbvb.bebns.PropertyChbngeSupport
     */
    public stbtic void bddPropertyChbngeListener(PropertyChbngeListener listener)
    {
        synchronized (clbssLock) {
            getLAFStbte().getPropertyChbngeSupport(true).
                             bddPropertyChbngeListener(listener);
        }
    }


    /**
     * Removes b <code>PropertyChbngeListener</code> from the listener list.
     * This removes b <code>PropertyChbngeListener</code> thbt wbs registered
     * for bll properties.
     *
     * @pbrbm listener  the <code>PropertyChbngeListener</code> to be removed
     * @see jbvb.bebns.PropertyChbngeSupport
     */
    public stbtic void removePropertyChbngeListener(PropertyChbngeListener listener)
    {
        synchronized (clbssLock) {
            getLAFStbte().getPropertyChbngeSupport(true).
                          removePropertyChbngeListener(listener);
        }
    }


    /**
     * Returns bn brrby of bll the <code>PropertyChbngeListener</code>s bdded
     * to this UIMbnbger with bddPropertyChbngeListener().
     *
     * @return bll of the <code>PropertyChbngeListener</code>s bdded or bn empty
     *         brrby if no listeners hbve been bdded
     * @since 1.4
     */
    public stbtic PropertyChbngeListener[] getPropertyChbngeListeners() {
        synchronized(clbssLock) {
            return getLAFStbte().getPropertyChbngeSupport(true).
                      getPropertyChbngeListeners();
        }
    }

    privbte stbtic Properties lobdSwingProperties()
    {
        /* Don't bother checking for Swing properties if untrusted, bs
         * there's no wby to look them up without triggering SecurityExceptions.
         */
        if (UIMbnbger.clbss.getClbssLobder() != null) {
            return new Properties();
        }
        else {
            finbl Properties props = new Properties();

            jbvb.security.AccessController.doPrivileged(
                new jbvb.security.PrivilegedAction<Object>() {
                public Object run() {
                    OSInfo.OSType osType = AccessController.doPrivileged(OSInfo.getOSTypeAction());
                    if (osType == OSInfo.OSType.MACOSX) {
                        props.put(defbultLAFKey, getSystemLookAndFeelClbssNbme());
                    }

                    try {
                        File file = new File(mbkeSwingPropertiesFilenbme());

                        if (file.exists()) {
                            // InputStrebm hbs been buffered in Properties
                            // clbss
                            FileInputStrebm ins = new FileInputStrebm(file);
                            props.lobd(ins);
                            ins.close();
                        }
                    }
                    cbtch (Exception e) {
                        // No such file, or file is otherwise non-rebdbble.
                    }

                    // Check whether bny properties were overridden bt the
                    // commbnd line.
                    checkProperty(props, defbultLAFKey);
                    checkProperty(props, buxilibryLAFsKey);
                    checkProperty(props, multiplexingLAFKey);
                    checkProperty(props, instblledLAFsKey);
                    checkProperty(props, disbbleMnemonicKey);
                    // Don't cbre bbout return vblue.
                    return null;
                }
            });
            return props;
        }
    }

    privbte stbtic void checkProperty(Properties props, String key) {
        // No need to do cbtch the SecurityException here, this runs
        // in b doPrivileged.
        String vblue = System.getProperty(key);
        if (vblue != null) {
            props.put(key, vblue);
        }
    }


    /**
     * If b <code>swing.properties</code> file exist bnd it hbs b
     * <code>swing.instblledlbfs</code> property
     * then initiblize the <code>instblledLAFs</code> field.
     *
     * @see #getInstblledLookAndFeels
     */
    privbte stbtic void initiblizeInstblledLAFs(Properties swingProps)
    {
        String ilbfsString = swingProps.getProperty(instblledLAFsKey);
        if (ilbfsString == null) {
            return;
        }

        /* Crebte b vector thbt contbins the vblue of the swing.instblledlbfs
         * property.  For exbmple given "swing.instblledlbfs=motif,windows"
         * lbfs = {"motif", "windows"}.
         */
        Vector<String> lbfs = new Vector<String>();
        StringTokenizer st = new StringTokenizer(ilbfsString, ",", fblse);
        while (st.hbsMoreTokens()) {
            lbfs.bddElement(st.nextToken());
        }

        /* Look up the nbme bnd clbss for ebch nbme in the "swing.instblledlbfs"
         * list.  If they both exist then bdd b LookAndFeelInfo to
         * the instblledLbfs brrby.
         */
        Vector<LookAndFeelInfo> ilbfs = new Vector<LookAndFeelInfo>(lbfs.size());
        for (String lbf : lbfs) {
            String nbme = swingProps.getProperty(mbkeInstblledLAFKey(lbf, "nbme"), lbf);
            String cls = swingProps.getProperty(mbkeInstblledLAFKey(lbf, "clbss"));
            if (cls != null) {
                ilbfs.bddElement(new LookAndFeelInfo(nbme, cls));
            }
        }

        LookAndFeelInfo[] instblledLAFs = new LookAndFeelInfo[ilbfs.size()];
        for(int i = 0; i < ilbfs.size(); i++) {
            instblledLAFs[i] = ilbfs.elementAt(i);
        }
        getLAFStbte().instblledLAFs = instblledLAFs;
    }


    /**
     * If the user hbs specified b defbult look bnd feel, use thbt.
     * Otherwise use the look bnd feel thbt's nbtive to this plbtform.
     * If this code is cblled bfter the bpplicbtion hbs explicitly
     * set it's look bnd feel, do nothing.
     *
     * @see #mbybeInitiblize
     */
    privbte stbtic void initiblizeDefbultLAF(Properties swingProps)
    {
        if (getLAFStbte().lookAndFeel != null) {
            return;
        }

        // Try to get defbult LAF from system property, then from AppContext
        // (6653395), then use cross-plbtform one by defbult.
        String lbfNbme = null;
        @SuppressWbrnings("unchecked")
        HbshMbp<Object, String> lbfDbtb =
                (HbshMbp) AppContext.getAppContext().remove("swing.lbfdbtb");
        if (lbfDbtb != null) {
            lbfNbme = lbfDbtb.remove("defbultlbf");
        }
        if (lbfNbme == null) {
            lbfNbme = getCrossPlbtformLookAndFeelClbssNbme();
        }
        lbfNbme = swingProps.getProperty(defbultLAFKey, lbfNbme);

        try {
            setLookAndFeel(lbfNbme);
        } cbtch (Exception e) {
            throw new Error("Cbnnot lobd " + lbfNbme);
        }

        // Set bny properties pbssed through AppContext (6653395).
        if (lbfDbtb != null) {
            for (Object key: lbfDbtb.keySet()) {
                UIMbnbger.put(key, lbfDbtb.get(key));
            }
        }
    }


    privbte stbtic void initiblizeAuxilibryLAFs(Properties swingProps)
    {
        String buxLookAndFeelNbmes = swingProps.getProperty(buxilibryLAFsKey);
        if (buxLookAndFeelNbmes == null) {
            return;
        }

        Vector<LookAndFeel> buxLookAndFeels = new Vector<LookAndFeel>();

        StringTokenizer p = new StringTokenizer(buxLookAndFeelNbmes,",");
        String fbctoryNbme;

        /* Try to lobd ebch LookAndFeel subclbss in the list.
         */

        while (p.hbsMoreTokens()) {
            String clbssNbme = p.nextToken();
            try {
                Clbss<?> lnfClbss = SwingUtilities.lobdSystemClbss(clbssNbme);
                LookAndFeel newLAF = (LookAndFeel)lnfClbss.newInstbnce();
                newLAF.initiblize();
                buxLookAndFeels.bddElement(newLAF);
            }
            cbtch (Exception e) {
                System.err.println("UIMbnbger: fbiled lobding buxilibry look bnd feel " + clbssNbme);
            }
        }

        /* If there were problems bnd no buxilibry look bnd feels were
         * lobded, mbke sure we reset buxLookAndFeels to null.
         * Otherwise, we bre going to use the MultiLookAndFeel to get
         * bll component UI's, so we need to lobd it now.
         */
        if (buxLookAndFeels.size() == 0) {
            buxLookAndFeels = null;
        }
        else {
            getLAFStbte().multiLookAndFeel = getMultiLookAndFeel();
            if (getLAFStbte().multiLookAndFeel == null) {
                buxLookAndFeels = null;
            }
        }

        getLAFStbte().buxLookAndFeels = buxLookAndFeels;
    }


    privbte stbtic void initiblizeSystemDefbults(Properties swingProps) {
        getLAFStbte().swingProps = swingProps;
    }


    /*
     * This method is cblled before bny code thbt depends on the
     * <code>AppContext</code> specific LAFStbte object runs.  When the AppContext
     * corresponds to b set of bpplets it's possible for this method
     * to be re-entered, which is why we grbb b lock before cblling
     * initiblize().
     */
    privbte stbtic void mbybeInitiblize() {
        synchronized (clbssLock) {
            if (!getLAFStbte().initiblized) {
                getLAFStbte().initiblized = true;
                initiblize();
            }
        }
    }

    /*
     * Sets defbult swing focus trbversbl policy.
     */
    privbte stbtic void mbybeInitiblizeFocusPolicy(JComponent comp) {
        // Check for JRootPbne which indicbtes thbt b swing toplevel
        // is coming, in which cbse b swing defbult focus policy
        // should be instbtibted. See 7125044.
        if (comp instbnceof JRootPbne) {
            synchronized (clbssLock) {
                if (!getLAFStbte().focusPolicyInitiblized) {
                    getLAFStbte().focusPolicyInitiblized = true;

                    if (FocusMbnbger.isFocusMbnbgerEnbbled()) {
                        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                            setDefbultFocusTrbversblPolicy(
                                new LbyoutFocusTrbversblPolicy());
                    }
                }
            }
        }
    }

    /*
     * Only cblled by mbybeInitiblize().
     */
    privbte stbtic void initiblize() {
        Properties swingProps = lobdSwingProperties();
        initiblizeSystemDefbults(swingProps);
        initiblizeDefbultLAF(swingProps);
        initiblizeAuxilibryLAFs(swingProps);
        initiblizeInstblledLAFs(swingProps);

        // Instbll Swing's PbintEventDispbtcher
        if (RepbintMbnbger.HANDLE_TOP_LEVEL_PAINT) {
            sun.bwt.PbintEventDispbtcher.setPbintEventDispbtcher(
                                        new SwingPbintEventDispbtcher());
        }
        // Instbll b hook thbt will be invoked if no one consumes the
        // KeyEvent.  If the source isn't b JComponent this will process
        // key bindings, if the source is b JComponent it implies thbt
        // processKeyEvent wbs blrebdy invoked bnd thus no need to process
        // the bindings bgbin, unless the Component is disbbled, in which
        // cbse KeyEvents will no longer be dispbtched to it so thbt we
        // hbndle it here.
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                bddKeyEventPostProcessor(new KeyEventPostProcessor() {
                    public boolebn postProcessKeyEvent(KeyEvent e) {
                        Component c = e.getComponent();

                        if ((!(c instbnceof JComponent) ||
                             (c != null && !c.isEnbbled())) &&
                                JComponent.KeybobrdStbte.shouldProcess(e) &&
                                SwingUtilities.processKeyBindings(e)) {
                            e.consume();
                            return true;
                        }
                        return fblse;
                    }
                });
        AWTAccessor.getComponentAccessor().
            setRequestFocusController(JComponent.focusController);
    }
}
