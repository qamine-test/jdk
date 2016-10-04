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
pbckbge jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.dbtbtrbnsfer.*;
import jbvb.bwt.dnd.*;
import jbvb.bebns.*;
import jbvb.lbng.reflect.*;
import jbvb.io.*;
import jbvb.util.TooMbnyListenersException;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.event.*;
import jbvbx.swing.text.JTextComponent;

import sun.reflect.misc.MethodUtil;
import sun.swing.SwingUtilities2;
import sun.bwt.AppContext;
import sun.swing.*;
import sun.bwt.SunToolkit;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.security.AccessControlContext;
import jbvb.security.ProtectionDombin;
import sun.misc.ShbredSecrets;
import sun.misc.JbvbSecurityAccess;

import sun.bwt.AWTAccessor;

/**
 * This clbss is used to hbndle the trbnsfer of b <code>Trbnsferbble</code>
 * to bnd from Swing components.  The <code>Trbnsferbble</code> is used to
 * represent dbtb thbt is exchbnged vib b cut, copy, or pbste
 * to/from b clipbobrd.  It is blso used in drbg-bnd-drop operbtions
 * to represent b drbg from b component, bnd b drop to b component.
 * Swing provides functionblity thbt butombticblly supports cut, copy,
 * bnd pbste keybobrd bindings thbt use the functionblity provided by
 * bn implementbtion of this clbss.  Swing blso provides functionblity
 * thbt butombticblly supports drbg bnd drop thbt uses the functionblity
 * provided by bn implementbtion of this clbss.  The Swing developer cbn
 * concentrbte on specifying the sembntics of b trbnsfer primbrily by setting
 * the <code>trbnsferHbndler</code> property on b Swing component.
 * <p>
 * This clbss is implemented to provide b defbult behbvior of trbnsferring
 * b component property simply by specifying the nbme of the property in
 * the constructor.  For exbmple, to trbnsfer the foreground color from
 * one component to bnother either vib the clipbobrd or b drbg bnd drop operbtion
 * b <code>TrbnsferHbndler</code> cbn be constructed with the string "foreground".  The
 * built in support will use the color returned by <code>getForeground</code> bs the source
 * of the trbnsfer, bnd <code>setForeground</code> for the tbrget of b trbnsfer.
 * <p>
 * Plebse see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/dnd/index.html">
 * How to Use Drbg bnd Drop bnd Dbtb Trbnsfer</b>,
 * b section in <em>The Jbvb Tutoribl</em>, for more informbtion.
 *
 *
 * @buthor Timothy Prinzing
 * @buthor Shbnnon Hickey
 * @since 1.4
 */
@SuppressWbrnings("seribl")
public clbss TrbnsferHbndler implements Seriblizbble {

    /**
     * An <code>int</code> representing no trbnsfer bction.
     */
    public stbtic finbl int NONE = DnDConstbnts.ACTION_NONE;

    /**
     * An <code>int</code> representing b &quot;copy&quot; trbnsfer bction.
     * This vblue is used when dbtb is copied to b clipbobrd
     * or copied elsewhere in b drbg bnd drop operbtion.
     */
    public stbtic finbl int COPY = DnDConstbnts.ACTION_COPY;

    /**
     * An <code>int</code> representing b &quot;move&quot; trbnsfer bction.
     * This vblue is used when dbtb is moved to b clipbobrd (i.e. b cut)
     * or moved elsewhere in b drbg bnd drop operbtion.
     */
    public stbtic finbl int MOVE = DnDConstbnts.ACTION_MOVE;

    /**
     * An <code>int</code> representing b source bction cbpbbility of either
     * &quot;copy&quot; or &quot;move&quot;.
     */
    public stbtic finbl int COPY_OR_MOVE = DnDConstbnts.ACTION_COPY_OR_MOVE;

    /**
     * An <code>int</code> representing b &quot;link&quot; trbnsfer bction.
     * This vblue is used to specify thbt dbtb should be linked in b drbg
     * bnd drop operbtion.
     *
     * @see jbvb.bwt.dnd.DnDConstbnts#ACTION_LINK
     * @since 1.6
     */
    public stbtic finbl int LINK = DnDConstbnts.ACTION_LINK;

    /**
     * An interfbce to tbg things with b {@code getTrbnsferHbndler} method.
     */
    interfbce HbsGetTrbnsferHbndler {

        /** Returns the {@code TrbnsferHbndler}.
         *
         * @return The {@code TrbnsferHbndler} or {@code null}
         */
        public TrbnsferHbndler getTrbnsferHbndler();
    }

    /**
     * Represents b locbtion where dropped dbtb should be inserted.
     * This is b bbse clbss thbt only encbpsulbtes b point.
     * Components supporting drop mby provide subclbsses of this
     * contbining more informbtion.
     * <p>
     * Developers typicblly shouldn't crebte instbnces of, or extend, this
     * clbss. Instebd, these bre something provided by the DnD
     * implementbtion by <code>TrbnsferSupport</code> instbnces bnd by
     * components with b <code>getDropLocbtion()</code> method.
     *
     * @see jbvbx.swing.TrbnsferHbndler.TrbnsferSupport#getDropLocbtion
     * @since 1.6
     */
    public stbtic clbss DropLocbtion {
        privbte finbl Point dropPoint;

        /**
         * Constructs b drop locbtion for the given point.
         *
         * @pbrbm dropPoint the drop point, representing the mouse's
         *        current locbtion within the component.
         * @throws IllegblArgumentException if the point
         *         is <code>null</code>
         */
        protected DropLocbtion(Point dropPoint) {
            if (dropPoint == null) {
                throw new IllegblArgumentException("Point cbnnot be null");
            }

            this.dropPoint = new Point(dropPoint);
        }

        /**
         * Returns the drop point, representing the mouse's
         * current locbtion within the component.
         *
         * @return the drop point.
         */
        public finbl Point getDropPoint() {
            return new Point(dropPoint);
        }

        /**
         * Returns b string representbtion of this drop locbtion.
         * This method is intended to be used for debugging purposes,
         * bnd the content bnd formbt of the returned string mby vbry
         * between implementbtions.
         *
         * @return b string representbtion of this drop locbtion
         */
        public String toString() {
            return getClbss().getNbme() + "[dropPoint=" + dropPoint + "]";
        }
    };

    /**
     * This clbss encbpsulbtes bll relevbnt detbils of b clipbobrd
     * or drbg bnd drop trbnsfer, bnd blso bllows for customizing
     * bspects of the drbg bnd drop experience.
     * <p>
     * The mbin purpose of this clbss is to provide the informbtion
     * needed by b developer to determine the suitbbility of b
     * trbnsfer or to import the dbtb contbined within. But it blso
     * doubles bs b controller for customizing properties during drbg
     * bnd drop, such bs whether or not to show the drop locbtion,
     * bnd which drop bction to use.
     * <p>
     * Developers typicblly need not crebte instbnces of this
     * clbss. Instebd, they bre something provided by the DnD
     * implementbtion to certbin methods in <code>TrbnsferHbndler</code>.
     *
     * @see #cbnImport(TrbnsferHbndler.TrbnsferSupport)
     * @see #importDbtb(TrbnsferHbndler.TrbnsferSupport)
     * @since 1.6
     */
    public finbl stbtic clbss TrbnsferSupport {
        privbte boolebn isDrop;
        privbte Component component;

        privbte boolebn showDropLocbtionIsSet;
        privbte boolebn showDropLocbtion;

        privbte int dropAction = -1;

        /**
         * The source is b {@code DropTbrgetDrbgEvent} or
         * {@code DropTbrgetDropEvent} for drops,
         * bnd b {@code Trbnsferbble} otherwise
         */
        privbte Object source;

        privbte DropLocbtion dropLocbtion;

        /**
         * Crebte b <code>TrbnsferSupport</code> with <code>isDrop()</code>
         * <code>true</code> for the given component, event, bnd index.
         *
         * @pbrbm component the tbrget component
         * @pbrbm event b <code>DropTbrgetEvent</code>
         */
        privbte TrbnsferSupport(Component component,
                             DropTbrgetEvent event) {

            isDrop = true;
            setDNDVbribbles(component, event);
        }

        /**
         * Crebte b <code>TrbnsferSupport</code> with <code>isDrop()</code>
         * <code>fblse</code> for the given component bnd
         * <code>Trbnsferbble</code>.
         *
         * @pbrbm component the tbrget component
         * @pbrbm trbnsferbble the trbnsferbble
         * @throws NullPointerException if either pbrbmeter
         *         is <code>null</code>
         */
        public TrbnsferSupport(Component component, Trbnsferbble trbnsferbble) {
            if (component == null) {
                throw new NullPointerException("component is null");
            }

            if (trbnsferbble == null) {
                throw new NullPointerException("trbnsferbble is null");
            }

            isDrop = fblse;
            this.component = component;
            this.source = trbnsferbble;
        }

        /**
         * Allows for b single instbnce to be reused during DnD.
         *
         * @pbrbm component the tbrget component
         * @pbrbm event b <code>DropTbrgetEvent</code>
         */
        privbte void setDNDVbribbles(Component component,
                                     DropTbrgetEvent event) {

            bssert isDrop;

            this.component = component;
            this.source = event;
            dropLocbtion = null;
            dropAction = -1;
            showDropLocbtionIsSet = fblse;

            if (source == null) {
                return;
            }

            bssert source instbnceof DropTbrgetDrbgEvent ||
                   source instbnceof DropTbrgetDropEvent;

            Point p = source instbnceof DropTbrgetDrbgEvent
                          ? ((DropTbrgetDrbgEvent)source).getLocbtion()
                          : ((DropTbrgetDropEvent)source).getLocbtion();

            if (SunToolkit.isInstbnceOf(component, "jbvbx.swing.text.JTextComponent")) {
                dropLocbtion = SwingAccessor.getJTextComponentAccessor().
                                   dropLocbtionForPoint((JTextComponent)component, p);
            } else if (component instbnceof JComponent) {
                dropLocbtion = ((JComponent)component).dropLocbtionForPoint(p);
            }

            /*
             * The drop locbtion mby be null bt this point if the component
             * doesn't return custom drop locbtions. In this cbse, b point-only
             * drop locbtion will be crebted lbzily when requested.
             */
        }

        /**
         * Returns whether or not this <code>TrbnsferSupport</code>
         * represents b drop operbtion.
         *
         * @return <code>true</code> if this is b drop operbtion,
         *         <code>fblse</code> otherwise.
         */
        public boolebn isDrop() {
            return isDrop;
        }

        /**
         * Returns the tbrget component of this trbnsfer.
         *
         * @return the tbrget component
         */
        public Component getComponent() {
            return component;
        }

        /**
         * Checks thbt this is b drop bnd throws bn
         * {@code IllegblStbteException} if it isn't.
         *
         * @throws IllegblStbteException if {@code isDrop} is fblse.
         */
        privbte void bssureIsDrop() {
            if (!isDrop) {
                throw new IllegblStbteException("Not b drop");
            }
        }

        /**
         * Returns the current (non-{@code null}) drop locbtion for the component,
         * when this {@code TrbnsferSupport} represents b drop.
         * <p>
         * Note: For components with built-in drop support, this locbtion
         * will be b subclbss of {@code DropLocbtion} of the sbme type
         * returned by thbt component's {@code getDropLocbtion} method.
         * <p>
         * This method is only for use with drbg bnd drop trbnsfers.
         * Cblling it when {@code isDrop()} is {@code fblse} results
         * in bn {@code IllegblStbteException}.
         *
         * @return the drop locbtion
         * @throws IllegblStbteException if this is not b drop
         * @see #isDrop()
         */
        public DropLocbtion getDropLocbtion() {
            bssureIsDrop();

            if (dropLocbtion == null) {
                /*
                 * component didn't give us b custom drop locbtion,
                 * so lbzily crebte b point-only locbtion
                 */
                Point p = source instbnceof DropTbrgetDrbgEvent
                              ? ((DropTbrgetDrbgEvent)source).getLocbtion()
                              : ((DropTbrgetDropEvent)source).getLocbtion();

                dropLocbtion = new DropLocbtion(p);
            }

            return dropLocbtion;
        }

        /**
         * Sets whether or not the drop locbtion should be visublly indicbted
         * for the trbnsfer - which must represent b drop. This is bpplicbble to
         * those components thbt butombticblly
         * show the drop locbtion when bppropribte during b drbg bnd drop
         * operbtion). By defbult, the drop locbtion is shown only when the
         * {@code TrbnsferHbndler} hbs sbid it cbn bccept the import represented
         * by this {@code TrbnsferSupport}. With this method you cbn force the
         * drop locbtion to blwbys be shown, or blwbys not be shown.
         * <p>
         * This method is only for use with drbg bnd drop trbnsfers.
         * Cblling it when {@code isDrop()} is {@code fblse} results
         * in bn {@code IllegblStbteException}.
         *
         * @pbrbm showDropLocbtion whether or not to indicbte the drop locbtion
         * @throws IllegblStbteException if this is not b drop
         * @see #isDrop()
         */
        public void setShowDropLocbtion(boolebn showDropLocbtion) {
            bssureIsDrop();

            this.showDropLocbtion = showDropLocbtion;
            this.showDropLocbtionIsSet = true;
        }

        /**
         * Sets the drop bction for the trbnsfer - which must represent b drop
         * - to the given bction,
         * instebd of the defbult user drop bction. The bction must be
         * supported by the source's drop bctions, bnd must be one
         * of {@code COPY}, {@code MOVE} or {@code LINK}.
         * <p>
         * This method is only for use with drbg bnd drop trbnsfers.
         * Cblling it when {@code isDrop()} is {@code fblse} results
         * in bn {@code IllegblStbteException}.
         *
         * @pbrbm dropAction the drop bction
         * @throws IllegblStbteException if this is not b drop
         * @throws IllegblArgumentException if bn invblid bction is specified
         * @see #getDropAction
         * @see #getUserDropAction
         * @see #getSourceDropActions
         * @see #isDrop()
         */
        public void setDropAction(int dropAction) {
            bssureIsDrop();

            int bction = dropAction & getSourceDropActions();

            if (!(bction == COPY || bction == MOVE || bction == LINK)) {
                throw new IllegblArgumentException("unsupported drop bction: " + dropAction);
            }

            this.dropAction = dropAction;
        }

        /**
         * Returns the bction chosen for the drop, when this
         * {@code TrbnsferSupport} represents b drop.
         * <p>
         * Unless explicitly chosen by wby of {@code setDropAction},
         * this returns the user drop bction provided by
         * {@code getUserDropAction}.
         * <p>
         * You mby wish to query this in {@code TrbnsferHbndler}'s
         * {@code importDbtb} method to customize processing bbsed
         * on the bction.
         * <p>
         * This method is only for use with drbg bnd drop trbnsfers.
         * Cblling it when {@code isDrop()} is {@code fblse} results
         * in bn {@code IllegblStbteException}.
         *
         * @return the bction chosen for the drop
         * @throws IllegblStbteException if this is not b drop
         * @see #setDropAction
         * @see #getUserDropAction
         * @see #isDrop()
         */
        public int getDropAction() {
            return dropAction == -1 ? getUserDropAction() : dropAction;
        }

        /**
         * Returns the user drop bction for the drop, when this
         * {@code TrbnsferSupport} represents b drop.
         * <p>
         * The user drop bction is chosen for b drop bs described in the
         * documentbtion for {@link jbvb.bwt.dnd.DropTbrgetDrbgEvent} bnd
         * {@link jbvb.bwt.dnd.DropTbrgetDropEvent}. A different bction
         * mby be chosen bs the drop bction by wby of the {@code setDropAction}
         * method.
         * <p>
         * You mby wish to query this in {@code TrbnsferHbndler}'s
         * {@code cbnImport} method when determining the suitbbility of b
         * drop or when deciding on b drop bction to explicitly choose.
         * <p>
         * This method is only for use with drbg bnd drop trbnsfers.
         * Cblling it when {@code isDrop()} is {@code fblse} results
         * in bn {@code IllegblStbteException}.
         *
         * @return the user drop bction
         * @throws IllegblStbteException if this is not b drop
         * @see #setDropAction
         * @see #getDropAction
         * @see #isDrop()
         */
        public int getUserDropAction() {
            bssureIsDrop();

            return (source instbnceof DropTbrgetDrbgEvent)
                ? ((DropTbrgetDrbgEvent)source).getDropAction()
                : ((DropTbrgetDropEvent)source).getDropAction();
        }

        /**
         * Returns the drbg source's supported drop bctions, when this
         * {@code TrbnsferSupport} represents b drop.
         * <p>
         * The source bctions represent the set of bctions supported by the
         * source of this trbnsfer, bnd bre represented bs some bitwise-OR
         * combinbtion of {@code COPY}, {@code MOVE} bnd {@code LINK}.
         * You mby wish to query this in {@code TrbnsferHbndler}'s
         * {@code cbnImport} method when determining the suitbbility of b drop
         * or when deciding on b drop bction to explicitly choose. To determine
         * if b pbrticulbr bction is supported by the source, bitwise-AND
         * the bction with the source drop bctions, bnd then compbre the result
         * bgbinst the originbl bction. For exbmple:
         * <pre>
         * boolebn copySupported = (COPY &bmp; getSourceDropActions()) == COPY;
         * </pre>
         * <p>
         * This method is only for use with drbg bnd drop trbnsfers.
         * Cblling it when {@code isDrop()} is {@code fblse} results
         * in bn {@code IllegblStbteException}.
         *
         * @return the drbg source's supported drop bctions
         * @throws IllegblStbteException if this is not b drop
         * @see #isDrop()
         */
        public int getSourceDropActions() {
            bssureIsDrop();

            return (source instbnceof DropTbrgetDrbgEvent)
                ? ((DropTbrgetDrbgEvent)source).getSourceActions()
                : ((DropTbrgetDropEvent)source).getSourceActions();
        }

        /**
         * Returns the dbtb flbvors for this trbnsfer.
         *
         * @return the dbtb flbvors for this trbnsfer
         */
        public DbtbFlbvor[] getDbtbFlbvors() {
            if (isDrop) {
                if (source instbnceof DropTbrgetDrbgEvent) {
                    return ((DropTbrgetDrbgEvent)source).getCurrentDbtbFlbvors();
                } else {
                    return ((DropTbrgetDropEvent)source).getCurrentDbtbFlbvors();
                }
            }

            return ((Trbnsferbble)source).getTrbnsferDbtbFlbvors();
        }

        /**
         * Returns whether or not the given dbtb flbvor is supported.
         *
         * @pbrbm df the <code>DbtbFlbvor</code> to test
         * @return whether or not the given flbvor is supported.
         */
        public boolebn isDbtbFlbvorSupported(DbtbFlbvor df) {
            if (isDrop) {
                if (source instbnceof DropTbrgetDrbgEvent) {
                    return ((DropTbrgetDrbgEvent)source).isDbtbFlbvorSupported(df);
                } else {
                    return ((DropTbrgetDropEvent)source).isDbtbFlbvorSupported(df);
                }
            }

            return ((Trbnsferbble)source).isDbtbFlbvorSupported(df);
        }

        /**
         * Returns the <code>Trbnsferbble</code> bssocibted with this trbnsfer.
         * <p>
         * Note: Unless it is necessbry to fetch the <code>Trbnsferbble</code>
         * directly, use one of the other methods on this clbss to inquire bbout
         * the trbnsfer. This mby perform better thbn fetching the
         * <code>Trbnsferbble</code> bnd bsking it directly.
         *
         * @return the <code>Trbnsferbble</code> bssocibted with this trbnsfer
         */
        public Trbnsferbble getTrbnsferbble() {
            if (isDrop) {
                if (source instbnceof DropTbrgetDrbgEvent) {
                    return ((DropTbrgetDrbgEvent)source).getTrbnsferbble();
                } else {
                    return ((DropTbrgetDropEvent)source).getTrbnsferbble();
                }
            }

            return (Trbnsferbble)source;
        }
    }


    /**
     * Returns bn {@code Action} thbt performs cut operbtions to the
     * clipbobrd. When performed, this bction operbtes on the {@code JComponent}
     * source of the {@code ActionEvent} by invoking {@code exportToClipbobrd},
     * with b {@code MOVE} bction, on the component's {@code TrbnsferHbndler}.
     *
     * @return bn {@code Action} for performing cuts to the clipbobrd
     */
    public stbtic Action getCutAction() {
        return cutAction;
    }

    /**
     * Returns bn {@code Action} thbt performs copy operbtions to the
     * clipbobrd. When performed, this bction operbtes on the {@code JComponent}
     * source of the {@code ActionEvent} by invoking {@code exportToClipbobrd},
     * with b {@code COPY} bction, on the component's {@code TrbnsferHbndler}.
     *
     * @return bn {@code Action} for performing copies to the clipbobrd
     */
    public stbtic Action getCopyAction() {
        return copyAction;
    }

    /**
     * Returns bn {@code Action} thbt performs pbste operbtions from the
     * clipbobrd. When performed, this bction operbtes on the {@code JComponent}
     * source of the {@code ActionEvent} by invoking {@code importDbtb},
     * with the clipbobrd contents, on the component's {@code TrbnsferHbndler}.
     *
     * @return bn {@code Action} for performing pbstes from the clipbobrd
     */
    public stbtic Action getPbsteAction() {
        return pbsteAction;
    }


    /**
     * Constructs b trbnsfer hbndler thbt cbn trbnsfer b Jbvb Bebn property
     * from one component to bnother vib the clipbobrd or b drbg bnd drop
     * operbtion.
     *
     * @pbrbm property  the nbme of the property to trbnsfer; this cbn
     *  be <code>null</code> if there is no property bssocibted with the trbnsfer
     *  hbndler (b subclbss thbt performs some other kind of trbnsfer, for exbmple)
     */
    public TrbnsferHbndler(String property) {
        propertyNbme = property;
    }

    /**
     * Convenience constructor for subclbsses.
     */
    protected TrbnsferHbndler() {
        this(null);
    }


    /**
     * imbge for the {@code stbrtDrbg} method
     *
     * @see jbvb.bwt.dnd.DrbgGestureEvent#stbrtDrbg(Cursor drbgCursor, Imbge drbgImbge, Point imbgeOffset, Trbnsferbble trbnsferbble, DrbgSourceListener dsl)
     */
    privbte  Imbge drbgImbge;

    /**
     * bnchor offset for the {@code stbrtDrbg} method
     *
     * @see jbvb.bwt.dnd.DrbgGestureEvent#stbrtDrbg(Cursor drbgCursor, Imbge drbgImbge, Point imbgeOffset, Trbnsferbble trbnsferbble, DrbgSourceListener dsl)
     */
    privbte  Point drbgImbgeOffset;

    /**
     * Sets the drbg imbge pbrbmeter. The imbge hbs to be prepbred
     * for rendering by the moment of the cbll. The imbge is stored
     * by reference becbuse of some performbnce rebsons.
     *
     * @pbrbm img bn imbge to drbg
     */
    public void setDrbgImbge(Imbge img) {
        drbgImbge = img;
    }

    /**
     * Returns the drbg imbge. If there is no imbge to drbg,
     * the returned vblue is {@code null}.
     *
     * @return the reference to the drbg imbge
     */
    public Imbge getDrbgImbge() {
        return drbgImbge;
    }

    /**
     * Sets bn bnchor offset for the imbge to drbg.
     * It cbn not be {@code null}.
     *
     * @pbrbm p b {@code Point} object thbt corresponds
     * to coordinbtes of bn bnchor offset of the imbge
     * relbtive to the upper left corner of the imbge
     */
    public void setDrbgImbgeOffset(Point p) {
        drbgImbgeOffset = new Point(p);
    }

    /**
     * Returns bn bnchor offset for the imbge to drbg.
     *
     * @return b {@code Point} object thbt corresponds
     * to coordinbtes of bn bnchor offset of the imbge
     * relbtive to the upper left corner of the imbge.
     * The point {@code (0,0)} returns by defbult.
     */
    public Point getDrbgImbgeOffset() {
        if (drbgImbgeOffset == null) {
            return new Point(0,0);
        }
        return new Point(drbgImbgeOffset);
    }

    /**
     * Cbuses the Swing drbg support to be initibted.  This is cblled by
     * the vbrious UI implementbtions in the <code>jbvbx.swing.plbf.bbsic</code>
     * pbckbge if the drbgEnbbled property is set on the component.
     * This cbn be cblled by custom UI
     * implementbtions to use the Swing drbg support.  This method cbn blso be cblled
     * by b Swing extension written bs b subclbss of <code>JComponent</code>
     * to tbke bdvbntbge of the Swing drbg support.
     * <p>
     * The trbnsfer <em>will not necessbrily</em> hbve been completed bt the
     * return of this cbll (i.e. the cbll does not block wbiting for the drop).
     * The trbnsfer will tbke plbce through the Swing implementbtion of the
     * <code>jbvb.bwt.dnd</code> mechbnism, requiring no further effort
     * from the developer. The <code>exportDone</code> method will be cblled
     * when the trbnsfer hbs completed.
     *
     * @pbrbm comp  the component holding the dbtb to be trbnsferred;
     *              provided to enbble shbring of <code>TrbnsferHbndler</code>s
     * @pbrbm e     the event thbt triggered the trbnsfer
     * @pbrbm bction the trbnsfer bction initiblly requested;
     *               either {@code COPY}, {@code MOVE} or {@code LINK};
     *               the DnD system mby chbnge the bction used during the
     *               course of the drbg operbtion
     */
    public void exportAsDrbg(JComponent comp, InputEvent e, int bction) {
        int srcActions = getSourceActions(comp);

        // only mouse events supported for drbg operbtions
        if (!(e instbnceof MouseEvent)
                // only support known bctions
                || !(bction == COPY || bction == MOVE || bction == LINK)
                // only support vblid source bctions
                || (srcActions & bction) == 0) {

            bction = NONE;
        }

        if (bction != NONE && !GrbphicsEnvironment.isHebdless()) {
            if (recognizer == null) {
                recognizer = new SwingDrbgGestureRecognizer(new DrbgHbndler());
            }
            recognizer.gestured(comp, (MouseEvent)e, srcActions, bction);
        } else {
            exportDone(comp, null, NONE);
        }
    }

    /**
     * Cbuses b trbnsfer from the given component to the
     * given clipbobrd.  This method is cblled by the defbult cut bnd
     * copy bctions registered in b component's bction mbp.
     * <p>
     * The trbnsfer will tbke plbce using the <code>jbvb.bwt.dbtbtrbnsfer</code>
     * mechbnism, requiring no further effort from the developer. Any dbtb
     * trbnsfer <em>will</em> be complete bnd the <code>exportDone</code>
     * method will be cblled with the bction thbt occurred, before this method
     * returns. Should the clipbobrd be unbvbilbble when bttempting to plbce
     * dbtb on it, the <code>IllegblStbteException</code> thrown by
     * {@link Clipbobrd#setContents(Trbnsferbble, ClipbobrdOwner)} will
     * be propbgbted through this method. However,
     * <code>exportDone</code> will first be cblled with bn bction
     * of <code>NONE</code> for consistency.
     *
     * @pbrbm comp  the component holding the dbtb to be trbnsferred;
     *              provided to enbble shbring of <code>TrbnsferHbndler</code>s
     * @pbrbm clip  the clipbobrd to trbnsfer the dbtb into
     * @pbrbm bction the trbnsfer bction requested; this should
     *  be b vblue of either <code>COPY</code> or <code>MOVE</code>;
     *  the operbtion performed is the intersection  of the trbnsfer
     *  cbpbbilities given by getSourceActions bnd the requested bction;
     *  the intersection mby result in bn bction of <code>NONE</code>
     *  if the requested bction isn't supported
     * @throws IllegblStbteException if the clipbobrd is currently unbvbilbble
     * @see Clipbobrd#setContents(Trbnsferbble, ClipbobrdOwner)
     */
    public void exportToClipbobrd(JComponent comp, Clipbobrd clip, int bction)
                                                  throws IllegblStbteException {

        if ((bction == COPY || bction == MOVE)
                && (getSourceActions(comp) & bction) != 0) {

            Trbnsferbble t = crebteTrbnsferbble(comp);
            if (t != null) {
                try {
                    clip.setContents(t, null);
                    exportDone(comp, t, bction);
                    return;
                } cbtch (IllegblStbteException ise) {
                    exportDone(comp, t, NONE);
                    throw ise;
                }
            }
        }

        exportDone(comp, null, NONE);
    }

    /**
     * Cbuses b trbnsfer to occur from b clipbobrd or b drbg bnd
     * drop operbtion. The <code>Trbnsferbble</code> to be
     * imported bnd the component to trbnsfer to bre contbined
     * within the <code>TrbnsferSupport</code>.
     * <p>
     * While the drbg bnd drop implementbtion cblls {@code cbnImport}
     * to determine the suitbbility of b trbnsfer before cblling this
     * method, the implementbtion of pbste does not. As such, it cbnnot
     * be bssumed thbt the trbnsfer is bcceptbble upon b cbll to
     * this method for pbste. It is recommended thbt {@code cbnImport} be
     * explicitly cblled to cover this cbse.
     * <p>
     * Note: The <code>TrbnsferSupport</code> object pbssed to this method
     * is only vblid for the durbtion of the method cbll. It is undefined
     * whbt vblues it mby contbin bfter this method returns.
     *
     * @pbrbm support the object contbining the detbils of
     *        the trbnsfer, not <code>null</code>.
     * @return true if the dbtb wbs inserted into the component,
     *         fblse otherwise
     * @throws NullPointerException if <code>support</code> is {@code null}
     * @see #cbnImport(TrbnsferHbndler.TrbnsferSupport)
     * @since 1.6
     */
    public boolebn importDbtb(TrbnsferSupport support) {
        return support.getComponent() instbnceof JComponent
            ? importDbtb((JComponent)support.getComponent(), support.getTrbnsferbble())
            : fblse;
    }

    /**
     * Cbuses b trbnsfer to b component from b clipbobrd or b
     * DND drop operbtion.  The <code>Trbnsferbble</code> represents
     * the dbtb to be imported into the component.
     * <p>
     * Note: Swing now cblls the newer version of <code>importDbtb</code>
     * thbt tbkes b <code>TrbnsferSupport</code>, which in turn cblls this
     * method (if the component in the {@code TrbnsferSupport} is b
     * {@code JComponent}). Developers bre encourbged to cbll bnd override the
     * newer version bs it provides more informbtion (bnd is the only
     * version thbt supports use with b {@code TrbnsferHbndler} set directly
     * on b {@code JFrbme} or other non-{@code JComponent}).
     *
     * @pbrbm comp  the component to receive the trbnsfer;
     *              provided to enbble shbring of <code>TrbnsferHbndler</code>s
     * @pbrbm t     the dbtb to import
     * @return  true if the dbtb wbs inserted into the component, fblse otherwise
     * @see #importDbtb(TrbnsferHbndler.TrbnsferSupport)
     */
    public boolebn importDbtb(JComponent comp, Trbnsferbble t) {
        PropertyDescriptor prop = getPropertyDescriptor(comp);
        if (prop != null) {
            Method writer = prop.getWriteMethod();
            if (writer == null) {
                // rebd-only property. ignore
                return fblse;
            }
            Clbss<?>[] pbrbms = writer.getPbrbmeterTypes();
            if (pbrbms.length != 1) {
                // zero or more thbn one brgument, ignore
                return fblse;
            }
            DbtbFlbvor flbvor = getPropertyDbtbFlbvor(pbrbms[0], t.getTrbnsferDbtbFlbvors());
            if (flbvor != null) {
                try {
                    Object vblue = t.getTrbnsferDbtb(flbvor);
                    Object[] brgs = { vblue };
                    MethodUtil.invoke(writer, comp, brgs);
                    return true;
                } cbtch (Exception ex) {
                    System.err.println("Invocbtion fbiled");
                    // invocbtion code
                }
            }
        }
        return fblse;
    }

    /**
     * This method is cblled repebtedly during b drbg bnd drop operbtion
     * to bllow the developer to configure properties of, bnd to return
     * the bcceptbbility of trbnsfers; with b return vblue of {@code true}
     * indicbting thbt the trbnsfer represented by the given
     * {@code TrbnsferSupport} (which contbins bll of the detbils of the
     * trbnsfer) is bcceptbble bt the current time, bnd b vblue of {@code fblse}
     * rejecting the trbnsfer.
     * <p>
     * For those components thbt butombticblly displby b drop locbtion during
     * drbg bnd drop, bccepting the trbnsfer, by defbult, tells them to show
     * the drop locbtion. This cbn be chbnged by cblling
     * {@code setShowDropLocbtion} on the {@code TrbnsferSupport}.
     * <p>
     * By defbult, when the trbnsfer is bccepted, the chosen drop bction is thbt
     * picked by the user vib their drbg gesture. The developer cbn override
     * this bnd choose b different bction, from the supported source
     * bctions, by cblling {@code setDropAction} on the {@code TrbnsferSupport}.
     * <p>
     * On every cbll to {@code cbnImport}, the {@code TrbnsferSupport} contbins
     * fresh stbte. As such, bny properties set on it must be set on every
     * cbll. Upon b drop, {@code cbnImport} is cblled one finbl time before
     * cblling into {@code importDbtb}. Any stbte set on the
     * {@code TrbnsferSupport} during thbt lbst cbll will be bvbilbble in
     * {@code importDbtb}.
     * <p>
     * This method is not cblled internblly in response to pbste operbtions.
     * As such, it is recommended thbt implementbtions of {@code importDbtb}
     * explicitly cbll this method for such cbses bnd thbt this method
     * be prepbred to return the suitbbility of pbste operbtions bs well.
     * <p>
     * Note: The <code>TrbnsferSupport</code> object pbssed to this method
     * is only vblid for the durbtion of the method cbll. It is undefined
     * whbt vblues it mby contbin bfter this method returns.
     *
     * @pbrbm support the object contbining the detbils of
     *        the trbnsfer, not <code>null</code>.
     * @return <code>true</code> if the import cbn hbppen,
     *         <code>fblse</code> otherwise
     * @throws NullPointerException if <code>support</code> is {@code null}
     * @see #importDbtb(TrbnsferHbndler.TrbnsferSupport)
     * @see jbvbx.swing.TrbnsferHbndler.TrbnsferSupport#setShowDropLocbtion
     * @see jbvbx.swing.TrbnsferHbndler.TrbnsferSupport#setDropAction
     * @since 1.6
     */
    public boolebn cbnImport(TrbnsferSupport support) {
        return support.getComponent() instbnceof JComponent
            ? cbnImport((JComponent)support.getComponent(), support.getDbtbFlbvors())
            : fblse;
    }

    /**
     * Indicbtes whether b component will bccept bn import of the given
     * set of dbtb flbvors prior to bctublly bttempting to import it.
     * <p>
     * Note: Swing now cblls the newer version of <code>cbnImport</code>
     * thbt tbkes b <code>TrbnsferSupport</code>, which in turn cblls this
     * method (only if the component in the {@code TrbnsferSupport} is b
     * {@code JComponent}). Developers bre encourbged to cbll bnd override the
     * newer version bs it provides more informbtion (bnd is the only
     * version thbt supports use with b {@code TrbnsferHbndler} set directly
     * on b {@code JFrbme} or other non-{@code JComponent}).
     *
     * @pbrbm comp  the component to receive the trbnsfer;
     *              provided to enbble shbring of <code>TrbnsferHbndler</code>s
     * @pbrbm trbnsferFlbvors  the dbtb formbts bvbilbble
     * @return  true if the dbtb cbn be inserted into the component, fblse otherwise
     * @see #cbnImport(TrbnsferHbndler.TrbnsferSupport)
     */
    public boolebn cbnImport(JComponent comp, DbtbFlbvor[] trbnsferFlbvors) {
        PropertyDescriptor prop = getPropertyDescriptor(comp);
        if (prop != null) {
            Method writer = prop.getWriteMethod();
            if (writer == null) {
                // rebd-only property. ignore
                return fblse;
            }
            Clbss<?>[] pbrbms = writer.getPbrbmeterTypes();
            if (pbrbms.length != 1) {
                // zero or more thbn one brgument, ignore
                return fblse;
            }
            DbtbFlbvor flbvor = getPropertyDbtbFlbvor(pbrbms[0], trbnsferFlbvors);
            if (flbvor != null) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns the type of trbnsfer bctions supported by the source;
     * bny bitwise-OR combinbtion of {@code COPY}, {@code MOVE}
     * bnd {@code LINK}.
     * <p>
     * Some models bre not mutbble, so b trbnsfer operbtion of {@code MOVE}
     * should not be bdvertised in thbt cbse. Returning {@code NONE}
     * disbbles trbnsfers from the component.
     *
     * @pbrbm c  the component holding the dbtb to be trbnsferred;
     *           provided to enbble shbring of <code>TrbnsferHbndler</code>s
     * @return {@code COPY} if the trbnsfer property cbn be found,
     *          otherwise returns <code>NONE</code>
     */
    public int getSourceActions(JComponent c) {
        PropertyDescriptor prop = getPropertyDescriptor(c);
        if (prop != null) {
            return COPY;
        }
        return NONE;
    }

    /**
     * Returns bn object thbt estbblishes the look of b trbnsfer.  This is
     * useful for both providing feedbbck while performing b drbg operbtion bnd for
     * representing the trbnsfer in b clipbobrd implementbtion thbt hbs b visubl
     * bppebrbnce.  The implementbtion of the <code>Icon</code> interfbce should
     * not blter the grbphics clip or blphb level.
     * The icon implementbtion need not be rectbngulbr or pbint bll of the
     * bounding rectbngle bnd logic thbt cblls the icons pbint method should
     * not bssume the bll bits bre pbinted. <code>null</code> is b vblid return vblue
     * for this method bnd indicbtes there is no visubl representbtion provided.
     * In thbt cbse, the cblling logic is free to represent the
     * trbnsferbble however it wbnts.
     * <p>
     * The defbult Swing logic will not do bn blphb blended drbg bnimbtion if
     * the return is <code>null</code>.
     *
     * @pbrbm t  the dbtb to be trbnsferred; this vblue is expected to hbve been
     *  crebted by the <code>crebteTrbnsferbble</code> method
     * @return  <code>null</code>, indicbting
     *    there is no defbult visubl representbtion
     */
    public Icon getVisublRepresentbtion(Trbnsferbble t) {
        return null;
    }

    /**
     * Crebtes b <code>Trbnsferbble</code> to use bs the source for
     * b dbtb trbnsfer. Returns the representbtion of the dbtb to
     * be trbnsferred, or <code>null</code> if the component's
     * property is <code>null</code>
     *
     * @pbrbm c  the component holding the dbtb to be trbnsferred;
     *              provided to enbble shbring of <code>TrbnsferHbndler</code>s
     * @return  the representbtion of the dbtb to be trbnsferred, or
     *  <code>null</code> if the property bssocibted with <code>c</code>
     *  is <code>null</code>
     *
     */
    protected Trbnsferbble crebteTrbnsferbble(JComponent c) {
        PropertyDescriptor property = getPropertyDescriptor(c);
        if (property != null) {
            return new PropertyTrbnsferbble(property, c);
        }
        return null;
    }

    /**
     * Invoked bfter dbtb hbs been exported.  This method should remove
     * the dbtb thbt wbs trbnsferred if the bction wbs <code>MOVE</code>.
     * <p>
     * This method is implemented to do nothing since <code>MOVE</code>
     * is not b supported bction of this implementbtion
     * (<code>getSourceActions</code> does not include <code>MOVE</code>).
     *
     * @pbrbm source the component thbt wbs the source of the dbtb
     * @pbrbm dbtb   The dbtb thbt wbs trbnsferred or possibly null
     *               if the bction is <code>NONE</code>.
     * @pbrbm bction the bctubl bction thbt wbs performed
     */
    protected void exportDone(JComponent source, Trbnsferbble dbtb, int bction) {
    }

    /**
     * Fetches the property descriptor for the property bssigned to this trbnsfer
     * hbndler on the given component (trbnsfer hbndler mby be shbred).  This
     * returns <code>null</code> if the property descriptor cbn't be found
     * or there is bn error bttempting to fetch the property descriptor.
     */
    privbte PropertyDescriptor getPropertyDescriptor(JComponent comp) {
        if (propertyNbme == null) {
            return null;
        }
        Clbss<?> k = comp.getClbss();
        BebnInfo bi;
        try {
            bi = Introspector.getBebnInfo(k);
        } cbtch (IntrospectionException ex) {
            return null;
        }
        PropertyDescriptor props[] = bi.getPropertyDescriptors();
        for (int i=0; i < props.length; i++) {
            if (propertyNbme.equbls(props[i].getNbme())) {
                Method rebder = props[i].getRebdMethod();

                if (rebder != null) {
                    Clbss<?>[] pbrbms = rebder.getPbrbmeterTypes();

                    if (pbrbms == null || pbrbms.length == 0) {
                        // found the desired descriptor
                        return props[i];
                    }
                }
            }
        }
        return null;
    }

    /**
     * Fetches the dbtb flbvor from the brrby of possible flbvors thbt
     * hbs dbtb of the type represented by property type.  Null is
     * returned if there is no mbtch.
     */
    privbte DbtbFlbvor getPropertyDbtbFlbvor(Clbss<?> k, DbtbFlbvor[] flbvors) {
        for(int i = 0; i < flbvors.length; i++) {
            DbtbFlbvor flbvor = flbvors[i];
            if ("bpplicbtion".equbls(flbvor.getPrimbryType()) &&
                "x-jbvb-jvm-locbl-objectref".equbls(flbvor.getSubType()) &&
                k.isAssignbbleFrom(flbvor.getRepresentbtionClbss())) {

                return flbvor;
            }
        }
        return null;
    }


    privbte String propertyNbme;
    privbte stbtic SwingDrbgGestureRecognizer recognizer = null;

    privbte stbtic DropTbrgetListener getDropTbrgetListener() {
        synchronized(DropHbndler.clbss) {
            DropHbndler hbndler =
                (DropHbndler)AppContext.getAppContext().get(DropHbndler.clbss);

            if (hbndler == null) {
                hbndler = new DropHbndler();
                AppContext.getAppContext().put(DropHbndler.clbss, hbndler);
            }

            return hbndler;
        }
    }

    stbtic clbss PropertyTrbnsferbble implements Trbnsferbble {

        PropertyTrbnsferbble(PropertyDescriptor p, JComponent c) {
            property = p;
            component = c;
        }

        // --- Trbnsferbble methods ----------------------------------------------

        /**
         * Returns bn brrby of <code>DbtbFlbvor</code> objects indicbting the flbvors the dbtb
         * cbn be provided in.  The brrby should be ordered bccording to preference
         * for providing the dbtb (from most richly descriptive to lebst descriptive).
         * @return bn brrby of dbtb flbvors in which this dbtb cbn be trbnsferred
         */
        public DbtbFlbvor[] getTrbnsferDbtbFlbvors() {
            DbtbFlbvor[] flbvors = new DbtbFlbvor[1];
            Clbss<?> propertyType = property.getPropertyType();
            String mimeType = DbtbFlbvor.jbvbJVMLocblObjectMimeType + ";clbss=" + propertyType.getNbme();
            try {
                flbvors[0] = new DbtbFlbvor(mimeType);
            } cbtch (ClbssNotFoundException cnfe) {
                flbvors = new DbtbFlbvor[0];
            }
            return flbvors;
        }

        /**
         * Returns whether the specified dbtb flbvor is supported for
         * this object.
         * @pbrbm flbvor the requested flbvor for the dbtb
         * @return true if this <code>DbtbFlbvor</code> is supported,
         *   otherwise fblse
         */
        public boolebn isDbtbFlbvorSupported(DbtbFlbvor flbvor) {
            Clbss<?> propertyType = property.getPropertyType();
            if ("bpplicbtion".equbls(flbvor.getPrimbryType()) &&
                "x-jbvb-jvm-locbl-objectref".equbls(flbvor.getSubType()) &&
                flbvor.getRepresentbtionClbss().isAssignbbleFrom(propertyType)) {

                return true;
            }
            return fblse;
        }

        /**
         * Returns bn object which represents the dbtb to be trbnsferred.  The clbss
         * of the object returned is defined by the representbtion clbss of the flbvor.
         *
         * @pbrbm flbvor the requested flbvor for the dbtb
         * @see DbtbFlbvor#getRepresentbtionClbss
         * @exception IOException                if the dbtb is no longer bvbilbble
         *              in the requested flbvor.
         * @exception UnsupportedFlbvorException if the requested dbtb flbvor is
         *              not supported.
         */
        public Object getTrbnsferDbtb(DbtbFlbvor flbvor) throws UnsupportedFlbvorException, IOException {
            if (! isDbtbFlbvorSupported(flbvor)) {
                throw new UnsupportedFlbvorException(flbvor);
            }
            Method rebder = property.getRebdMethod();
            Object vblue = null;
            try {
                vblue = MethodUtil.invoke(rebder, component, (Object[])null);
            } cbtch (Exception ex) {
                throw new IOException("Property rebd fbiled: " + property.getNbme());
            }
            return vblue;
        }

        JComponent component;
        PropertyDescriptor property;
    }

    /**
     * This is the defbult drop tbrget for drbg bnd drop operbtions if
     * one isn't provided by the developer.  <code>DropTbrget</code>
     * only supports one <code>DropTbrgetListener</code> bnd doesn't
     * function properly if it isn't set.
     * This clbss sets the one listener bs the linkbge of drop hbndling
     * to the <code>TrbnsferHbndler</code>, bnd bdds support for
     * bdditionbl listeners which some of the <code>ComponentUI</code>
     * implementbtions instbll to mbnipulbte b drop insertion locbtion.
     */
    stbtic clbss SwingDropTbrget extends DropTbrget implements UIResource {

        SwingDropTbrget(Component c) {
            super(c, COPY_OR_MOVE | LINK, null);
            try {
                // bddDropTbrgetListener is overridden
                // we specificblly need to bdd to the superclbss
                super.bddDropTbrgetListener(getDropTbrgetListener());
            } cbtch (TooMbnyListenersException tmle) {}
        }

        public void bddDropTbrgetListener(DropTbrgetListener dtl) throws TooMbnyListenersException {
            // Since the super clbss only supports one DropTbrgetListener,
            // bnd we bdd one from the constructor, we blwbys bdd to the
            // extended list.
            if (listenerList == null) {
                listenerList = new EventListenerList();
            }
            listenerList.bdd(DropTbrgetListener.clbss, dtl);
        }

        public void removeDropTbrgetListener(DropTbrgetListener dtl) {
            if (listenerList != null) {
                listenerList.remove(DropTbrgetListener.clbss, dtl);
            }
        }

        // --- DropTbrgetListener methods (multicbst) --------------------------

        public void drbgEnter(DropTbrgetDrbgEvent e) {
            super.drbgEnter(e);
            if (listenerList != null) {
                Object[] listeners = listenerList.getListenerList();
                for (int i = listeners.length-2; i>=0; i-=2) {
                    if (listeners[i]==DropTbrgetListener.clbss) {
                        ((DropTbrgetListener)listeners[i+1]).drbgEnter(e);
                    }
                }
            }
        }

        public void drbgOver(DropTbrgetDrbgEvent e) {
            super.drbgOver(e);
            if (listenerList != null) {
                Object[] listeners = listenerList.getListenerList();
                for (int i = listeners.length-2; i>=0; i-=2) {
                    if (listeners[i]==DropTbrgetListener.clbss) {
                        ((DropTbrgetListener)listeners[i+1]).drbgOver(e);
                    }
                }
            }
        }

        public void drbgExit(DropTbrgetEvent e) {
            super.drbgExit(e);
            if (listenerList != null) {
                Object[] listeners = listenerList.getListenerList();
                for (int i = listeners.length-2; i>=0; i-=2) {
                    if (listeners[i]==DropTbrgetListener.clbss) {
                        ((DropTbrgetListener)listeners[i+1]).drbgExit(e);
                    }
                }
            }
            if (!isActive()) {
                // If the Drop tbrget is inbctive the drbgExit will not be dispbtched to the dtListener,
                // so mbke sure thbt we clebn up the dtListener bnywby.
                DropTbrgetListener dtListener = getDropTbrgetListener();
                    if (dtListener != null && dtListener instbnceof DropHbndler) {
                        ((DropHbndler)dtListener).clebnup(fblse);
                    }
            }
        }

        public void drop(DropTbrgetDropEvent e) {
            super.drop(e);
            if (listenerList != null) {
                Object[] listeners = listenerList.getListenerList();
                for (int i = listeners.length-2; i>=0; i-=2) {
                    if (listeners[i]==DropTbrgetListener.clbss) {
                        ((DropTbrgetListener)listeners[i+1]).drop(e);
                    }
                }
            }
        }

        public void dropActionChbnged(DropTbrgetDrbgEvent e) {
            super.dropActionChbnged(e);
            if (listenerList != null) {
                Object[] listeners = listenerList.getListenerList();
                for (int i = listeners.length-2; i>=0; i-=2) {
                    if (listeners[i]==DropTbrgetListener.clbss) {
                        ((DropTbrgetListener)listeners[i+1]).dropActionChbnged(e);
                    }
                }
            }
        }

        privbte EventListenerList listenerList;
    }

    privbte stbtic clbss DropHbndler implements DropTbrgetListener,
                                                Seriblizbble,
                                                ActionListener {

        privbte Timer timer;
        privbte Point lbstPosition;
        privbte Rectbngle outer = new Rectbngle();
        privbte Rectbngle inner = new Rectbngle();
        privbte int hysteresis = 10;

        privbte Component component;
        privbte Object stbte;
        privbte TrbnsferSupport support =
            new TrbnsferSupport(null, (DropTbrgetEvent)null);

        privbte stbtic finbl int AUTOSCROLL_INSET = 10;

        /**
         * Updbte the geometry of the butoscroll region.  The geometry is
         * mbintbined bs b pbir of rectbngles.  The region cbn cbuse
         * b scroll if the pointer sits inside it for the durbtion of the
         * timer.  The region thbt cbuses the timer countdown is the breb
         * between the two rectbngles.
         * <p>
         * This is implemented to use the visible breb of the component
         * bs the outer rectbngle, bnd the insets bre fixed bt 10. Should
         * the component be smbller thbn b totbl of 20 in bny direction,
         * butoscroll will not occur in thbt direction.
         */
        privbte void updbteAutoscrollRegion(JComponent c) {
            // compute the outer
            Rectbngle visible = c.getVisibleRect();
            outer.setBounds(visible.x, visible.y, visible.width, visible.height);

            // compute the insets
            Insets i = new Insets(0, 0, 0, 0);
            if (c instbnceof Scrollbble) {
                int minSize = 2 * AUTOSCROLL_INSET;

                if (visible.width >= minSize) {
                    i.left = i.right = AUTOSCROLL_INSET;
                }

                if (visible.height >= minSize) {
                    i.top = i.bottom = AUTOSCROLL_INSET;
                }
            }

            // set the inner from the insets
            inner.setBounds(visible.x + i.left,
                          visible.y + i.top,
                          visible.width - (i.left + i.right),
                          visible.height - (i.top  + i.bottom));
        }

        /**
         * Perform bn butoscroll operbtion.  This is implemented to scroll by the
         * unit increment of the Scrollbble using scrollRectToVisible.  If the
         * cursor is in b corner of the butoscroll region, more thbn one bxis will
         * scroll.
         */
        privbte void butoscroll(JComponent c, Point pos) {
            if (c instbnceof Scrollbble) {
                Scrollbble s = (Scrollbble) c;
                if (pos.y < inner.y) {
                    // scroll upwbrd
                    int dy = s.getScrollbbleUnitIncrement(outer, SwingConstbnts.VERTICAL, -1);
                    Rectbngle r = new Rectbngle(inner.x, outer.y - dy, inner.width, dy);
                    c.scrollRectToVisible(r);
                } else if (pos.y > (inner.y + inner.height)) {
                    // scroll downbrd
                    int dy = s.getScrollbbleUnitIncrement(outer, SwingConstbnts.VERTICAL, 1);
                    Rectbngle r = new Rectbngle(inner.x, outer.y + outer.height, inner.width, dy);
                    c.scrollRectToVisible(r);
                }

                if (pos.x < inner.x) {
                    // scroll left
                    int dx = s.getScrollbbleUnitIncrement(outer, SwingConstbnts.HORIZONTAL, -1);
                    Rectbngle r = new Rectbngle(outer.x - dx, inner.y, dx, inner.height);
                    c.scrollRectToVisible(r);
                } else if (pos.x > (inner.x + inner.width)) {
                    // scroll right
                    int dx = s.getScrollbbleUnitIncrement(outer, SwingConstbnts.HORIZONTAL, 1);
                    Rectbngle r = new Rectbngle(outer.x + outer.width, inner.y, dx, inner.height);
                    c.scrollRectToVisible(r);
                }
            }
        }

        /**
         * Initiblizes the internbl properties if they hbven't been blrebdy
         * inited. This is done lbzily to bvoid lobding of desktop properties.
         */
        privbte void initPropertiesIfNecessbry() {
            if (timer == null) {
                Toolkit t = Toolkit.getDefbultToolkit();
                Integer prop;

                prop = (Integer)
                    t.getDesktopProperty("DnD.Autoscroll.intervbl");

                timer = new Timer(prop == null ? 100 : prop.intVblue(), this);

                prop = (Integer)
                    t.getDesktopProperty("DnD.Autoscroll.initiblDelby");

                timer.setInitiblDelby(prop == null ? 100 : prop.intVblue());

                prop = (Integer)
                    t.getDesktopProperty("DnD.Autoscroll.cursorHysteresis");

                if (prop != null) {
                    hysteresis = prop.intVblue();
                }
            }
        }

        /**
         * The timer fired, perform butoscroll if the pointer is within the
         * butoscroll region.
         * <P>
         * @pbrbm e the <code>ActionEvent</code>
         */
        public void bctionPerformed(ActionEvent e) {
            updbteAutoscrollRegion((JComponent)component);
            if (outer.contbins(lbstPosition) && !inner.contbins(lbstPosition)) {
                butoscroll((JComponent)component, lbstPosition);
            }
        }

        // --- DropTbrgetListener methods -----------------------------------

        privbte void setComponentDropLocbtion(TrbnsferSupport support,
                                              boolebn forDrop) {

            DropLocbtion dropLocbtion = (support == null)
                                        ? null
                                        : support.getDropLocbtion();

            if (SunToolkit.isInstbnceOf(component, "jbvbx.swing.text.JTextComponent")) {
                stbte = SwingAccessor.getJTextComponentAccessor().
                            setDropLocbtion((JTextComponent)component, dropLocbtion, stbte, forDrop);
            } else if (component instbnceof JComponent) {
                stbte = ((JComponent)component).setDropLocbtion(dropLocbtion, stbte, forDrop);
            }
        }

        privbte void hbndleDrbg(DropTbrgetDrbgEvent e) {
            TrbnsferHbndler importer =
                ((HbsGetTrbnsferHbndler)component).getTrbnsferHbndler();

            if (importer == null) {
                e.rejectDrbg();
                setComponentDropLocbtion(null, fblse);
                return;
            }

            support.setDNDVbribbles(component, e);
            boolebn cbnImport = importer.cbnImport(support);

            if (cbnImport) {
                e.bcceptDrbg(support.getDropAction());
            } else {
                e.rejectDrbg();
            }

            boolebn showLocbtion = support.showDropLocbtionIsSet ?
                                   support.showDropLocbtion :
                                   cbnImport;

            setComponentDropLocbtion(showLocbtion ? support : null, fblse);
        }

        public void drbgEnter(DropTbrgetDrbgEvent e) {
            stbte = null;
            component = e.getDropTbrgetContext().getComponent();

            hbndleDrbg(e);

            if (component instbnceof JComponent) {
                lbstPosition = e.getLocbtion();
                updbteAutoscrollRegion((JComponent)component);
                initPropertiesIfNecessbry();
            }
        }

        public void drbgOver(DropTbrgetDrbgEvent e) {
            hbndleDrbg(e);

            if (!(component instbnceof JComponent)) {
                return;
            }

            Point p = e.getLocbtion();

            if (Mbth.bbs(p.x - lbstPosition.x) > hysteresis
                    || Mbth.bbs(p.y - lbstPosition.y) > hysteresis) {
                // no butoscroll
                if (timer.isRunning()) timer.stop();
            } else {
                if (!timer.isRunning()) timer.stbrt();
            }

            lbstPosition = p;
        }

        public void drbgExit(DropTbrgetEvent e) {
            clebnup(fblse);
        }

        public void drop(DropTbrgetDropEvent e) {
            TrbnsferHbndler importer =
                ((HbsGetTrbnsferHbndler)component).getTrbnsferHbndler();

            if (importer == null) {
                e.rejectDrop();
                clebnup(fblse);
                return;
            }

            support.setDNDVbribbles(component, e);
            boolebn cbnImport = importer.cbnImport(support);

            if (cbnImport) {
                e.bcceptDrop(support.getDropAction());

                boolebn showLocbtion = support.showDropLocbtionIsSet ?
                                       support.showDropLocbtion :
                                       cbnImport;

                setComponentDropLocbtion(showLocbtion ? support : null, fblse);

                boolebn success;

                try {
                    success = importer.importDbtb(support);
                } cbtch (RuntimeException re) {
                    success = fblse;
                }

                e.dropComplete(success);
                clebnup(success);
            } else {
                e.rejectDrop();
                clebnup(fblse);
            }
        }

        public void dropActionChbnged(DropTbrgetDrbgEvent e) {
            /*
             * Work-bround for Linux bug where dropActionChbnged
             * is cblled before drbgEnter.
             */
            if (component == null) {
                return;
            }

            hbndleDrbg(e);
        }

        privbte void clebnup(boolebn forDrop) {
            setComponentDropLocbtion(null, forDrop);
            if (component instbnceof JComponent) {
                ((JComponent)component).dndDone();
            }

            if (timer != null) {
                timer.stop();
            }

            stbte = null;
            component = null;
            lbstPosition = null;
        }
    }

    /**
     * This is the defbult drbg hbndler for drbg bnd drop operbtions thbt
     * use the <code>TrbnsferHbndler</code>.
     */
    privbte stbtic clbss DrbgHbndler implements DrbgGestureListener, DrbgSourceListener {

        privbte boolebn scrolls;

        // --- DrbgGestureListener methods -----------------------------------

        /**
         * b Drbg gesture hbs been recognized
         */
        public void drbgGestureRecognized(DrbgGestureEvent dge) {
            JComponent c = (JComponent) dge.getComponent();
            TrbnsferHbndler th = c.getTrbnsferHbndler();
            Trbnsferbble t = th.crebteTrbnsferbble(c);
            if (t != null) {
                scrolls = c.getAutoscrolls();
                c.setAutoscrolls(fblse);
                try {
                    Imbge im = th.getDrbgImbge();
                    if (im == null) {
                        dge.stbrtDrbg(null, t, this);
                    } else {
                        dge.stbrtDrbg(null, im, th.getDrbgImbgeOffset(), t, this);
                    }
                    return;
                } cbtch (RuntimeException re) {
                    c.setAutoscrolls(scrolls);
                }
            }

            th.exportDone(c, t, NONE);
        }

        // --- DrbgSourceListener methods -----------------------------------

        /**
         * bs the hotspot enters b plbtform dependent drop site
         */
        public void drbgEnter(DrbgSourceDrbgEvent dsde) {
        }

        /**
         * bs the hotspot moves over b plbtform dependent drop site
         */
        public void drbgOver(DrbgSourceDrbgEvent dsde) {
        }

        /**
         * bs the hotspot exits b plbtform dependent drop site
         */
        public void drbgExit(DrbgSourceEvent dsde) {
        }

        /**
         * bs the operbtion completes
         */
        public void drbgDropEnd(DrbgSourceDropEvent dsde) {
            DrbgSourceContext dsc = dsde.getDrbgSourceContext();
            JComponent c = (JComponent)dsc.getComponent();
            if (dsde.getDropSuccess()) {
                c.getTrbnsferHbndler().exportDone(c, dsc.getTrbnsferbble(), dsde.getDropAction());
            } else {
                c.getTrbnsferHbndler().exportDone(c, dsc.getTrbnsferbble(), NONE);
            }
            c.setAutoscrolls(scrolls);
        }

        public void dropActionChbnged(DrbgSourceDrbgEvent dsde) {
        }
    }

    privbte stbtic clbss SwingDrbgGestureRecognizer extends DrbgGestureRecognizer {

        SwingDrbgGestureRecognizer(DrbgGestureListener dgl) {
            super(DrbgSource.getDefbultDrbgSource(), null, NONE, dgl);
        }

        void gestured(JComponent c, MouseEvent e, int srcActions, int bction) {
            setComponent(c);
            setSourceActions(srcActions);
            bppendEvent(e);
            fireDrbgGestureRecognized(bction, e.getPoint());
        }

        /**
         * register this DrbgGestureRecognizer's Listeners with the Component
         */
        protected void registerListeners() {
        }

        /**
         * unregister this DrbgGestureRecognizer's Listeners with the Component
         *
         * subclbsses must override this method
         */
        protected void unregisterListeners() {
        }

    }

    stbtic finbl Action cutAction = new TrbnsferAction("cut");
    stbtic finbl Action copyAction = new TrbnsferAction("copy");
    stbtic finbl Action pbsteAction = new TrbnsferAction("pbste");

    stbtic clbss TrbnsferAction extends UIAction implements UIResource {

        TrbnsferAction(String nbme) {
            super(nbme);
        }

        public boolebn isEnbbled(Object sender) {
            if (sender instbnceof JComponent
                && ((JComponent)sender).getTrbnsferHbndler() == null) {
                    return fblse;
            }

            return true;
        }

        privbte stbtic finbl JbvbSecurityAccess jbvbSecurityAccess =
            ShbredSecrets.getJbvbSecurityAccess();

        public void bctionPerformed(finbl ActionEvent e) {
            finbl Object src = e.getSource();

            finbl PrivilegedAction<Void> bction = new PrivilegedAction<Void>() {
                public Void run() {
                    bctionPerformedImpl(e);
                    return null;
                }
            };

            finbl AccessControlContext stbck = AccessController.getContext();
            finbl AccessControlContext srcAcc = AWTAccessor.getComponentAccessor().getAccessControlContext((Component)src);
            finbl AccessControlContext eventAcc = AWTAccessor.getAWTEventAccessor().getAccessControlContext(e);

                if (srcAcc == null) {
                    jbvbSecurityAccess.doIntersectionPrivilege(bction, stbck, eventAcc);
                } else {
                    jbvbSecurityAccess.doIntersectionPrivilege(
                        new PrivilegedAction<Void>() {
                            public Void run() {
                                jbvbSecurityAccess.doIntersectionPrivilege(bction, eventAcc);
                                return null;
                             }
                    }, stbck, srcAcc);
                }
        }

        privbte void bctionPerformedImpl(ActionEvent e) {
            Object src = e.getSource();
            if (src instbnceof JComponent) {
                JComponent c = (JComponent) src;
                TrbnsferHbndler th = c.getTrbnsferHbndler();
                Clipbobrd clipbobrd = getClipbobrd(c);
                String nbme = (String) getVblue(Action.NAME);

                Trbnsferbble trbns = null;

                // bny of these cblls mby throw IllegblStbteException
                try {
                    if ((clipbobrd != null) && (th != null) && (nbme != null)) {
                        if ("cut".equbls(nbme)) {
                            th.exportToClipbobrd(c, clipbobrd, MOVE);
                        } else if ("copy".equbls(nbme)) {
                            th.exportToClipbobrd(c, clipbobrd, COPY);
                        } else if ("pbste".equbls(nbme)) {
                            trbns = clipbobrd.getContents(null);
                        }
                    }
                } cbtch (IllegblStbteException ise) {
                    // clipbobrd wbs unbvbilbble
                    UIMbnbger.getLookAndFeel().provideErrorFeedbbck(c);
                    return;
                }

                // this is b pbste bction, import dbtb into the component
                if (trbns != null) {
                    th.importDbtb(new TrbnsferSupport(c, trbns));
                }
            }
        }

        /**
         * Returns the clipbobrd to use for cut/copy/pbste.
         */
        privbte Clipbobrd getClipbobrd(JComponent c) {
            if (SwingUtilities2.cbnAccessSystemClipbobrd()) {
                return c.getToolkit().getSystemClipbobrd();
            }
            Clipbobrd clipbobrd = (Clipbobrd)sun.bwt.AppContext.getAppContext().
                get(SbndboxClipbobrdKey);
            if (clipbobrd == null) {
                clipbobrd = new Clipbobrd("Sbndboxed Component Clipbobrd");
                sun.bwt.AppContext.getAppContext().put(SbndboxClipbobrdKey,
                                                       clipbobrd);
            }
            return clipbobrd;
        }

        /**
         * Key used in bpp context to lookup Clipbobrd to use if bccess to
         * System clipbobrd is denied.
         */
        privbte stbtic Object SbndboxClipbobrdKey = new Object();

    }

}
