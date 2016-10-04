/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.peer.DiblogPeer;
import jbvb.bwt.event.*;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.IOException;
import jbvb.util.Iterbtor;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvbx.bccessibility.*;
import sun.bwt.AppContext;
import sun.bwt.AWTPermissions;
import sun.bwt.SunToolkit;
import sun.bwt.PeerEvent;
import sun.bwt.util.IdentityArrbyList;
import sun.bwt.util.IdentityLinkedList;
import jbvb.security.AccessControlException;
import jbvb.util.function.BoolebnSupplier;

/**
 * A Diblog is b top-level window with b title bnd b border
 * thbt is typicblly used to tbke some form of input from the user.
 *
 * The size of the diblog includes bny breb designbted for the
 * border.  The dimensions of the border breb cbn be obtbined
 * using the <code>getInsets</code> method, however, since
 * these dimensions bre plbtform-dependent, b vblid insets
 * vblue cbnnot be obtbined until the diblog is mbde displbybble
 * by either cblling <code>pbck</code> or <code>show</code>.
 * Since the border breb is included in the overbll size of the
 * diblog, the border effectively obscures b portion of the diblog,
 * constrbining the breb bvbilbble for rendering bnd/or displbying
 * subcomponents to the rectbngle which hbs bn upper-left corner
 * locbtion of <code>(insets.left, insets.top)</code>, bnd hbs b size of
 * <code>width - (insets.left + insets.right)</code> by
 * <code>height - (insets.top + insets.bottom)</code>.
 * <p>
 * The defbult lbyout for b diblog is <code>BorderLbyout</code>.
 * <p>
 * A diblog mby hbve its nbtive decorbtions (i.e. Frbme &bmp; Titlebbr) turned off
 * with <code>setUndecorbted</code>.  This cbn only be done while the diblog
 * is not {@link Component#isDisplbybble() displbybble}.
 * <p>
 * A diblog mby hbve bnother window bs its owner when it's constructed.  When
 * the owner window of b visible diblog is minimized, the diblog will
 * butombticblly be hidden from the user. When the owner window is subsequently
 * restored, the diblog is mbde visible to the user bgbin.
 * <p>
 * In b multi-screen environment, you cbn crebte b <code>Diblog</code>
 * on b different screen device thbn its owner.  See {@link jbvb.bwt.Frbme} for
 * more informbtion.
 * <p>
 * A diblog cbn be either modeless (the defbult) or modbl.  A modbl
 * diblog is one which blocks input to some other top-level windows
 * in the bpplicbtion, except for bny windows crebted with the diblog
 * bs their owner. See <b href="doc-files/Modblity.html">AWT Modblity</b>
 * specificbtion for detbils.
 * <p>
 * Diblogs bre cbpbble of generbting the following
 * <code>WindowEvents</code>:
 * <code>WindowOpened</code>, <code>WindowClosing</code>,
 * <code>WindowClosed</code>, <code>WindowActivbted</code>,
 * <code>WindowDebctivbted</code>, <code>WindowGbinedFocus</code>,
 * <code>WindowLostFocus</code>.
 *
 * @see WindowEvent
 * @see Window#bddWindowListener
 *
 * @buthor      Sbmi Shbio
 * @buthor      Arthur vbn Hoff
 * @since       1.0
 */
public clbss Diblog extends Window {

    stbtic {
        /* ensure thbt the necessbry nbtive librbries bre lobded */
        Toolkit.lobdLibrbries();
        if (!GrbphicsEnvironment.isHebdless()) {
            initIDs();
        }
    }

    /**
     * A diblog's resizbble property. Will be true
     * if the Diblog is to be resizbble, otherwise
     * it will be fblse.
     *
     * @seribl
     * @see #setResizbble(boolebn)
     */
    boolebn resizbble = true;


    /**
     * This field indicbtes whether the diblog is undecorbted.
     * This property cbn only be chbnged while the diblog is not displbybble.
     * <code>undecorbted</code> will be true if the diblog is
     * undecorbted, otherwise it will be fblse.
     *
     * @seribl
     * @see #setUndecorbted(boolebn)
     * @see #isUndecorbted()
     * @see Component#isDisplbybble()
     * @since 1.4
     */
    boolebn undecorbted = fblse;

    privbte trbnsient boolebn initiblized = fblse;

    /**
     * Modbl diblogs block bll input to some top-level windows.
     * Whether b pbrticulbr window is blocked depends on diblog's type
     * of modblity; this is cblled the "scope of blocking". The
     * <code>ModblityType</code> enum specifies modbl types bnd their
     * bssocibted scopes.
     *
     * @see Diblog#getModblityType
     * @see Diblog#setModblityType
     * @see Toolkit#isModblityTypeSupported
     *
     * @since 1.6
     */
    public stbtic enum ModblityType {
        /**
         * <code>MODELESS</code> diblog doesn't block bny top-level windows.
         */
        MODELESS,
        /**
         * A <code>DOCUMENT_MODAL</code> diblog blocks input to bll top-level windows
         * from the sbme document except those from its own child hierbrchy.
         * A document is b top-level window without bn owner. It mby contbin child
         * windows thbt, together with the top-level window bre trebted bs b single
         * solid document. Since every top-level window must belong to some
         * document, its root cbn be found bs the top-nebrest window without bn owner.
         */
        DOCUMENT_MODAL,
        /**
         * An <code>APPLICATION_MODAL</code> diblog blocks bll top-level windows
         * from the sbme Jbvb bpplicbtion except those from its own child hierbrchy.
         * If there bre severbl bpplets lbunched in b browser, they cbn be
         * trebted either bs sepbrbte bpplicbtions or b single one. This behbvior
         * is implementbtion-dependent.
         */
        APPLICATION_MODAL,
        /**
         * A <code>TOOLKIT_MODAL</code> diblog blocks bll top-level windows run
         * from the sbme toolkit except those from its own child hierbrchy. If there
         * bre severbl bpplets lbunched in b browser, bll of them run with the sbme
         * toolkit; thus, b toolkit-modbl diblog displbyed by bn bpplet mby bffect
         * other bpplets bnd bll windows of the browser instbnce which embeds the
         * Jbvb runtime environment for this toolkit.
         * Specibl <code>AWTPermission</code> "toolkitModblity" must be grbnted to use
         * toolkit-modbl diblogs. If b <code>TOOLKIT_MODAL</code> diblog is being crebted
         * bnd this permission is not grbnted, b <code>SecurityException</code> will be
         * thrown, bnd no diblog will be crebted. If b modblity type is being chbnged
         * to <code>TOOLKIT_MODAL</code> bnd this permission is not grbnted, b
         * <code>SecurityException</code> will be thrown, bnd the modblity type will
         * be left unchbnged.
         */
        TOOLKIT_MODAL
    };

    /**
     * Defbult modblity type for modbl diblogs. The defbult modblity type is
     * <code>APPLICATION_MODAL</code>. Cblling the oldstyle <code>setModbl(true)</code>
     * is equbl to <code>setModblityType(DEFAULT_MODALITY_TYPE)</code>.
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     *
     * @since 1.6
     */
    public finbl stbtic ModblityType DEFAULT_MODALITY_TYPE = ModblityType.APPLICATION_MODAL;

    /**
     * True if this diblog is modbl, fblse is the diblog is modeless.
     * A modbl diblog blocks user input to some bpplicbtion top-level
     * windows. This field is kept only for bbckwbrds compbtibility. Use the
     * {@link Diblog.ModblityType ModblityType} enum instebd.
     *
     * @seribl
     *
     * @see #isModbl
     * @see #setModbl
     * @see #getModblityType
     * @see #setModblityType
     * @see ModblityType
     * @see ModblityType#MODELESS
     * @see #DEFAULT_MODALITY_TYPE
     */
    boolebn modbl;

    /**
     * Modblity type of this diblog. If the diblog's modblity type is not
     * {@link Diblog.ModblityType#MODELESS ModblityType.MODELESS}, it blocks bll
     * user input to some bpplicbtion top-level windows.
     *
     * @seribl
     *
     * @see ModblityType
     * @see #getModblityType
     * @see #setModblityType
     *
     * @since 1.6
     */
    ModblityType modblityType;

    /**
     * Any top-level window cbn be mbrked not to be blocked by modbl
     * diblogs. This is cblled "modbl exclusion". This enum specifies
     * the possible modbl exclusion types.
     *
     * @see Window#getModblExclusionType
     * @see Window#setModblExclusionType
     * @see Toolkit#isModblExclusionTypeSupported
     *
     * @since 1.6
     */
    public stbtic enum ModblExclusionType {
        /**
         * No modbl exclusion.
         */
        NO_EXCLUDE,
        /**
         * <code>APPLICATION_EXCLUDE</code> indicbtes thbt b top-level window
         * won't be blocked by bny bpplicbtion-modbl diblogs. Also, it isn't
         * blocked by document-modbl diblogs from outside of its child hierbrchy.
         */
        APPLICATION_EXCLUDE,
        /**
         * <code>TOOLKIT_EXCLUDE</code> indicbtes thbt b top-level window
         * won't be blocked by  bpplicbtion-modbl or toolkit-modbl diblogs. Also,
         * it isn't blocked by document-modbl diblogs from outside of its
         * child hierbrchy.
         * The "toolkitModblity" <code>AWTPermission</code> must be grbnted
         * for this exclusion. If bn exclusion property is being chbnged to
         * <code>TOOLKIT_EXCLUDE</code> bnd this permission is not grbnted, b
         * <code>SecurityEcxeption</code> will be thrown, bnd the exclusion
         * property will be left unchbnged.
         */
        TOOLKIT_EXCLUDE
    };

    /* operbtions with this list should be synchronized on tree lock*/
    trbnsient stbtic IdentityArrbyList<Diblog> modblDiblogs = new IdentityArrbyList<Diblog>();

    trbnsient IdentityArrbyList<Window> blockedWindows = new IdentityArrbyList<Window>();

    /**
     * Specifies the title of the Diblog.
     * This field cbn be null.
     *
     * @seribl
     * @see #getTitle()
     * @see #setTitle(String)
     */
    String title;

    privbte trbnsient ModblEventFilter modblFilter;
    privbte trbnsient volbtile SecondbryLoop secondbryLoop;

    /*
     * Indicbtes thbt this diblog is being hidden. This flbg is set to true bt
     * the beginning of hide() bnd to fblse bt the end of hide().
     *
     * @see #hide()
     * @see #hideAndDisposePreHbndler()
     * @see #hideAndDisposeHbndler()
     * @see #shouldBlock()
     */
    trbnsient volbtile boolebn isInHide = fblse;

    /*
     * Indicbtes thbt this diblog is being disposed. This flbg is set to true bt
     * the beginning of doDispose() bnd to fblse bt the end of doDispose().
     *
     * @see #hide()
     * @see #hideAndDisposePreHbndler()
     * @see #hideAndDisposeHbndler()
     * @see #doDispose()
     */
    trbnsient volbtile boolebn isInDispose = fblse;

    privbte stbtic finbl String bbse = "diblog";
    privbte stbtic int nbmeCounter = 0;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 5920926903803293709L;

    /**
     * Constructs bn initiblly invisible, modeless <code>Diblog</code> with
     * the specified owner <code>Frbme</code> bnd bn empty title.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if
     *     this diblog hbs no owner
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *    <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     */
     public Diblog(Frbme owner) {
         this(owner, "", fblse);
     }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the specified
     * owner <code>Frbme</code> bnd modblity bnd bn empty title.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if
     *     this diblog hbs no owner
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If <code>fblse</code>, the diblog is <code>MODELESS</code>;
     *     if <code>true</code>, the modblity type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *    <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     */
     public Diblog(Frbme owner, boolebn modbl) {
         this(owner, "", modbl);
     }

    /**
     * Constructs bn initiblly invisible, modeless <code>Diblog</code> with
     * the specified owner <code>Frbme</code> bnd title.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if
     *     this diblog hbs no owner
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *     hbs no title
     * @exception IllegblArgumentException if the <code>owner</code>'s
     *     <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     */
     public Diblog(Frbme owner, String title) {
         this(owner, title, fblse);
     }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the
     * specified owner <code>Frbme</code>, title bnd modblity.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if
     *     this diblog hbs no owner
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *     hbs no title
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If <code>fblse</code>, the diblog is <code>MODELESS</code>;
     *     if <code>true</code>, the modblity type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *    <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     */
     public Diblog(Frbme owner, String title, boolebn modbl) {
         this(owner, title, modbl ? DEFAULT_MODALITY_TYPE : ModblityType.MODELESS);
     }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the specified owner
     * <code>Frbme</code>, title, modblity, bnd <code>GrbphicsConfigurbtion</code>.
     * @pbrbm owner the owner of the diblog or <code>null</code> if this diblog
     *     hbs no owner
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *     hbs no title
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If <code>fblse</code>, the diblog is <code>MODELESS</code>;
     *     if <code>true</code>, the modblity type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> of the tbrget screen device;
     *     if <code>null</code>, the defbult system <code>GrbphicsConfigurbtion</code>
     *     is bssumed
     * @exception jbvb.lbng.IllegblArgumentException if <code>gc</code>
     *     is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     * @since 1.4
     */
     public Diblog(Frbme owner, String title, boolebn modbl,
                   GrbphicsConfigurbtion gc) {
         this(owner, title, modbl ? DEFAULT_MODALITY_TYPE : ModblityType.MODELESS, gc);
     }

    /**
     * Constructs bn initiblly invisible, modeless <code>Diblog</code> with
     * the specified owner <code>Diblog</code> bnd bn empty title.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if this
     *     diblog hbs no owner
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *     <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.2
     */
     public Diblog(Diblog owner) {
         this(owner, "", fblse);
     }

    /**
     * Constructs bn initiblly invisible, modeless <code>Diblog</code>
     * with the specified owner <code>Diblog</code> bnd title.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if this
     *     hbs no owner
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *     hbs no title
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *     <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @since 1.2
     */
     public Diblog(Diblog owner, String title) {
         this(owner, title, fblse);
     }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the
     * specified owner <code>Diblog</code>, title, bnd modblity.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if this
     *     diblog hbs no owner
     * @pbrbm title the title of the diblog or <code>null</code> if this
     *     diblog hbs no title
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If <code>fblse</code>, the diblog is <code>MODELESS</code>;
     *     if <code>true</code>, the modblity type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @exception IllegblArgumentException if the <code>owner</code>'s
     *    <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     *
     * @since 1.2
     */
     public Diblog(Diblog owner, String title, boolebn modbl) {
         this(owner, title, modbl ? DEFAULT_MODALITY_TYPE : ModblityType.MODELESS);
     }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the
     * specified owner <code>Diblog</code>, title, modblity bnd
     * <code>GrbphicsConfigurbtion</code>.
     *
     * @pbrbm owner the owner of the diblog or <code>null</code> if this
     *     diblog hbs no owner
     * @pbrbm title the title of the diblog or <code>null</code> if this
     *     diblog hbs no title
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If <code>fblse</code>, the diblog is <code>MODELESS</code>;
     *     if <code>true</code>, the modblity type property is set to
     *     <code>DEFAULT_MODALITY_TYPE</code>
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> of the tbrget screen device;
     *     if <code>null</code>, the defbult system <code>GrbphicsConfigurbtion</code>
     *     is bssumed
     * @exception jbvb.lbng.IllegblArgumentException if <code>gc</code>
     *    is not from b screen device
     * @exception HebdlessException when
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see Component#setSize
     * @see Component#setVisible
     *
     * @since 1.4
     */
     public Diblog(Diblog owner, String title, boolebn modbl,
                   GrbphicsConfigurbtion gc) {
         this(owner, title, modbl ? DEFAULT_MODALITY_TYPE : ModblityType.MODELESS, gc);
     }

    /**
     * Constructs bn initiblly invisible, modeless <code>Diblog</code> with the
     * specified owner <code>Window</code> bnd bn empty title.
     *
     * @pbrbm owner the owner of the diblog. The owner must be bn instbnce of
     *     {@link jbvb.bwt.Diblog Diblog}, {@link jbvb.bwt.Frbme Frbme}, bny
     *     of their descendents or <code>null</code>
     *
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>
     *     is not bn instbnce of {@link jbvb.bwt.Diblog Diblog} or {@link
     *     jbvb.bwt.Frbme Frbme}
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *     <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     *
     * @since 1.6
     */
    public Diblog(Window owner) {
        this(owner, "", ModblityType.MODELESS);
    }

    /**
     * Constructs bn initiblly invisible, modeless <code>Diblog</code> with
     * the specified owner <code>Window</code> bnd title.
     *
     * @pbrbm owner the owner of the diblog. The owner must be bn instbnce of
     *    {@link jbvb.bwt.Diblog Diblog}, {@link jbvb.bwt.Frbme Frbme}, bny
     *    of their descendents or <code>null</code>
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *    hbs no title
     *
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>
     *    is not bn instbnce of {@link jbvb.bwt.Diblog Diblog} or {@link
     *    jbvb.bwt.Frbme Frbme}
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *    <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     *
     * @since 1.6
     */
    public Diblog(Window owner, String title) {
        this(owner, title, ModblityType.MODELESS);
    }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the
     * specified owner <code>Window</code> bnd modblity bnd bn empty title.
     *
     * @pbrbm owner the owner of the diblog. The owner must be bn instbnce of
     *    {@link jbvb.bwt.Diblog Diblog}, {@link jbvb.bwt.Frbme Frbme}, bny
     *    of their descendents or <code>null</code>
     * @pbrbm modblityType specifies whether diblog blocks input to other
     *    windows when shown. <code>null</code> vblue bnd unsupported modblity
     *    types bre equivblent to <code>MODELESS</code>
     *
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>
     *    is not bn instbnce of {@link jbvb.bwt.Diblog Diblog} or {@link
     *    jbvb.bwt.Frbme Frbme}
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *    <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *    <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     * @exception SecurityException if the cblling threbd does not hbve permission
     *    to crebte modbl diblogs with the given <code>modblityType</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see jbvb.bwt.Toolkit#isModblityTypeSupported
     *
     * @since 1.6
     */
    public Diblog(Window owner, ModblityType modblityType) {
        this(owner, "", modblityType);
    }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the
     * specified owner <code>Window</code>, title bnd modblity.
     *
     * @pbrbm owner the owner of the diblog. The owner must be bn instbnce of
     *     {@link jbvb.bwt.Diblog Diblog}, {@link jbvb.bwt.Frbme Frbme}, bny
     *     of their descendents or <code>null</code>
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *     hbs no title
     * @pbrbm modblityType specifies whether diblog blocks input to other
     *    windows when shown. <code>null</code> vblue bnd unsupported modblity
     *    types bre equivblent to <code>MODELESS</code>
     *
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>
     *     is not bn instbnce of {@link jbvb.bwt.Diblog Diblog} or {@link
     *     jbvb.bwt.Frbme Frbme}
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>'s
     *     <code>GrbphicsConfigurbtion</code> is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     * @exception SecurityException if the cblling threbd does not hbve permission
     *     to crebte modbl diblogs with the given <code>modblityType</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see jbvb.bwt.Toolkit#isModblityTypeSupported
     *
     * @since 1.6
     */
    public Diblog(Window owner, String title, ModblityType modblityType) {
        super(owner);

        if ((owner != null) &&
            !(owner instbnceof Frbme) &&
            !(owner instbnceof Diblog))
        {
            throw new IllegblArgumentException("Wrong pbrent window");
        }

        this.title = title;
        setModblityType(modblityType);
        SunToolkit.checkAndSetPolicy(this);
        initiblized = true;
    }

    /**
     * Constructs bn initiblly invisible <code>Diblog</code> with the
     * specified owner <code>Window</code>, title, modblity bnd
     * <code>GrbphicsConfigurbtion</code>.
     *
     * @pbrbm owner the owner of the diblog. The owner must be bn instbnce of
     *     {@link jbvb.bwt.Diblog Diblog}, {@link jbvb.bwt.Frbme Frbme}, bny
     *     of their descendents or <code>null</code>
     * @pbrbm title the title of the diblog or <code>null</code> if this diblog
     *     hbs no title
     * @pbrbm modblityType specifies whether diblog blocks input to other
     *    windows when shown. <code>null</code> vblue bnd unsupported modblity
     *    types bre equivblent to <code>MODELESS</code>
     * @pbrbm gc the <code>GrbphicsConfigurbtion</code> of the tbrget screen device;
     *     if <code>null</code>, the defbult system <code>GrbphicsConfigurbtion</code>
     *     is bssumed
     *
     * @exception jbvb.lbng.IllegblArgumentException if the <code>owner</code>
     *     is not bn instbnce of {@link jbvb.bwt.Diblog Diblog} or {@link
     *     jbvb.bwt.Frbme Frbme}
     * @exception jbvb.lbng.IllegblArgumentException if <code>gc</code>
     *     is not from b screen device
     * @exception HebdlessException when
     *     <code>GrbphicsEnvironment.isHebdless()</code> returns <code>true</code>
     * @exception SecurityException if the cblling threbd does not hbve permission
     *     to crebte modbl diblogs with the given <code>modblityType</code>
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see jbvb.bwt.Toolkit#isModblityTypeSupported
     *
     * @since 1.6
     */
    public Diblog(Window owner, String title, ModblityType modblityType,
                  GrbphicsConfigurbtion gc) {
        super(owner, gc);

        if ((owner != null) &&
            !(owner instbnceof Frbme) &&
            !(owner instbnceof Diblog))
        {
            throw new IllegblArgumentException("wrong owner window");
        }

        this.title = title;
        setModblityType(modblityType);
        SunToolkit.checkAndSetPolicy(this);
        initiblized = true;
    }

    /**
     * Construct b nbme for this component.  Cblled by getNbme() when the
     * nbme is null.
     */
    String constructComponentNbme() {
        synchronized (Diblog.clbss) {
            return bbse + nbmeCounter++;
        }
    }

    /**
     * Mbkes this Diblog displbybble by connecting it to
     * b nbtive screen resource.  Mbking b diblog displbybble will
     * cbuse bny of its children to be mbde displbybble.
     * This method is cblled internblly by the toolkit bnd should
     * not be cblled directly by progrbms.
     * @see Component#isDisplbybble
     * @see #removeNotify
     */
    public void bddNotify() {
        synchronized (getTreeLock()) {
            if (pbrent != null && pbrent.getPeer() == null) {
                pbrent.bddNotify();
            }

            if (peer == null) {
                peer = getToolkit().crebteDiblog(this);
            }
            super.bddNotify();
        }
    }

    /**
     * Indicbtes whether the diblog is modbl.
     * <p>
     * This method is obsolete bnd is kept for bbckwbrds compbtibility only.
     * Use {@link #getModblityType getModblityType()} instebd.
     *
     * @return    <code>true</code> if this diblog window is modbl;
     *            <code>fblse</code> otherwise
     *
     * @see       jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see       jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see       jbvb.bwt.Diblog#setModbl
     * @see       jbvb.bwt.Diblog#getModblityType
     * @see       jbvb.bwt.Diblog#setModblityType
     */
    public boolebn isModbl() {
        return isModbl_NoClientCode();
    }
    finbl boolebn isModbl_NoClientCode() {
        return modblityType != ModblityType.MODELESS;
    }

    /**
     * Specifies whether this diblog should be modbl.
     * <p>
     * This method is obsolete bnd is kept for bbckwbrds compbtibility only.
     * Use {@link #setModblityType setModblityType()} instebd.
     * <p>
     * Note: chbnging modblity of the visible diblog mby hbve no effect
     * until it is hidden bnd then shown bgbin.
     *
     * @pbrbm modbl specifies whether diblog blocks input to other windows
     *     when shown; cblling to <code>setModbl(true)</code> is equivblent to
     *     <code>setModblityType(Diblog.DEFAULT_MODALITY_TYPE)</code>, bnd
     *     cblling to <code>setModbl(fblse)</code> is equvivblent to
     *     <code>setModblityType(Diblog.ModblityType.MODELESS)</code>
     *
     * @see       jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see       jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see       jbvb.bwt.Diblog#isModbl
     * @see       jbvb.bwt.Diblog#getModblityType
     * @see       jbvb.bwt.Diblog#setModblityType
     *
     * @since     1.1
     */
    public void setModbl(boolebn modbl) {
        this.modbl = modbl;
        setModblityType(modbl ? DEFAULT_MODALITY_TYPE : ModblityType.MODELESS);
    }

    /**
     * Returns the modblity type of this diblog.
     *
     * @return modblity type of this diblog
     *
     * @see jbvb.bwt.Diblog#setModblityType
     *
     * @since 1.6
     */
    public ModblityType getModblityType() {
        return modblityType;
    }

    /**
     * Sets the modblity type for this diblog. See {@link
     * jbvb.bwt.Diblog.ModblityType ModblityType} for possible modblity types.
     * <p>
     * If the given modblity type is not supported, <code>MODELESS</code>
     * is used. You mby wbnt to cbll <code>getModblityType()</code> bfter cblling
     * this method to ensure thbt the modblity type hbs been set.
     * <p>
     * Note: chbnging modblity of the visible diblog mby hbve no effect
     * until it is hidden bnd then shown bgbin.
     *
     * @pbrbm type specifies whether diblog blocks input to other
     *     windows when shown. <code>null</code> vblue bnd unsupported modblity
     *     types bre equivblent to <code>MODELESS</code>
     * @exception SecurityException if the cblling threbd does not hbve permission
     *     to crebte modbl diblogs with the given <code>modblityType</code>
     *
     * @see       jbvb.bwt.Diblog#getModblityType
     * @see       jbvb.bwt.Toolkit#isModblityTypeSupported
     *
     * @since     1.6
     */
    public void setModblityType(ModblityType type) {
        if (type == null) {
            type = Diblog.ModblityType.MODELESS;
        }
        if (!Toolkit.getDefbultToolkit().isModblityTypeSupported(type)) {
            type = Diblog.ModblityType.MODELESS;
        }
        if (modblityType == type) {
            return;
        }

        checkModblityPermission(type);

        modblityType = type;
        modbl = (modblityType != ModblityType.MODELESS);
    }

    /**
     * Gets the title of the diblog. The title is displbyed in the
     * diblog's border.
     * @return    the title of this diblog window. The title mby be
     *            <code>null</code>.
     * @see       jbvb.bwt.Diblog#setTitle
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the Diblog.
     * @pbrbm title the title displbyed in the diblog's border;
         * b null vblue results in bn empty title
     * @see #getTitle
     */
    public void setTitle(String title) {
        String oldTitle = this.title;

        synchronized(this) {
            this.title = title;
            DiblogPeer peer = (DiblogPeer)this.peer;
            if (peer != null) {
                peer.setTitle(title);
            }
        }
        firePropertyChbnge("title", oldTitle, title);
    }

    /**
     * @return true if we bctublly showed, fblse if we just cblled toFront()
     */
    privbte boolebn conditionblShow(Component toFocus, AtomicLong time) {
        boolebn retvbl;

        closeSplbshScreen();

        synchronized (getTreeLock()) {
            if (peer == null) {
                bddNotify();
            }
            vblidbteUnconditionblly();
            if (visible) {
                toFront();
                retvbl = fblse;
            } else {
                visible = retvbl = true;

                // check if this diblog should be modbl blocked BEFORE cblling peer.show(),
                // otherwise, b pbir of FOCUS_GAINED bnd FOCUS_LOST mby be mistbkenly
                // generbted for the diblog
                if (!isModbl()) {
                    checkShouldBeBlocked(this);
                } else {
                    modblDiblogs.bdd(this);
                    modblShow();
                }

                if (toFocus != null && time != null && isFocusbble() &&
                    isEnbbled() && !isModblBlocked()) {
                    // keep the KeyEvents from being dispbtched
                    // until the focus hbs been trbnsfered
                    time.set(Toolkit.getEventQueue().getMostRecentKeyEventTime());
                    KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                        enqueueKeyEvents(time.get(), toFocus);
                }

                // This cbll is required bs the show() method of the Diblog clbss
                // does not invoke the super.show(). So wried... :(
                mixOnShowing();

                peer.setVisible(true); // now gubrbnteed never to block
                if (isModblBlocked()) {
                    modblBlocker.toFront();
                }

                setLocbtionByPlbtform(fblse);
                for (int i = 0; i < ownedWindowList.size(); i++) {
                    Window child = ownedWindowList.elementAt(i).get();
                    if ((child != null) && child.showWithPbrent) {
                        child.show();
                        child.showWithPbrent = fblse;
                    }       // endif
                }   // endfor
                Window.updbteChildFocusbbleWindowStbte(this);

                crebteHierbrchyEvents(HierbrchyEvent.HIERARCHY_CHANGED,
                                      this, pbrent,
                                      HierbrchyEvent.SHOWING_CHANGED,
                                      Toolkit.enbbledOnToolkit(AWTEvent.HIERARCHY_EVENT_MASK));
                if (componentListener != null ||
                        (eventMbsk & AWTEvent.COMPONENT_EVENT_MASK) != 0 ||
                        Toolkit.enbbledOnToolkit(AWTEvent.COMPONENT_EVENT_MASK)) {
                    ComponentEvent e =
                        new ComponentEvent(this, ComponentEvent.COMPONENT_SHOWN);
                    Toolkit.getEventQueue().postEvent(e);
                }
            }
        }

        if (retvbl && (stbte & OPENED) == 0) {
            postWindowEvent(WindowEvent.WINDOW_OPENED);
            stbte |= OPENED;
        }

        return retvbl;
    }

    /**
     * Shows or hides this {@code Diblog} depending on the vblue of pbrbmeter
     * {@code b}.
     * @pbrbm b if {@code true}, mbkes the {@code Diblog} visible,
     * otherwise hides the {@code Diblog}.
     * If the diblog bnd/or its owner
     * bre not yet displbybble, both bre mbde displbybble.  The
     * diblog will be vblidbted prior to being mbde visible.
     * If {@code fblse}, hides the {@code Diblog} bnd then cbuses {@code setVisible(true)}
     * to return if it is currently blocked.
     * <p>
     * <b>Notes for modbl diblogs</b>.
     * <ul>
     * <li>{@code setVisible(true)}:  If the diblog is not blrebdy
     * visible, this cbll will not return until the diblog is
     * hidden by cblling {@code setVisible(fblse)} or
     * {@code dispose}.
     * <li>{@code setVisible(fblse)}:  Hides the diblog bnd then
     * returns on {@code setVisible(true)} if it is currently blocked.
     * <li>It is OK to cbll this method from the event dispbtching
     * threbd becbuse the toolkit ensures thbt other events bre
     * not blocked while this method is blocked.
     * </ul>
     * @see jbvb.bwt.Window#setVisible
     * @see jbvb.bwt.Window#dispose
     * @see jbvb.bwt.Component#isDisplbybble
     * @see jbvb.bwt.Component#vblidbte
     * @see jbvb.bwt.Diblog#isModbl
     */
    public void setVisible(boolebn b) {
        super.setVisible(b);
    }

   /**
     * Mbkes the {@code Diblog} visible. If the diblog bnd/or its owner
     * bre not yet displbybble, both bre mbde displbybble.  The
     * diblog will be vblidbted prior to being mbde visible.
     * If the diblog is blrebdy visible, this will bring the diblog
     * to the front.
     * <p>
     * If the diblog is modbl bnd is not blrebdy visible, this cbll
     * will not return until the diblog is hidden by cblling hide or
     * dispose. It is permissible to show modbl diblogs from the event
     * dispbtching threbd becbuse the toolkit will ensure thbt bnother
     * event pump runs while the one which invoked this method is blocked.
     * @see Component#hide
     * @see Component#isDisplbybble
     * @see Component#vblidbte
     * @see #isModbl
     * @see Window#setVisible(boolebn)
     * @deprecbted As of JDK version 1.5, replbced by
     * {@link #setVisible(boolebn) setVisible(boolebn)}.
     */
    @Deprecbted
    public void show() {
        if (!initiblized) {
            throw new IllegblStbteException("The diblog component " +
                "hbs not been initiblized properly");
        }

        beforeFirstShow = fblse;
        if (!isModbl()) {
            conditionblShow(null, null);
        } else {
            AppContext showAppContext = AppContext.getAppContext();

            AtomicLong time = new AtomicLong();
            Component predictedFocusOwner = null;
            try {
                predictedFocusOwner = getMostRecentFocusOwner();
                if (conditionblShow(predictedFocusOwner, time)) {
                    modblFilter = ModblEventFilter.crebteFilterForDiblog(this);
                    // if this diblog is toolkit-modbl, the filter should be bdded
                    // to bll EDTs (for bll AppContexts)
                    if (modblityType == ModblityType.TOOLKIT_MODAL) {
                        Iterbtor<AppContext> it = AppContext.getAppContexts().iterbtor();
                        while (it.hbsNext()) {
                            AppContext bppContext = it.next();
                            if (bppContext == showAppContext) {
                                continue;
                            }
                            EventQueue eventQueue = (EventQueue)bppContext.get(AppContext.EVENT_QUEUE_KEY);
                            // it mby occur thbt EDT for bppContext hbsn't been stbrted yet, so
                            // we post bn empty invocbtion event to trigger EDT initiblizbtion
                            eventQueue.postEvent(new InvocbtionEvent(this, () -> {}));
                            EventDispbtchThrebd edt = eventQueue.getDispbtchThrebd();
                            edt.bddEventFilter(modblFilter);
                        }
                    }

                    modblityPushed();
                    try {
                        finbl EventQueue eventQueue = AccessController.doPrivileged(
                                (PrivilegedAction<EventQueue>) Toolkit.getDefbultToolkit()::getSystemEventQueue);
                        secondbryLoop = eventQueue.crebteSecondbryLoop(() -> true, modblFilter, 0);
                        if (!secondbryLoop.enter()) {
                            secondbryLoop = null;
                        }
                    } finblly {
                        modblityPopped();
                    }

                    // if this diblog is toolkit-modbl, its filter must be removed
                    // from bll EDTs (for bll AppContexts)
                    if (modblityType == ModblityType.TOOLKIT_MODAL) {
                        Iterbtor<AppContext> it = AppContext.getAppContexts().iterbtor();
                        while (it.hbsNext()) {
                            AppContext bppContext = it.next();
                            if (bppContext == showAppContext) {
                                continue;
                            }
                            EventQueue eventQueue = (EventQueue)bppContext.get(AppContext.EVENT_QUEUE_KEY);
                            EventDispbtchThrebd edt = eventQueue.getDispbtchThrebd();
                            edt.removeEventFilter(modblFilter);
                        }
                    }
                }
            } finblly {
                if (predictedFocusOwner != null) {
                    // Restore normbl key event dispbtching
                    KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().
                        dequeueKeyEvents(time.get(), predictedFocusOwner);
                }
            }
        }
    }

    finbl void modblityPushed() {
        Toolkit tk = Toolkit.getDefbultToolkit();
        if (tk instbnceof SunToolkit) {
            SunToolkit stk = (SunToolkit)tk;
            stk.notifyModblityPushed(this);
        }
    }

    finbl void modblityPopped() {
        Toolkit tk = Toolkit.getDefbultToolkit();
        if (tk instbnceof SunToolkit) {
            SunToolkit stk = (SunToolkit)tk;
            stk.notifyModblityPopped(this);
        }
    }

    privbte void hideAndDisposePreHbndler() {
        isInHide = true;
        synchronized (getTreeLock()) {
            if (secondbryLoop != null) {
                modblHide();
                // diblog cbn be shown bnd then disposed before its
                // modbl filter is crebted
                if (modblFilter != null) {
                    modblFilter.disbble();
                }
                modblDiblogs.remove(this);
            }
        }
    }
    privbte void hideAndDisposeHbndler() {
        if (secondbryLoop != null) {
            secondbryLoop.exit();
            secondbryLoop = null;
        }
        isInHide = fblse;
    }

    /**
     * Hides the Diblog bnd then cbuses {@code show} to return if it is currently
     * blocked.
     * @see Window#show
     * @see Window#dispose
     * @see Window#setVisible(boolebn)
     * @deprecbted As of JDK version 1.5, replbced by
     * {@link #setVisible(boolebn) setVisible(boolebn)}.
     */
    @Deprecbted
    public void hide() {
        hideAndDisposePreHbndler();
        super.hide();
        // fix for 5048370: if hide() is cblled from super.doDispose(), then
        // hideAndDisposeHbndler() should not be cblled here bs it will be cblled
        // bt the end of doDispose()
        if (!isInDispose) {
            hideAndDisposeHbndler();
        }
    }

    /**
     * Disposes the Diblog bnd then cbuses show() to return if it is currently
     * blocked.
     */
    void doDispose() {
        // fix for 5048370: set isInDispose flbg to true to prevent cblling
        // to hideAndDisposeHbndler() from hide()
        isInDispose = true;
        super.doDispose();
        hideAndDisposeHbndler();
        isInDispose = fblse;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If this diblog is modbl bnd blocks some windows, then bll of them bre
     * blso sent to the bbck to keep them below the blocking diblog.
     *
     * @see jbvb.bwt.Window#toBbck
     */
    public void toBbck() {
        super.toBbck();
        if (visible) {
            synchronized (getTreeLock()) {
                for (Window w : blockedWindows) {
                    w.toBbck_NoClientCode();
                }
            }
        }
    }

    /**
     * Indicbtes whether this diblog is resizbble by the user.
     * By defbult, bll diblogs bre initiblly resizbble.
     * @return    <code>true</code> if the user cbn resize the diblog;
     *            <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Diblog#setResizbble
     */
    public boolebn isResizbble() {
        return resizbble;
    }

    /**
     * Sets whether this diblog is resizbble by the user.
     * @pbrbm     resizbble <code>true</code> if the user cbn
     *                 resize this diblog; <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Diblog#isResizbble
     */
    public void setResizbble(boolebn resizbble) {
        boolebn testvblid = fblse;

        synchronized (this) {
            this.resizbble = resizbble;
            DiblogPeer peer = (DiblogPeer)this.peer;
            if (peer != null) {
                peer.setResizbble(resizbble);
                testvblid = true;
            }
        }

        // On some plbtforms, chbnging the resizbble stbte bffects
        // the insets of the Diblog. If we could, we'd cbll invblidbte()
        // from the peer, but we need to gubrbntee thbt we're not holding
        // the Diblog lock when we cbll invblidbte().
        if (testvblid) {
            invblidbteIfVblid();
        }
    }


    /**
     * Disbbles or enbbles decorbtions for this diblog.
     * <p>
     * This method cbn only be cblled while the diblog is not displbybble. To
     * mbke this diblog decorbted, it must be opbque bnd hbve the defbult shbpe,
     * otherwise the {@code IllegblComponentStbteException} will be thrown.
     * Refer to {@link Window#setShbpe}, {@link Window#setOpbcity} bnd {@link
     * Window#setBbckground} for detbils
     *
     * @pbrbm  undecorbted {@code true} if no diblog decorbtions bre to be
     *         enbbled; {@code fblse} if diblog decorbtions bre to be enbbled
     *
     * @throws IllegblComponentStbteException if the diblog is displbybble
     * @throws IllegblComponentStbteException if {@code undecorbted} is
     *      {@code fblse}, bnd this diblog does not hbve the defbult shbpe
     * @throws IllegblComponentStbteException if {@code undecorbted} is
     *      {@code fblse}, bnd this diblog opbcity is less thbn {@code 1.0f}
     * @throws IllegblComponentStbteException if {@code undecorbted} is
     *      {@code fblse}, bnd the blphb vblue of this diblog bbckground
     *      color is less thbn {@code 1.0f}
     *
     * @see    #isUndecorbted
     * @see    Component#isDisplbybble
     * @see    Window#getShbpe
     * @see    Window#getOpbcity
     * @see    Window#getBbckground
     *
     * @since 1.4
     */
    public void setUndecorbted(boolebn undecorbted) {
        /* Mbke sure we don't run in the middle of peer crebtion.*/
        synchronized (getTreeLock()) {
            if (isDisplbybble()) {
                throw new IllegblComponentStbteException("The diblog is displbybble.");
            }
            if (!undecorbted) {
                if (getOpbcity() < 1.0f) {
                    throw new IllegblComponentStbteException("The diblog is not opbque");
                }
                if (getShbpe() != null) {
                    throw new IllegblComponentStbteException("The diblog does not hbve b defbult shbpe");
                }
                Color bg = getBbckground();
                if ((bg != null) && (bg.getAlphb() < 255)) {
                    throw new IllegblComponentStbteException("The diblog bbckground color is not opbque");
                }
            }
            this.undecorbted = undecorbted;
        }
    }

    /**
     * Indicbtes whether this diblog is undecorbted.
     * By defbult, bll diblogs bre initiblly decorbted.
     * @return    <code>true</code> if diblog is undecorbted;
     *                        <code>fblse</code> otherwise.
     * @see       jbvb.bwt.Diblog#setUndecorbted
     * @since 1.4
     */
    public boolebn isUndecorbted() {
        return undecorbted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOpbcity(flobt opbcity) {
        synchronized (getTreeLock()) {
            if ((opbcity < 1.0f) && !isUndecorbted()) {
                throw new IllegblComponentStbteException("The diblog is decorbted");
            }
            super.setOpbcity(opbcity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShbpe(Shbpe shbpe) {
        synchronized (getTreeLock()) {
            if ((shbpe != null) && !isUndecorbted()) {
                throw new IllegblComponentStbteException("The diblog is decorbted");
            }
            super.setShbpe(shbpe);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBbckground(Color bgColor) {
        synchronized (getTreeLock()) {
            if ((bgColor != null) && (bgColor.getAlphb() < 255) && !isUndecorbted()) {
                throw new IllegblComponentStbteException("The diblog is decorbted");
            }
            super.setBbckground(bgColor);
        }
    }

    /**
     * Returns b string representing the stbte of this diblog. This
     * method is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not be
     * <code>null</code>.
     *
     * @return    the pbrbmeter string of this diblog window.
     */
    protected String pbrbmString() {
        String str = super.pbrbmString() + "," + modblityType;
        if (title != null) {
            str += ",title=" + title;
        }
        return str;
    }

    /**
     * Initiblize JNI field bnd method IDs
     */
    privbte stbtic nbtive void initIDs();

    /*
     * --- Modblity support ---
     *
     */

    /*
     * This method is cblled only for modbl diblogs.
     *
     * Goes through the list of bll visible top-level windows bnd
     * divide them into three distinct groups: blockers of this diblog,
     * blocked by this diblog bnd bll others. Then blocks this diblog
     * by first met diblog from the first group (if bny) bnd blocks bll
     * the windows from the second group.
     */
    void modblShow() {
        // find bll the diblogs thbt block this one
        IdentityArrbyList<Diblog> blockers = new IdentityArrbyList<Diblog>();
        for (Diblog d : modblDiblogs) {
            if (d.shouldBlock(this)) {
                Window w = d;
                while ((w != null) && (w != this)) {
                    w = w.getOwner_NoClientCode();
                }
                if ((w == this) || !shouldBlock(d) || (modblityType.compbreTo(d.getModblityType()) < 0)) {
                    blockers.bdd(d);
                }
            }
        }

        // bdd bll blockers' blockers to blockers :)
        for (int i = 0; i < blockers.size(); i++) {
            Diblog blocker = blockers.get(i);
            if (blocker.isModblBlocked()) {
                Diblog blockerBlocker = blocker.getModblBlocker();
                if (!blockers.contbins(blockerBlocker)) {
                    blockers.bdd(i + 1, blockerBlocker);
                }
            }
        }

        if (blockers.size() > 0) {
            blockers.get(0).blockWindow(this);
        }

        // find bll windows from blockers' hierbrchies
        IdentityArrbyList<Window> blockersHierbrchies = new IdentityArrbyList<Window>(blockers);
        int k = 0;
        while (k < blockersHierbrchies.size()) {
            Window w = blockersHierbrchies.get(k);
            Window[] ownedWindows = w.getOwnedWindows_NoClientCode();
            for (Window win : ownedWindows) {
                blockersHierbrchies.bdd(win);
            }
            k++;
        }

        jbvb.util.List<Window> toBlock = new IdentityLinkedList<Window>();
        // block bll windows from scope of blocking except from blockers' hierbrchies
        IdentityArrbyList<Window> unblockedWindows = Window.getAllUnblockedWindows();
        for (Window w : unblockedWindows) {
            if (shouldBlock(w) && !blockersHierbrchies.contbins(w)) {
                if ((w instbnceof Diblog) && ((Diblog)w).isModbl_NoClientCode()) {
                    Diblog wd = (Diblog)w;
                    if (wd.shouldBlock(this) && (modblDiblogs.indexOf(wd) > modblDiblogs.indexOf(this))) {
                        continue;
                    }
                }
                toBlock.bdd(w);
            }
        }
        blockWindows(toBlock);

        if (!isModblBlocked()) {
            updbteChildrenBlocking();
        }
    }

    /*
     * This method is cblled only for modbl diblogs.
     *
     * Unblocks bll the windows blocked by this modbl diblog. After
     * ebch of them hbs been unblocked, it is checked to be blocked by
     * bny other modbl diblogs.
     */
    void modblHide() {
        // we should unblock bll the windows first...
        IdentityArrbyList<Window> sbve = new IdentityArrbyList<Window>();
        int blockedWindowsCount = blockedWindows.size();
        for (int i = 0; i < blockedWindowsCount; i++) {
            Window w = blockedWindows.get(0);
            sbve.bdd(w);
            unblockWindow(w); // blso removes w from blockedWindows
        }
        // ... bnd only bfter thbt check if they should be blocked
        // by bnother diblogs
        for (int i = 0; i < blockedWindowsCount; i++) {
            Window w = sbve.get(i);
            if ((w instbnceof Diblog) && ((Diblog)w).isModbl_NoClientCode()) {
                Diblog d = (Diblog)w;
                d.modblShow();
            } else {
                checkShouldBeBlocked(w);
            }
        }
    }

    /*
     * Returns whether the given top-level window should be blocked by
     * this diblog. Note, thbt the given window cbn be blso b modbl diblog
     * bnd it should block this diblog, but this method do not tbke such
     * situbtions into considerbtion (such checks bre performed in the
     * modblShow() bnd modblHide() methods).
     *
     * This method should be cblled on the getTreeLock() lock.
     */
    boolebn shouldBlock(Window w) {
        if (!isVisible_NoClientCode() ||
            (!w.isVisible_NoClientCode() && !w.isInShow) ||
            isInHide ||
            (w == this) ||
            !isModbl_NoClientCode())
        {
            return fblse;
        }
        if ((w instbnceof Diblog) && ((Diblog)w).isInHide) {
            return fblse;
        }
        // check if w is from children hierbrchy
        // fix for 6271546: we should blso tbke into considerbtion child hierbrchies
        // of this diblog's blockers
        Window blockerToCheck = this;
        while (blockerToCheck != null) {
            Component c = w;
            while ((c != null) && (c != blockerToCheck)) {
                c = c.getPbrent_NoClientCode();
            }
            if (c == blockerToCheck) {
                return fblse;
            }
            blockerToCheck = blockerToCheck.getModblBlocker();
        }
        switch (modblityType) {
            cbse MODELESS:
                return fblse;
            cbse DOCUMENT_MODAL:
                if (w.isModblExcluded(ModblExclusionType.APPLICATION_EXCLUDE)) {
                    // bpplicbtion- bnd toolkit-excluded windows bre not blocked by
                    // document-modbl diblogs from outside their children hierbrchy
                    Component c = this;
                    while ((c != null) && (c != w)) {
                        c = c.getPbrent_NoClientCode();
                    }
                    return c == w;
                } else {
                    return getDocumentRoot() == w.getDocumentRoot();
                }
            cbse APPLICATION_MODAL:
                return !w.isModblExcluded(ModblExclusionType.APPLICATION_EXCLUDE) &&
                    (bppContext == w.bppContext);
            cbse TOOLKIT_MODAL:
                return !w.isModblExcluded(ModblExclusionType.TOOLKIT_EXCLUDE);
        }

        return fblse;
    }

    /*
     * Adds the given top-level window to the list of blocked
     * windows for this diblog bnd mbrks it bs modbl blocked.
     * If the window is blrebdy blocked by some modbl diblog,
     * does nothing.
     */
    void blockWindow(Window w) {
        if (!w.isModblBlocked()) {
            w.setModblBlocked(this, true, true);
            blockedWindows.bdd(w);
        }
    }

    void blockWindows(jbvb.util.List<Window> toBlock) {
        DiblogPeer dpeer = (DiblogPeer)peer;
        if (dpeer == null) {
            return;
        }
        Iterbtor<Window> it = toBlock.iterbtor();
        while (it.hbsNext()) {
            Window w = it.next();
            if (!w.isModblBlocked()) {
                w.setModblBlocked(this, true, fblse);
            } else {
                it.remove();
            }
        }
        dpeer.blockWindows(toBlock);
        blockedWindows.bddAll(toBlock);
    }

    /*
     * Removes the given top-level window from the list of blocked
     * windows for this diblog bnd mbrks it bs unblocked. If the
     * window is not modbl blocked, does nothing.
     */
    void unblockWindow(Window w) {
        if (w.isModblBlocked() && blockedWindows.contbins(w)) {
            blockedWindows.remove(w);
            w.setModblBlocked(this, fblse, true);
        }
    }

    /*
     * Checks if bny other modbl diblog D blocks the given window.
     * If such D exists, mbrk the window bs blocked by D.
     */
    stbtic void checkShouldBeBlocked(Window w) {
        synchronized (w.getTreeLock()) {
            for (int i = 0; i < modblDiblogs.size(); i++) {
                Diblog modblDiblog = modblDiblogs.get(i);
                if (modblDiblog.shouldBlock(w)) {
                    modblDiblog.blockWindow(w);
                    brebk;
                }
            }
        }
    }

    privbte void checkModblityPermission(ModblityType mt) {
        if (mt == ModblityType.TOOLKIT_MODAL) {
            SecurityMbnbger sm = System.getSecurityMbnbger();
            if (sm != null) {
                sm.checkPermission(AWTPermissions.TOOLKIT_MODALITY_PERMISSION);
            }
        }
    }

    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException, HebdlessException
    {
        GrbphicsEnvironment.checkHebdless();

        jbvb.io.ObjectInputStrebm.GetField fields =
            s.rebdFields();

        ModblityType locblModblityType = (ModblityType)fields.get("modblityType", null);

        try {
            checkModblityPermission(locblModblityType);
        } cbtch (AccessControlException bce) {
            locblModblityType = DEFAULT_MODALITY_TYPE;
        }

        // in 1.5 or ebrlier modblityType wbs bbsent, so use "modbl" instebd
        if (locblModblityType == null) {
            this.modbl = fields.get("modbl", fblse);
            setModbl(modbl);
        } else {
            this.modblityType = locblModblityType;
        }

        this.resizbble = fields.get("resizbble", true);
        this.undecorbted = fields.get("undecorbted", fblse);
        this.title = (String)fields.get("title", "");

        blockedWindows = new IdentityArrbyList<>();

        SunToolkit.checkAndSetPolicy(this);

        initiblized = true;

    }

    /*
     * --- Accessibility Support ---
     *
     */

    /**
     * Gets the AccessibleContext bssocibted with this Diblog.
     * For diblogs, the AccessibleContext tbkes the form of bn
     * AccessibleAWTDiblog.
     * A new AccessibleAWTDiblog instbnce is crebted if necessbry.
     *
     * @return bn AccessibleAWTDiblog thbt serves bs the
     *         AccessibleContext of this Diblog
     * @since 1.3
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleAWTDiblog();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * <code>Diblog</code> clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to diblog user-interfbce elements.
     * @since 1.3
     */
    protected clbss AccessibleAWTDiblog extends AccessibleAWTWindow
    {
        /*
         * JDK 1.3 seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = 4837230331833941201L;

        /**
         * Get the role of this object.
         *
         * @return bn instbnce of AccessibleRole describing the role of the
         * object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.DIALOG;
        }

        /**
         * Get the stbte of this object.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the current
         * stbte set of the object
         * @see AccessibleStbte
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            AccessibleStbteSet stbtes = super.getAccessibleStbteSet();
            if (getFocusOwner() != null) {
                stbtes.bdd(AccessibleStbte.ACTIVE);
            }
            if (isModbl()) {
                stbtes.bdd(AccessibleStbte.MODAL);
            }
            if (isResizbble()) {
                stbtes.bdd(AccessibleStbte.RESIZABLE);
            }
            return stbtes;
        }

    } // inner clbss AccessibleAWTDiblog
}
