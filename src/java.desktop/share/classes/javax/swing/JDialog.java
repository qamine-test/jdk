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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.bccessibility.*;

/**
 * The mbin clbss for crebting b diblog window. You cbn use this clbss
 * to crebte b custom diblog, or invoke the mbny clbss methods
 * in {@link JOptionPbne} to crebte b vbriety of stbndbrd diblogs.
 * For informbtion bbout crebting diblogs, see
 * <em>The Jbvb Tutoribl</em> section
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/diblog.html">How
 * to Mbke Diblogs</b>.
 *
 * <p>
 *
 * The {@code JDiblog} component contbins b {@code JRootPbne}
 * bs its only child.
 * The {@code contentPbne} should be the pbrent of bny children of the
 * {@code JDiblog}.
 * As b convenience, the {@code bdd}, {@code remove}, bnd {@code setLbyout}
 * methods of this clbss bre overridden, so thbt they delegbte cblls
 * to the corresponding methods of the {@code ContentPbne}.
 * For exbmple, you cbn bdd b child component to b diblog bs follows:
 * <pre>
 *       diblog.bdd(child);
 * </pre>
 * And the child will be bdded to the contentPbne.
 * The {@code contentPbne} is blwbys non-{@code null}.
 * Attempting to set it to {@code null} generbtes bn exception.
 * The defbult {@code contentPbne} hbs b {@code BorderLbyout}
 * mbnbger set on it.
 * Refer to {@link jbvbx.swing.RootPbneContbiner}
 * for detbils on bdding, removing bnd setting the {@code LbyoutMbnbger}
 * of b {@code JDiblog}.
 * <p>
 * Plebse see the {@code JRootPbne} documentbtion for b complete
 * description of the {@code contentPbne}, {@code glbssPbne},
 * bnd {@code lbyeredPbne} components.
 * <p>
 * In b multi-screen environment, you cbn crebte b {@code JDiblog}
 * on b different screen device thbn its owner.  See {@link jbvb.bwt.Frbme} for
 * more informbtion.
 * <p>
 * <strong>Wbrning:</strong> Swing is not threbd sbfe. For more
 * informbtion see <b
 * href="pbckbge-summbry.html#threbding">Swing's Threbding
 * Policy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the {@code jbvb.bebns} pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see JOptionPbne
 * @see JRootPbne
 * @see jbvbx.swing.RootPbneContbiner
 *
 * @bebninfo
 *      bttribute: isContbiner true
 *      bttribute: contbinerDelegbte getContentPbne
 *    description: A toplevel window for crebting diblog boxes.
 *
 * @buthor Dbvid Klobb
 * @buthor Jbmes Gosling
 * @buthor Scott Violet
 * @since 1.2
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss JDiblog extends Diblog implements WindowConstbnts,
                                               Accessible,
                                               RootPbneContbiner,
                               TrbnsferHbndler.HbsGetTrbnsferHbndler
{
    /**
     * Key into the AppContext, used to check if should provide decorbtions
     * by defbult.
     */
    privbte stbtic finbl Object defbultLookAndFeelDecorbtedKey =
            new StringBuffer("JDiblog.defbultLookAndFeelDecorbted");

    privbte int defbultCloseOperbtion = HIDE_ON_CLOSE;

    /**
     * @see #getRootPbne
     * @see #setRootPbne
     */
    protected JRootPbne rootPbne;

    /**
     * If true then cblls to {@code bdd} bnd {@code setLbyout}
     * will be forwbrded to the {@code contentPbne}. This is initiblly
     * fblse, but is set to true when the {@code JDiblog} is constructed.
     *
     * @see #isRootPbneCheckingEnbbled
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected boolebn rootPbneCheckingEnbbled = fblse;

    /**
     * The {@code TrbnsferHbndler} for this diblog.
     */
    privbte TrbnsferHbndler trbnsferHbndler;

    /**
     * Crebtes b modeless diblog without b title bnd without b specified
     * {@code Frbme} owner.  A shbred, hidden frbme will be
     * set bs the owner of the diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     * <p>
     * NOTE: This constructor does not bllow you to crebte bn unowned
     * {@code JDiblog}. To crebte bn unowned {@code JDiblog}
     * you must use either the {@code JDiblog(Window)} or
     * {@code JDiblog(Diblog)} constructor with bn brgument of
     * {@code null}.
     *
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog() {
        this((Frbme)null, fblse);
    }

    /**
     * Crebtes b modeless diblog with the specified {@code Frbme}
     * bs its owner bnd bn empty title. If {@code owner}
     * is {@code null}, b shbred, hidden frbme will be set bs the
     * owner of the diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     * <p>
     * NOTE: This constructor does not bllow you to crebte bn unowned
     * {@code JDiblog}. To crebte bn unowned {@code JDiblog}
     * you must use either the {@code JDiblog(Window)} or
     * {@code JDiblog(Diblog)} constructor with bn brgument of
     * {@code null}.
     *
     * @pbrbm owner the {@code Frbme} from which the diblog is displbyed
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Frbme owner) {
        this(owner, fblse);
    }

    /**
     * Crebtes b diblog with bn empty title bnd the specified modblity bnd
     * {@code Frbme} bs its owner. If {@code owner} is {@code null},
     * b shbred, hidden frbme will be set bs the owner of the diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     * <p>
     * NOTE: This constructor does not bllow you to crebte bn unowned
     * {@code JDiblog}. To crebte bn unowned {@code JDiblog}
     * you must use either the {@code JDiblog(Window)} or
     * {@code JDiblog(Diblog)} constructor with bn brgument of
     * {@code null}.
     *
     * @pbrbm owner the {@code Frbme} from which the diblog is displbyed
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modblity type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the diblog is modeless.
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Frbme owner, boolebn modbl) {
        this(owner, "", modbl);
    }

    /**
     * Crebtes b modeless diblog with the specified title bnd
     * with the specified owner frbme.  If {@code owner}
     * is {@code null}, b shbred, hidden frbme will be set bs the
     * owner of the diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     * <p>
     * NOTE: This constructor does not bllow you to crebte bn unowned
     * {@code JDiblog}. To crebte bn unowned {@code JDiblog}
     * you must use either the {@code JDiblog(Window)} or
     * {@code JDiblog(Diblog)} constructor with bn brgument of
     * {@code null}.
     *
     * @pbrbm owner the {@code Frbme} from which the diblog is displbyed
     * @pbrbm title  the {@code String} to displby in the diblog's
     *                  title bbr
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Frbme owner, String title) {
        this(owner, title, fblse);
    }

    /**
     * Crebtes b diblog with the specified title, owner {@code Frbme}
     * bnd modblity. If {@code owner} is {@code null},
     * b shbred, hidden frbme will be set bs the owner of this diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBbr})
     * crebted within b modbl diblog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not bllow you to crebte bn unowned
     * {@code JDiblog}. To crebte bn unowned {@code JDiblog}
     * you must use either the {@code JDiblog(Window)} or
     * {@code JDiblog(Diblog)} constructor with bn brgument of
     * {@code null}.
     *
     * @pbrbm owner the {@code Frbme} from which the diblog is displbyed
     * @pbrbm title  the {@code String} to displby in the diblog's
     *     title bbr
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modblity type property is set to
     *     {@code DEFAULT_MODALITY_TYPE} otherwise the diblog is modeless
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Frbme owner, String title, boolebn modbl) {
        super(owner == null? SwingUtilities.getShbredOwnerFrbme() : owner,
              title, modbl);
        if (owner == null) {
            WindowListener ownerShutdownListener =
                    SwingUtilities.getShbredOwnerFrbmeShutdownListener();
            bddWindowListener(ownerShutdownListener);
        }
        diblogInit();
    }

    /**
     * Crebtes b diblog with the specified title,
     * owner {@code Frbme}, modblity bnd {@code GrbphicsConfigurbtion}.
     * If {@code owner} is {@code null},
     * b shbred, hidden frbme will be set bs the owner of this diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBbr})
     * crebted within b modbl diblog will be forced to be lightweight.
     * <p>
     * NOTE: This constructor does not bllow you to crebte bn unowned
     * {@code JDiblog}. To crebte bn unowned {@code JDiblog}
     * you must use either the {@code JDiblog(Window)} or
     * {@code JDiblog(Diblog)} constructor with bn brgument of
     * {@code null}.
     *
     * @pbrbm owner the {@code Frbme} from which the diblog is displbyed
     * @pbrbm title  the {@code String} to displby in the diblog's
     *     title bbr
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modblity type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the diblog is modeless.
     * @pbrbm gc the {@code GrbphicsConfigurbtion} of the tbrget screen device;
     *     if {@code null}, the defbult system {@code GrbphicsConfigurbtion}
     *     is bssumed
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     * @since 1.4
     */
    public JDiblog(Frbme owner, String title, boolebn modbl,
                   GrbphicsConfigurbtion gc) {
        super(owner == null? SwingUtilities.getShbredOwnerFrbme() : owner,
              title, modbl, gc);
        if (owner == null) {
            WindowListener ownerShutdownListener =
                    SwingUtilities.getShbredOwnerFrbmeShutdownListener();
            bddWindowListener(ownerShutdownListener);
        }
        diblogInit();
    }

    /**
     * Crebtes b modeless diblog with the specified {@code Diblog}
     * bs its owner bnd bn empty title.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the owner {@code Diblog} from which the diblog is displbyed
     *     or {@code null} if this diblog hbs no owner
     * @throws HebdlessException {@code if GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Diblog owner) {
        this(owner, fblse);
    }

    /**
     * Crebtes b diblog with bn empty title bnd the specified modblity bnd
     * {@code Diblog} bs its owner.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the owner {@code Diblog} from which the diblog is displbyed
     *     or {@code null} if this diblog hbs no owner
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modblity type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the diblog is modeless.
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Diblog owner, boolebn modbl) {
        this(owner, "", modbl);
    }

    /**
     * Crebtes b modeless diblog with the specified title bnd
     * with the specified owner diblog.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the owner {@code Diblog} from which the diblog is displbyed
     *     or {@code null} if this diblog hbs no owner
     * @pbrbm title  the {@code String} to displby in the diblog's
     *                  title bbr
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Diblog owner, String title) {
        this(owner, title, fblse);
    }

    /**
     * Crebtes b diblog with the specified title, modblity
     * bnd the specified owner {@code Diblog}.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the owner {@code Diblog} from which the diblog is displbyed
     *     or {@code null} if this diblog hbs no owner
     * @pbrbm title  the {@code String} to displby in the diblog's
     *     title bbr
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modblity type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the diblog is modeless
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     */
    public JDiblog(Diblog owner, String title, boolebn modbl) {
        super(owner, title, modbl);
        diblogInit();
    }

    /**
     * Crebtes b diblog with the specified title, owner {@code Diblog},
     * modblity bnd {@code GrbphicsConfigurbtion}.
     *
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBbr})
     * crebted within b modbl diblog will be forced to be lightweight.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the owner {@code Diblog} from which the diblog is displbyed
     *     or {@code null} if this diblog hbs no owner
     * @pbrbm title  the {@code String} to displby in the diblog's
     *     title bbr
     * @pbrbm modbl specifies whether diblog blocks user input to other top-level
     *     windows when shown. If {@code true}, the modblity type property is set to
     *     {@code DEFAULT_MODALITY_TYPE}, otherwise the diblog is modeless
     * @pbrbm gc the {@code GrbphicsConfigurbtion} of the tbrget screen device;
     *     if {@code null}, the defbult system {@code GrbphicsConfigurbtion}
     *     is bssumed
     * @throws HebdlessException if {@code GrbphicsEnvironment.isHebdless()}
     *     returns {@code true}.
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog.ModblityType#MODELESS
     * @see jbvb.bwt.Diblog#DEFAULT_MODALITY_TYPE
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     * @since 1.4
     */
    public JDiblog(Diblog owner, String title, boolebn modbl,
                   GrbphicsConfigurbtion gc) {
        super(owner, title, modbl, gc);
        diblogInit();
    }

    /**
     * Crebtes b modeless diblog with the specified {@code Window}
     * bs its owner bnd bn empty title.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the {@code Window} from which the diblog is displbyed or
     *     {@code null} if this diblog hbs no owner
     *
     * @throws IllegblArgumentException
     *     if the {@code owner} is not bn instbnce of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbme Frbme}
     * @throws IllegblArgumentException
     *     if the {@code owner}'s {@code GrbphicsConfigurbtion} is not from b screen device
     * @throws HebdlessException
     *     when {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     *
     * @since 1.6
     */
    public JDiblog(Window owner) {
        this(owner, Diblog.ModblityType.MODELESS);
    }

    /**
     * Crebtes b diblog with bn empty title bnd the specified modblity bnd
     * {@code Window} bs its owner.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the {@code Window} from which the diblog is displbyed or
     *     {@code null} if this diblog hbs no owner
     * @pbrbm modblityType specifies whether diblog blocks input to other
     *     windows when shown. {@code null} vblue bnd unsupported modblity
     *     types bre equivblent to {@code MODELESS}
     *
     * @throws IllegblArgumentException
     *     if the {@code owner} is not bn instbnce of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbme Frbme}
     * @throws IllegblArgumentException
     *     if the {@code owner}'s {@code GrbphicsConfigurbtion} is not from b screen device
     * @throws HebdlessException
     *     when {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     * @throws SecurityException
     *     if the cblling threbd does not hbve permission to crebte modbl diblogs
     *     with the given {@code modblityType}
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     *
     * @since 1.6
     */
    public JDiblog(Window owner, ModblityType modblityType) {
        this(owner, "", modblityType);
    }

    /**
     * Crebtes b modeless diblog with the specified title bnd owner
     * {@code Window}.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the {@code Window} from which the diblog is displbyed or
     *     {@code null} if this diblog hbs no owner
     * @pbrbm title the {@code String} to displby in the diblog's
     *     title bbr or {@code null} if the diblog hbs no title
     *
     * @throws IllegblArgumentException
     *     if the {@code owner} is not bn instbnce of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbme Frbme}
     * @throws IllegblArgumentException
     *     if the {@code owner}'s {@code GrbphicsConfigurbtion} is not from b screen device
     * @throws HebdlessException
     *     when {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     *
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     *
     * @since 1.6
     */
    public JDiblog(Window owner, String title) {
        this(owner, title, Diblog.ModblityType.MODELESS);
    }

    /**
     * Crebtes b diblog with the specified title, owner {@code Window} bnd
     * modblity.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the {@code Window} from which the diblog is displbyed or
     *     {@code null} if this diblog hbs no owner
     * @pbrbm title the {@code String} to displby in the diblog's
     *     title bbr or {@code null} if the diblog hbs no title
     * @pbrbm modblityType specifies whether diblog blocks input to other
     *     windows when shown. {@code null} vblue bnd unsupported modblity
     *     types bre equivblent to {@code MODELESS}
     *
     * @throws IllegblArgumentException
     *     if the {@code owner} is not bn instbnce of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbme Frbme}
     * @throws IllegblArgumentException
     *     if the {@code owner}'s {@code GrbphicsConfigurbtion} is not from b screen device
     * @throws HebdlessException
     *     when {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     * @throws SecurityException
     *     if the cblling threbd does not hbve permission to crebte modbl diblogs
     *     with the given {@code modblityType}
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     *
     * @since 1.6
     */
    public JDiblog(Window owner, String title, Diblog.ModblityType modblityType) {
        super(owner, title, modblityType);
        diblogInit();
    }

    /**
     * Crebtes b diblog with the specified title, owner {@code Window},
     * modblity bnd {@code GrbphicsConfigurbtion}.
     * <p>
     * NOTE: Any popup components ({@code JComboBox},
     * {@code JPopupMenu}, {@code JMenuBbr})
     * crebted within b modbl diblog will be forced to be lightweight.
     * <p>
     * This constructor sets the component's locble property to the vblue
     * returned by {@code JComponent.getDefbultLocble}.
     *
     * @pbrbm owner the {@code Window} from which the diblog is displbyed or
     *     {@code null} if this diblog hbs no owner
     * @pbrbm title the {@code String} to displby in the diblog's
     *     title bbr or {@code null} if the diblog hbs no title
     * @pbrbm modblityType specifies whether diblog blocks input to other
     *     windows when shown. {@code null} vblue bnd unsupported modblity
     *     types bre equivblent to {@code MODELESS}
     * @pbrbm gc the {@code GrbphicsConfigurbtion} of the tbrget screen device;
     *     if {@code null}, the defbult system {@code GrbphicsConfigurbtion}
     *     is bssumed
     * @throws IllegblArgumentException
     *     if the {@code owner} is not bn instbnce of {@link jbvb.bwt.Diblog Diblog}
     *     or {@link jbvb.bwt.Frbme Frbme}
     * @throws IllegblArgumentException
     *     if the {@code owner}'s {@code GrbphicsConfigurbtion} is not from b screen device
     * @throws HebdlessException
     *     when {@code GrbphicsEnvironment.isHebdless()} returns {@code true}
     * @throws SecurityException
     *     if the cblling threbd does not hbve permission to crebte modbl diblogs
     *     with the given {@code modblityType}
     *
     * @see jbvb.bwt.Diblog.ModblityType
     * @see jbvb.bwt.Diblog#setModbl
     * @see jbvb.bwt.Diblog#setModblityType
     * @see jbvb.bwt.GrbphicsEnvironment#isHebdless
     * @see JComponent#getDefbultLocble
     *
     * @since 1.6
     */
    public JDiblog(Window owner, String title, Diblog.ModblityType modblityType,
                   GrbphicsConfigurbtion gc) {
        super(owner, title, modblityType, gc);
        diblogInit();
    }

    /**
     * Cblled by the constructors to init the {@code JDiblog} properly.
     */
    protected void diblogInit() {
        enbbleEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.WINDOW_EVENT_MASK);
        setLocble( JComponent.getDefbultLocble() );
        setRootPbne(crebteRootPbne());
        setBbckground(UIMbnbger.getColor("control"));
        setRootPbneCheckingEnbbled(true);
        if (JDiblog.isDefbultLookAndFeelDecorbted()) {
            boolebn supportsWindowDecorbtions =
            UIMbnbger.getLookAndFeel().getSupportsWindowDecorbtions();
            if (supportsWindowDecorbtions) {
                setUndecorbted(true);
                getRootPbne().setWindowDecorbtionStyle(JRootPbne.PLAIN_DIALOG);
            }
        }
        sun.bwt.SunToolkit.checkAndSetPolicy(this);
    }

    /**
     * Cblled by the constructor methods to crebte the defbult
     * {@code rootPbne}.
     *
     * @return  b new {@code JRootPbne}
     */
    protected JRootPbne crebteRootPbne() {
        JRootPbne rp = new JRootPbne();
        // NOTE: this uses setOpbque vs LookAndFeel.instbllProperty bs there
        // is NO rebson for the RootPbne not to be opbque. For pbinting to
        // work the contentPbne must be opbque, therefor the RootPbne cbn
        // blso be opbque.
        rp.setOpbque(true);
        return rp;
    }

    /**
     * Hbndles window events depending on the stbte of the
     * {@code defbultCloseOperbtion} property.
     *
     * @see #setDefbultCloseOperbtion
     */
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            switch(defbultCloseOperbtion) {
              cbse HIDE_ON_CLOSE:
                 setVisible(fblse);
                 brebk;
              cbse DISPOSE_ON_CLOSE:
                 dispose();
                 brebk;
              cbse DO_NOTHING_ON_CLOSE:
                 defbult:
                 brebk;
            }
        }
    }


    /**
     * Sets the operbtion thbt will hbppen by defbult when
     * the user initibtes b "close" on this diblog.
     * You must specify one of the following choices:
     * <br><br>
     * <ul>
     * <li>{@code DO_NOTHING_ON_CLOSE}
     * (defined in {@code WindowConstbnts}):
     * Don't do bnything; require the
     * progrbm to hbndle the operbtion in the {@code windowClosing}
     * method of b registered {@code WindowListener} object.
     *
     * <li>{@code HIDE_ON_CLOSE}
     * (defined in {@code WindowConstbnts}):
     * Autombticblly hide the diblog bfter
     * invoking bny registered {@code WindowListener}
     * objects.
     *
     * <li>{@code DISPOSE_ON_CLOSE}
     * (defined in {@code WindowConstbnts}):
     * Autombticblly hide bnd dispose the
     * diblog bfter invoking bny registered {@code WindowListener}
     * objects.
     * </ul>
     * <p>
     * The vblue is set to {@code HIDE_ON_CLOSE} by defbult. Chbnges
     * to the vblue of this property cbuse the firing of b property
     * chbnge event, with property nbme "defbultCloseOperbtion".
     * <p>
     * <b>Note</b>: When the lbst displbybble window within the
     * Jbvb virtubl mbchine (VM) is disposed of, the VM mby
     * terminbte.  See <b href="../../jbvb/bwt/doc-files/AWTThrebdIssues.html">
     * AWT Threbding Issues</b> for more informbtion.
     *
     * @pbrbm operbtion the operbtion which should be performed when the
     *        user closes the diblog
     * @throws IllegblArgumentException if defbultCloseOperbtion vblue
     *         isn't one of the bbove vblid vblues
     * @see #bddWindowListener
     * @see #getDefbultCloseOperbtion
     * @see WindowConstbnts
     *
     * @bebninfo
     *   preferred: true
     *       bound: true
     *        enum: DO_NOTHING_ON_CLOSE WindowConstbnts.DO_NOTHING_ON_CLOSE
     *              HIDE_ON_CLOSE       WindowConstbnts.HIDE_ON_CLOSE
     *              DISPOSE_ON_CLOSE    WindowConstbnts.DISPOSE_ON_CLOSE
     * description: The diblog's defbult close operbtion.
     */
    public void setDefbultCloseOperbtion(int operbtion) {
        if (operbtion != DO_NOTHING_ON_CLOSE &&
            operbtion != HIDE_ON_CLOSE &&
            operbtion != DISPOSE_ON_CLOSE) {
            throw new IllegblArgumentException("defbultCloseOperbtion must be one of: DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, or DISPOSE_ON_CLOSE");
        }

        int oldVblue = this.defbultCloseOperbtion;
        this.defbultCloseOperbtion = operbtion;
        firePropertyChbnge("defbultCloseOperbtion", oldVblue, operbtion);
    }

   /**
    * Returns the operbtion which occurs when the user
    * initibtes b "close" on this diblog.
    *
    * @return bn integer indicbting the window-close operbtion
    * @see #setDefbultCloseOperbtion
    */
    public int getDefbultCloseOperbtion() {
        return defbultCloseOperbtion;
    }

    /**
     * Sets the {@code trbnsferHbndler} property, which is b mechbnism to
     * support trbnsfer of dbtb into this component. Use {@code null}
     * if the component does not support dbtb trbnsfer operbtions.
     * <p>
     * If the system property {@code suppressSwingDropSupport} is {@code fblse}
     * (the defbult) bnd the current drop tbrget on this component is either
     * {@code null} or not b user-set drop tbrget, this method will chbnge the
     * drop tbrget bs follows: If {@code newHbndler} is {@code null} it will
     * clebr the drop tbrget. If not {@code null} it will instbll b new
     * {@code DropTbrget}.
     * <p>
     * Note: When used with {@code JDiblog}, {@code TrbnsferHbndler} only
     * provides dbtb import cbpbbility, bs the dbtb export relbted methods
     * bre currently typed to {@code JComponent}.
     * <p>
     * Plebse see
     * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/dnd/index.html">
     * How to Use Drbg bnd Drop bnd Dbtb Trbnsfer</b>, b section in
     * <em>The Jbvb Tutoribl</em>, for more informbtion.
     *
     * @pbrbm newHbndler the new {@code TrbnsferHbndler}
     *
     * @see TrbnsferHbndler
     * @see #getTrbnsferHbndler
     * @see jbvb.bwt.Component#setDropTbrget
     * @since 1.6
     *
     * @bebninfo
     *        bound: true
     *       hidden: true
     *  description: Mechbnism for trbnsfer of dbtb into the component
     */
    public void setTrbnsferHbndler(TrbnsferHbndler newHbndler) {
        TrbnsferHbndler oldHbndler = trbnsferHbndler;
        trbnsferHbndler = newHbndler;
        SwingUtilities.instbllSwingDropTbrgetAsNecessbry(this, trbnsferHbndler);
        firePropertyChbnge("trbnsferHbndler", oldHbndler, newHbndler);
    }

    /**
     * Gets the {@code trbnsferHbndler} property.
     *
     * @return the vblue of the {@code trbnsferHbndler} property
     *
     * @see TrbnsferHbndler
     * @see #setTrbnsferHbndler
     * @since 1.6
     */
    public TrbnsferHbndler getTrbnsferHbndler() {
        return trbnsferHbndler;
    }

    /**
     * Cblls {@code pbint(g)}.  This method wbs overridden to
     * prevent bn unnecessbry cbll to clebr the bbckground.
     *
     * @pbrbm g  the {@code Grbphics} context in which to pbint
     */
    public void updbte(Grbphics g) {
        pbint(g);
    }

   /**
    * Sets the menubbr for this diblog.
    *
    * @pbrbm menu the menubbr being plbced in the diblog
    *
    * @see #getJMenuBbr
    *
    * @bebninfo
    *      hidden: true
    * description: The menubbr for bccessing pulldown menus from this diblog.
    */
    public void setJMenuBbr(JMenuBbr menu) {
        getRootPbne().setMenuBbr(menu);
    }

   /**
    * Returns the menubbr set on this diblog.
    *
    * @return the menubbr set on this diblog
    * @see #setJMenuBbr
    */
    public JMenuBbr getJMenuBbr() {
        return getRootPbne().getMenuBbr();
    }


    /**
     * Returns whether cblls to {@code bdd} bnd
     * {@code setLbyout} bre forwbrded to the {@code contentPbne}.
     *
     * @return true if {@code bdd} bnd {@code setLbyout}
     *         bre forwbrded; fblse otherwise
     *
     * @see #bddImpl
     * @see #setLbyout
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected boolebn isRootPbneCheckingEnbbled() {
        return rootPbneCheckingEnbbled;
    }


    /**
     * Sets whether cblls to {@code bdd} bnd
     * {@code setLbyout} bre forwbrded to the {@code contentPbne}.
     *
     * @pbrbm enbbled  true if {@code bdd} bnd {@code setLbyout}
     *        bre forwbrded, fblse if they should operbte directly on the
     *        {@code JDiblog}.
     *
     * @see #bddImpl
     * @see #setLbyout
     * @see #isRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     * @bebninfo
     *      hidden: true
     * description: Whether the bdd bnd setLbyout methods bre forwbrded
     */
    protected void setRootPbneCheckingEnbbled(boolebn enbbled) {
        rootPbneCheckingEnbbled = enbbled;
    }

    /**
     * Adds the specified child {@code Component}.
     * This method is overridden to conditionblly forwbrd cblls to the
     * {@code contentPbne}.
     * By defbult, children bre bdded to the {@code contentPbne} instebd
     * of the frbme, refer to {@link jbvbx.swing.RootPbneContbiner} for
     * detbils.
     *
     * @pbrbm comp the component to be enhbnced
     * @pbrbm constrbints the constrbints to be respected
     * @pbrbm index the index
     * @throws IllegblArgumentException if {@code index} is invblid
     * @throws IllegblArgumentException if bdding the contbiner's pbrent
     *                  to itself
     * @throws IllegblArgumentException if bdding b window to b contbiner
     *
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    protected void bddImpl(Component comp, Object constrbints, int index)
    {
        if(isRootPbneCheckingEnbbled()) {
            getContentPbne().bdd(comp, constrbints, index);
        }
        else {
            super.bddImpl(comp, constrbints, index);
        }
    }

    /**
     * Removes the specified component from the contbiner. If
     * {@code comp} is not the {@code rootPbne}, this will forwbrd
     * the cbll to the {@code contentPbne}. This will do nothing if
     * {@code comp} is not b child of the {@code JDiblog} or
     * {@code contentPbne}.
     *
     * @pbrbm comp the component to be removed
     * @throws NullPointerException if {@code comp} is null
     * @see #bdd
     * @see jbvbx.swing.RootPbneContbiner
     */
    public void remove(Component comp) {
        if (comp == rootPbne) {
            super.remove(comp);
        } else {
            getContentPbne().remove(comp);
        }
    }


    /**
     * Sets the {@code LbyoutMbnbger}.
     * Overridden to conditionblly forwbrd the cbll to the
     * {@code contentPbne}.
     * Refer to {@link jbvbx.swing.RootPbneContbiner} for
     * more informbtion.
     *
     * @pbrbm mbnbger the {@code LbyoutMbnbger}
     * @see #setRootPbneCheckingEnbbled
     * @see jbvbx.swing.RootPbneContbiner
     */
    public void setLbyout(LbyoutMbnbger mbnbger) {
        if(isRootPbneCheckingEnbbled()) {
            getContentPbne().setLbyout(mbnbger);
        }
        else {
            super.setLbyout(mbnbger);
        }
    }


    /**
     * Returns the {@code rootPbne} object for this diblog.
     *
     * @see #setRootPbne
     * @see RootPbneContbiner#getRootPbne
     */
    public JRootPbne getRootPbne() {
        return rootPbne;
    }


    /**
     * Sets the {@code rootPbne} property.
     * This method is cblled by the constructor.
     *
     * @pbrbm root the {@code rootPbne} object for this diblog
     *
     * @see #getRootPbne
     *
     * @bebninfo
     *   hidden: true
     * description: the RootPbne object for this diblog.
     */
    protected void setRootPbne(JRootPbne root) {
        if(rootPbne != null) {
            remove(rootPbne);
        }
        rootPbne = root;
        if(rootPbne != null) {
            boolebn checkingEnbbled = isRootPbneCheckingEnbbled();
            try {
                setRootPbneCheckingEnbbled(fblse);
                bdd(rootPbne, BorderLbyout.CENTER);
            }
            finblly {
                setRootPbneCheckingEnbbled(checkingEnbbled);
            }
        }
    }


    /**
     * Returns the {@code contentPbne} object for this diblog.
     *
     * @return the {@code contentPbne} property
     *
     * @see #setContentPbne
     * @see RootPbneContbiner#getContentPbne
     */
    public Contbiner getContentPbne() {
        return getRootPbne().getContentPbne();
    }


   /**
     * Sets the {@code contentPbne} property.
     * This method is cblled by the constructor.
     * <p>
     * Swing's pbinting brchitecture requires bn opbque {@code JComponent}
     * in the contbinment hierbrchy. This is typicblly provided by the
     * content pbne. If you replbce the content pbne it is recommended you
     * replbce it with bn opbque {@code JComponent}.
     * @see JRootPbne
     *
     * @pbrbm contentPbne the {@code contentPbne} object for this diblog
     *
     * @throws jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the content pbne pbrbmeter is {@code null}
     * @see #getContentPbne
     * @see RootPbneContbiner#setContentPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: The client breb of the diblog where child
     *                  components bre normblly inserted.
     */
    public void setContentPbne(Contbiner contentPbne) {
        getRootPbne().setContentPbne(contentPbne);
    }

    /**
     * Returns the {@code lbyeredPbne} object for this diblog.
     *
     * @return the {@code lbyeredPbne} property
     *
     * @see #setLbyeredPbne
     * @see RootPbneContbiner#getLbyeredPbne
     */
    public JLbyeredPbne getLbyeredPbne() {
        return getRootPbne().getLbyeredPbne();
    }

    /**
     * Sets the {@code lbyeredPbne} property.
     * This method is cblled by the constructor.
     *
     * @pbrbm lbyeredPbne the new {@code lbyeredPbne} property
     *
     * @throws jbvb.bwt.IllegblComponentStbteException (b runtime
     *            exception) if the lbyered pbne pbrbmeter is null
     * @see #getLbyeredPbne
     * @see RootPbneContbiner#setLbyeredPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: The pbne which holds the vbrious diblog lbyers.
     */
    public void setLbyeredPbne(JLbyeredPbne lbyeredPbne) {
        getRootPbne().setLbyeredPbne(lbyeredPbne);
    }

    /**
     * Returns the {@code glbssPbne} object for this diblog.
     *
     * @return the {@code glbssPbne} property
     *
     * @see #setGlbssPbne
     * @see RootPbneContbiner#getGlbssPbne
     */
    public Component getGlbssPbne() {
        return getRootPbne().getGlbssPbne();
    }

    /**
     * Sets the {@code glbssPbne} property.
     * This method is cblled by the constructor.
     *
     * @pbrbm glbssPbne the {@code glbssPbne} object for this diblog
     * @see #getGlbssPbne
     * @see RootPbneContbiner#setGlbssPbne
     *
     * @bebninfo
     *     hidden: true
     *     description: A trbnspbrent pbne used for menu rendering.
     */
    public void setGlbssPbne(Component glbssPbne) {
        getRootPbne().setGlbssPbne(glbssPbne);
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.6
     */
    public Grbphics getGrbphics() {
        JComponent.getGrbphicsInvoked(this);
        return super.getGrbphics();
    }

    /**
     * Repbints the specified rectbngle of this component within
     * {@code time} milliseconds.  Refer to {@code RepbintMbnbger}
     * for detbils on how the repbint is hbndled.
     *
     * @pbrbm     time   mbximum time in milliseconds before updbte
     * @pbrbm     x    the <i>x</i> coordinbte
     * @pbrbm     y    the <i>y</i> coordinbte
     * @pbrbm     width    the width
     * @pbrbm     height   the height
     * @see       RepbintMbnbger
     * @since     1.6
     */
    public void repbint(long time, int x, int y, int width, int height) {
        if (RepbintMbnbger.HANDLE_TOP_LEVEL_PAINT) {
            RepbintMbnbger.currentMbnbger(this).bddDirtyRegion(
                              this, x, y, width, height);
        }
        else {
            super.repbint(time, x, y, width, height);
        }
    }

    /**
     * Provides b hint bs to whether or not newly crebted {@code JDiblog}s
     * should hbve their Window decorbtions (such bs borders, widgets to
     * close the window, title...) provided by the current look
     * bnd feel. If {@code defbultLookAndFeelDecorbted} is true,
     * the current {@code LookAndFeel} supports providing window
     * decorbtions, bnd the current window mbnbger supports undecorbted
     * windows, then newly crebted {@code JDiblog}s will hbve their
     * Window decorbtions provided by the current {@code LookAndFeel}.
     * Otherwise, newly crebted {@code JDiblog}s will hbve their
     * Window decorbtions provided by the current window mbnbger.
     * <p>
     * You cbn get the sbme effect on b single JDiblog by doing the following:
     * <pre>
     *    JDiblog diblog = new JDiblog();
     *    diblog.setUndecorbted(true);
     *    diblog.getRootPbne().setWindowDecorbtionStyle(JRootPbne.PLAIN_DIALOG);
     * </pre>
     *
     * @pbrbm defbultLookAndFeelDecorbted A hint bs to whether or not current
     *        look bnd feel should provide window decorbtions
     * @see jbvbx.swing.LookAndFeel#getSupportsWindowDecorbtions
     * @since 1.4
     */
    public stbtic void setDefbultLookAndFeelDecorbted(boolebn defbultLookAndFeelDecorbted) {
        if (defbultLookAndFeelDecorbted) {
            SwingUtilities.bppContextPut(defbultLookAndFeelDecorbtedKey, Boolebn.TRUE);
        } else {
            SwingUtilities.bppContextPut(defbultLookAndFeelDecorbtedKey, Boolebn.FALSE);
        }
    }

    /**
     * Returns true if newly crebted {@code JDiblog}s should hbve their
     * Window decorbtions provided by the current look bnd feel. This is only
     * b hint, bs certbin look bnd feels mby not support this febture.
     *
     * @return true if look bnd feel should provide Window decorbtions.
     * @since 1.4
     */
    public stbtic boolebn isDefbultLookAndFeelDecorbted() {
        Boolebn defbultLookAndFeelDecorbted =
            (Boolebn) SwingUtilities.bppContextGet(defbultLookAndFeelDecorbtedKey);
        if (defbultLookAndFeelDecorbted == null) {
            defbultLookAndFeelDecorbted = Boolebn.FALSE;
        }
        return defbultLookAndFeelDecorbted.boolebnVblue();
    }

    /**
     * Returns b string representbtion of this {@code JDiblog}.
     * This method
     * is intended to be used only for debugging purposes, bnd the
     * content bnd formbt of the returned string mby vbry between
     * implementbtions. The returned string mby be empty but mby not
     * be {@code null}.
     *
     * @return  b string representbtion of this {@code JDiblog}.
     */
    protected String pbrbmString() {
        String defbultCloseOperbtionString;
        if (defbultCloseOperbtion == HIDE_ON_CLOSE) {
            defbultCloseOperbtionString = "HIDE_ON_CLOSE";
        } else if (defbultCloseOperbtion == DISPOSE_ON_CLOSE) {
            defbultCloseOperbtionString = "DISPOSE_ON_CLOSE";
        } else if (defbultCloseOperbtion == DO_NOTHING_ON_CLOSE) {
            defbultCloseOperbtionString = "DO_NOTHING_ON_CLOSE";
        } else defbultCloseOperbtionString = "";
        String rootPbneString = (rootPbne != null ?
                                 rootPbne.toString() : "");
        String rootPbneCheckingEnbbledString = (rootPbneCheckingEnbbled ?
                                                "true" : "fblse");

        return super.pbrbmString() +
        ",defbultCloseOperbtion=" + defbultCloseOperbtionString +
        ",rootPbne=" + rootPbneString +
        ",rootPbneCheckingEnbbled=" + rootPbneCheckingEnbbledString;
    }


/////////////////
// Accessibility support
////////////////

    /**
     * {@code AccessibleContext} bssocibted with this {@code JDiblog}
     */
    protected AccessibleContext bccessibleContext = null;

    /**
     * Gets the AccessibleContext bssocibted with this JDiblog.
     * For JDiblogs, the AccessibleContext tbkes the form of bn
     * AccessibleJDiblog.
     * A new AccessibleJDiblog instbnce is crebted if necessbry.
     *
     * @return bn AccessibleJDiblog thbt serves bs the
     *         AccessibleContext of this JDiblog
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleJDiblog();
        }
        return bccessibleContext;
    }

    /**
     * This clbss implements bccessibility support for the
     * {@code JDiblog} clbss.  It provides bn implementbtion of the
     * Jbvb Accessibility API bppropribte to diblog user-interfbce
     * elements.
     */
    protected clbss AccessibleJDiblog extends AccessibleAWTDiblog {

        // AccessibleContext methods
        //
        /**
         * Get the bccessible nbme of this object.
         *
         * @return the locblized nbme of the object -- cbn be null if this
         * object does not hbve b nbme
         */
        public String getAccessibleNbme() {
            if (bccessibleNbme != null) {
                return bccessibleNbme;
            } else {
                if (getTitle() == null) {
                    return super.getAccessibleNbme();
                } else {
                    return getTitle();
                }
            }
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

            if (isResizbble()) {
                stbtes.bdd(AccessibleStbte.RESIZABLE);
            }
            if (getFocusOwner() != null) {
                stbtes.bdd(AccessibleStbte.ACTIVE);
            }
            if (isModbl()) {
                stbtes.bdd(AccessibleStbte.MODAL);
            }
            return stbtes;
        }

    } // inner clbss AccessibleJDiblog
}
