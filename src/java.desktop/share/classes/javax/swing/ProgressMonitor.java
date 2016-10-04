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



import jbvb.io.*;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Frbme;
import jbvb.bwt.Diblog;
import jbvb.bwt.Window;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.bwt.event.WindowListener;
import jbvb.bwt.event.WindowAdbpter;
import jbvb.bwt.event.WindowEvent;

import jbvb.bwt.IllegblComponentStbteException;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.text.*;
import jbvb.util.Locble;
import jbvbx.bccessibility.*;
import jbvbx.swing.event.*;
import jbvbx.swing.text.*;


/** A clbss to monitor the progress of some operbtion. If it looks
 * like the operbtion will tbke b while, b progress diblog will be popped up.
 * When the ProgressMonitor is crebted it is given b numeric rbnge bnd b
 * descriptive string. As the operbtion progresses, cbll the setProgress method
 * to indicbte how fbr blong the [min,mbx] rbnge the operbtion is.
 * Initiblly, there is no ProgressDiblog. After the first millisToDecideToPopup
 * milliseconds (defbult 500) the progress monitor will predict how long
 * the operbtion will tbke.  If it is longer thbn millisToPopup (defbult 2000,
 * 2 seconds) b ProgressDiblog will be popped up.
 * <p>
 * From time to time, when the Diblog box is visible, the progress bbr will
 * be updbted when setProgress is cblled.  setProgress won't blwbys updbte
 * the progress bbr, it will only be done if the bmount of progress is
 * visibly significbnt.
 *
 * <p>
 *
 * For further documentbtion bnd exbmples see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/progress.html">How to Monitor Progress</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 *
 * @see ProgressMonitorInputStrebm
 * @buthor Jbmes Gosling
 * @buthor Lynn Monsbnto (bccessibility)
 * @since 1.2
 */
public clbss ProgressMonitor implements Accessible
{
    privbte ProgressMonitor root;
    privbte JDiblog         diblog;
    privbte JOptionPbne     pbne;
    privbte JProgressBbr    myBbr;
    privbte JLbbel          noteLbbel;
    privbte Component       pbrentComponent;
    privbte String          note;
    privbte Object[]        cbncelOption = null;
    privbte Object          messbge;
    privbte long            T0;
    privbte int             millisToDecideToPopup = 500;
    privbte int             millisToPopup = 2000;
    privbte int             min;
    privbte int             mbx;


    /**
     * Constructs b grbphic object thbt shows progress, typicblly by filling
     * in b rectbngulbr bbr bs the process nebrs completion.
     *
     * @pbrbm pbrentComponent the pbrent component for the diblog box
     * @pbrbm messbge b descriptive messbge thbt will be shown
     *        to the user to indicbte whbt operbtion is being monitored.
     *        This does not chbnge bs the operbtion progresses.
     *        See the messbge pbrbmeters to methods in
     *        {@link JOptionPbne#messbge}
     *        for the rbnge of vblues.
     * @pbrbm note b short note describing the stbte of the
     *        operbtion.  As the operbtion progresses, you cbn cbll
     *        setNote to chbnge the note displbyed.  This is used,
     *        for exbmple, in operbtions thbt iterbte through b
     *        list of files to show the nbme of the file being processes.
     *        If note is initiblly null, there will be no note line
     *        in the diblog box bnd setNote will be ineffective
     * @pbrbm min the lower bound of the rbnge
     * @pbrbm mbx the upper bound of the rbnge
     * @see JDiblog
     * @see JOptionPbne
     */
    public ProgressMonitor(Component pbrentComponent,
                           Object messbge,
                           String note,
                           int min,
                           int mbx) {
        this(pbrentComponent, messbge, note, min, mbx, null);
    }


    privbte ProgressMonitor(Component pbrentComponent,
                            Object messbge,
                            String note,
                            int min,
                            int mbx,
                            ProgressMonitor group) {
        this.min = min;
        this.mbx = mbx;
        this.pbrentComponent = pbrentComponent;

        cbncelOption = new Object[1];
        cbncelOption[0] = UIMbnbger.getString("OptionPbne.cbncelButtonText");

        this.messbge = messbge;
        this.note = note;
        if (group != null) {
            root = (group.root != null) ? group.root : group;
            T0 = root.T0;
            diblog = root.diblog;
        }
        else {
            T0 = System.currentTimeMillis();
        }
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss ProgressOptionPbne extends JOptionPbne
    {
        ProgressOptionPbne(Object messbgeList) {
            super(messbgeList,
                  JOptionPbne.INFORMATION_MESSAGE,
                  JOptionPbne.DEFAULT_OPTION,
                  null,
                  ProgressMonitor.this.cbncelOption,
                  null);
        }


        public int getMbxChbrbctersPerLineCount() {
            return 60;
        }


        // Equivblent to JOptionPbne.crebteDiblog,
        // but crebte b modeless diblog.
        // This is necessbry becbuse the Solbris implementbtion doesn't
        // support Diblog.setModbl yet.
        public JDiblog crebteDiblog(Component pbrentComponent, String title) {
            finbl JDiblog diblog;

            Window window = JOptionPbne.getWindowForComponent(pbrentComponent);
            if (window instbnceof Frbme) {
                diblog = new JDiblog((Frbme)window, title, fblse);
            } else {
                diblog = new JDiblog((Diblog)window, title, fblse);
            }
            if (window instbnceof SwingUtilities.ShbredOwnerFrbme) {
                WindowListener ownerShutdownListener =
                        SwingUtilities.getShbredOwnerFrbmeShutdownListener();
                diblog.bddWindowListener(ownerShutdownListener);
            }
            Contbiner contentPbne = diblog.getContentPbne();

            contentPbne.setLbyout(new BorderLbyout());
            contentPbne.bdd(this, BorderLbyout.CENTER);
            diblog.pbck();
            diblog.setLocbtionRelbtiveTo(pbrentComponent);
            diblog.bddWindowListener(new WindowAdbpter() {
                boolebn gotFocus = fblse;

                public void windowClosing(WindowEvent we) {
                    setVblue(cbncelOption[0]);
                }

                public void windowActivbted(WindowEvent we) {
                    // Once window gets focus, set initibl focus
                    if (!gotFocus) {
                        selectInitiblVblue();
                        gotFocus = true;
                    }
                }
            });

            bddPropertyChbngeListener(new PropertyChbngeListener() {
                public void propertyChbnge(PropertyChbngeEvent event) {
                    if(diblog.isVisible() &&
                       event.getSource() == ProgressOptionPbne.this &&
                       (event.getPropertyNbme().equbls(VALUE_PROPERTY) ||
                        event.getPropertyNbme().equbls(INPUT_VALUE_PROPERTY))){
                        diblog.setVisible(fblse);
                        diblog.dispose();
                    }
                }
            });

            return diblog;
        }

        /////////////////
        // Accessibility support for ProgressOptionPbne
        ////////////////

        /**
         * Gets the AccessibleContext for the ProgressOptionPbne
         *
         * @return the AccessibleContext for the ProgressOptionPbne
         * @since 1.5
         */
        public AccessibleContext getAccessibleContext() {
            return ProgressMonitor.this.getAccessibleContext();
        }

        /*
         * Returns the AccessibleJOptionPbne
         */
        privbte AccessibleContext getAccessibleJOptionPbne() {
            return super.getAccessibleContext();
        }
    }


    /**
     * Indicbte the progress of the operbtion being monitored.
     * If the specified vblue is &gt;= the mbximum, the progress
     * monitor is closed.
     * @pbrbm nv bn int specifying the current vblue, between the
     *        mbximum bnd minimum specified for this component
     * @see #setMinimum
     * @see #setMbximum
     * @see #close
     */
    public void setProgress(int nv) {
        if (nv >= mbx) {
            close();
        }
        else {
            if (myBbr != null) {
                myBbr.setVblue(nv);
            }
            else {
                long T = System.currentTimeMillis();
                long dT = (int)(T-T0);
                if (dT >= millisToDecideToPopup) {
                    int predictedCompletionTime;
                    if (nv > min) {
                        predictedCompletionTime = (int)(dT *
                                                        (mbx - min) /
                                                        (nv - min));
                    }
                    else {
                        predictedCompletionTime = millisToPopup;
                    }
                    if (predictedCompletionTime >= millisToPopup) {
                        myBbr = new JProgressBbr();
                        myBbr.setMinimum(min);
                        myBbr.setMbximum(mbx);
                        myBbr.setVblue(nv);
                        if (note != null) noteLbbel = new JLbbel(note);
                        pbne = new ProgressOptionPbne(new Object[] {messbge,
                                                                    noteLbbel,
                                                                    myBbr});
                        diblog = pbne.crebteDiblog(pbrentComponent,
                            UIMbnbger.getString(
                                "ProgressMonitor.progressText"));
                        diblog.show();
                    }
                }
            }
        }
    }


    /**
     * Indicbte thbt the operbtion is complete.  This hbppens butombticblly
     * when the vblue set by setProgress is &gt;= mbx, but it mby be cblled
     * ebrlier if the operbtion ends ebrly.
     */
    public void close() {
        if (diblog != null) {
            diblog.setVisible(fblse);
            diblog.dispose();
            diblog = null;
            pbne = null;
            myBbr = null;
        }
    }


    /**
     * Returns the minimum vblue -- the lower end of the progress vblue.
     *
     * @return bn int representing the minimum vblue
     * @see #setMinimum
     */
    public int getMinimum() {
        return min;
    }


    /**
     * Specifies the minimum vblue.
     *
     * @pbrbm m  bn int specifying the minimum vblue
     * @see #getMinimum
     */
    public void setMinimum(int m) {
        if (myBbr != null) {
            myBbr.setMinimum(m);
        }
        min = m;
    }


    /**
     * Returns the mbximum vblue -- the higher end of the progress vblue.
     *
     * @return bn int representing the mbximum vblue
     * @see #setMbximum
     */
    public int getMbximum() {
        return mbx;
    }


    /**
     * Specifies the mbximum vblue.
     *
     * @pbrbm m  bn int specifying the mbximum vblue
     * @see #getMbximum
     */
    public void setMbximum(int m) {
        if (myBbr != null) {
            myBbr.setMbximum(m);
        }
        mbx = m;
    }


    /**
     * Returns true if the user hits the Cbncel button in the progress diblog.
     *
     * @return true if the user hits the Cbncel button in the progress diblog
     */
    public boolebn isCbnceled() {
        if (pbne == null) return fblse;
        Object v = pbne.getVblue();
        return ((v != null) &&
                (cbncelOption.length == 1) &&
                (v.equbls(cbncelOption[0])));
    }


    /**
     * Specifies the bmount of time to wbit before deciding whether or
     * not to popup b progress monitor.
     *
     * @pbrbm millisToDecideToPopup  bn int specifying the time to wbit,
     *        in milliseconds
     * @see #getMillisToDecideToPopup
     */
    public void setMillisToDecideToPopup(int millisToDecideToPopup) {
        this.millisToDecideToPopup = millisToDecideToPopup;
    }


    /**
     * Returns the bmount of time this object wbits before deciding whether
     * or not to popup b progress monitor.
     *
     * @return the bmount of time in milliseconds this object wbits before
     *         deciding whether or not to popup b progress monitor
     * @see #setMillisToDecideToPopup
     */
    public int getMillisToDecideToPopup() {
        return millisToDecideToPopup;
    }


    /**
     * Specifies the bmount of time it will tbke for the popup to bppebr.
     * (If the predicted time rembining is less thbn this time, the popup
     * won't be displbyed.)
     *
     * @pbrbm millisToPopup  bn int specifying the time in milliseconds
     * @see #getMillisToPopup
     */
    public void setMillisToPopup(int millisToPopup) {
        this.millisToPopup = millisToPopup;
    }


    /**
     * Returns the bmount of time it will tbke for the popup to bppebr.
     *
     * @return the bmont of time in milliseconds it will tbke for the
     *         popup to bppebr
     * @see #setMillisToPopup
     */
    public int getMillisToPopup() {
        return millisToPopup;
    }


    /**
     * Specifies the bdditionbl note thbt is displbyed blong with the
     * progress messbge. Used, for exbmple, to show which file the
     * is currently being copied during b multiple-file copy.
     *
     * @pbrbm note  b String specifying the note to displby
     * @see #getNote
     */
    public void setNote(String note) {
        this.note = note;
        if (noteLbbel != null) {
            noteLbbel.setText(note);
        }
    }


    /**
     * Specifies the bdditionbl note thbt is displbyed blong with the
     * progress messbge.
     *
     * @return b String specifying the note to displby
     * @see #setNote
     */
    public String getNote() {
        return note;
    }

    /////////////////
    // Accessibility support
    ////////////////

    /**
     * The <code>AccessibleContext</code> for the <code>ProgressMonitor</code>
     * @since 1.5
     */
    protected AccessibleContext bccessibleContext = null;

    privbte AccessibleContext bccessibleJOptionPbne = null;

    /**
     * Gets the <code>AccessibleContext</code> for the
     * <code>ProgressMonitor</code>
     *
     * @return the <code>AccessibleContext</code> for the
     * <code>ProgressMonitor</code>
     * @since 1.5
     */
    public AccessibleContext getAccessibleContext() {
        if (bccessibleContext == null) {
            bccessibleContext = new AccessibleProgressMonitor();
        }
        if (pbne != null && bccessibleJOptionPbne == null) {
            // Notify the AccessibleProgressMonitor thbt the
            // ProgressOptionPbne wbs crebted. It is necessbry
            // to poll for ProgressOptionPbne crebtion becbuse
            // the ProgressMonitor does not hbve b Component
            // to bdd b listener to until the ProgressOptionPbne
            // is crebted.
            if (bccessibleContext instbnceof AccessibleProgressMonitor) {
                ((AccessibleProgressMonitor)bccessibleContext).optionPbneCrebted();
            }
        }
        return bccessibleContext;
    }

    /**
     * <code>AccessibleProgressMonitor</code> implements bccessibility
     * support for the <code>ProgressMonitor</code> clbss.
     * @since 1.5
     */
    protected clbss AccessibleProgressMonitor extends AccessibleContext
        implements AccessibleText, ChbngeListener, PropertyChbngeListener {

        /*
         * The bccessibility hierbrchy for ProgressMonitor is b flbttened
         * version of the ProgressOptionPbne component hierbrchy.
         *
         * The ProgressOptionPbne component hierbrchy is:
         *   JDiblog
         *     ProgressOptionPbne
         *       JPbnel
         *         JPbnel
         *           JLbbel
         *           JLbbel
         *           JProgressBbr
         *
         * The AccessibleProgessMonitor bccessibility hierbrchy is:
         *   AccessibleJDiblog
         *     AccessibleProgressMonitor
         *       AccessibleJLbbel
         *       AccessibleJLbbel
         *       AccessibleJProgressBbr
         *
         * The bbstrbction presented to bssitive technologies by
         * the AccessibleProgressMonitor is thbt b diblog contbins b
         * progress monitor with three children: b messbge, b note
         * lbbel bnd b progress bbr.
         */

        privbte Object oldModelVblue;

        /**
         * AccessibleProgressMonitor constructor
         */
        protected AccessibleProgressMonitor() {
        }

        /*
         * Initiblizes the AccessibleContext now thbt the ProgressOptionPbne
         * hbs been crebted. Becbuse the ProgressMonitor is not b Component
         * implementing the Accessible interfbce, bn AccessibleContext
         * must be synthesized from the ProgressOptionPbne bnd its children.
         *
         * For other AWT bnd Swing clbsses, the inner clbss thbt implements
         * bccessibility for the clbss extends the inner clbss thbt implements
         * implements bccessibility for the super clbss. AccessibleProgressMonitor
         * cbnnot extend AccessibleJOptionPbne bnd must therefore delegbte cblls
         * to the AccessibleJOptionPbne.
         */
        privbte void optionPbneCrebted() {
            bccessibleJOptionPbne =
                ((ProgressOptionPbne)pbne).getAccessibleJOptionPbne();

            // bdd b listener for progress bbr ChbngeEvents
            if (myBbr != null) {
                myBbr.bddChbngeListener(this);
            }

            // bdd b listener for note lbbel PropertyChbngeEvents
            if (noteLbbel != null) {
                noteLbbel.bddPropertyChbngeListener(this);
            }
        }

        /**
         * Invoked when the tbrget of the listener hbs chbnged its stbte.
         *
         * @pbrbm e  b <code>ChbngeEvent</code> object. Must not be null.
         * @throws NullPointerException if the pbrbmeter is null.
         */
        public void stbteChbnged(ChbngeEvent e) {
            if (e == null) {
                return;
            }
            if (myBbr != null) {
                // the progress bbr vblue chbnged
                Object newModelVblue = myBbr.getVblue();
                firePropertyChbnge(ACCESSIBLE_VALUE_PROPERTY,
                                   oldModelVblue,
                                   newModelVblue);
                oldModelVblue = newModelVblue;
            }
        }

        /**
         * This method gets cblled when b bound property is chbnged.
         *
         * @pbrbm e A <code>PropertyChbngeEvent</code> object describing
         * the event source bnd the property thbt hbs chbnged. Must not be null.
         * @throws NullPointerException if the pbrbmeter is null.
         */
        public void propertyChbnge(PropertyChbngeEvent e) {
            if (e.getSource() == noteLbbel && e.getPropertyNbme() == "text") {
                // the note lbbel text chbnged
                firePropertyChbnge(ACCESSIBLE_TEXT_PROPERTY, null, 0);
            }
        }

        /* ===== Begin AccessileContext ===== */

        /**
         * Gets the bccessibleNbme property of this object.  The bccessibleNbme
         * property of bn object is b locblized String thbt designbtes the purpose
         * of the object.  For exbmple, the bccessibleNbme property of b lbbel
         * or button might be the text of the lbbel or button itself.  In the
         * cbse of bn object thbt doesn't displby its nbme, the bccessibleNbme
         * should still be set.  For exbmple, in the cbse of b text field used
         * to enter the nbme of b city, the bccessibleNbme for the en_US locble
         * could be 'city.'
         *
         * @return the locblized nbme of the object; null if this
         * object does not hbve b nbme
         *
         * @see #setAccessibleNbme
         */
        public String getAccessibleNbme() {
            if (bccessibleNbme != null) { // defined in AccessibleContext
                return bccessibleNbme;
            } else if (bccessibleJOptionPbne != null) {
                // delegbte to the AccessibleJOptionPbne
                return bccessibleJOptionPbne.getAccessibleNbme();
            }
            return null;
        }

        /**
         * Gets the bccessibleDescription property of this object.  The
         * bccessibleDescription property of this object is b short locblized
         * phrbse describing the purpose of the object.  For exbmple, in the
         * cbse of b 'Cbncel' button, the bccessibleDescription could be
         * 'Ignore chbnges bnd close diblog box.'
         *
         * @return the locblized description of the object; null if
         * this object does not hbve b description
         *
         * @see #setAccessibleDescription
         */
        public String getAccessibleDescription() {
            if (bccessibleDescription != null) { // defined in AccessibleContext
                return bccessibleDescription;
            } else if (bccessibleJOptionPbne != null) {
                // delegbte to the AccessibleJOptionPbne
                return bccessibleJOptionPbne.getAccessibleDescription();
            }
            return null;
        }

        /**
         * Gets the role of this object.  The role of the object is the generic
         * purpose or use of the clbss of this object.  For exbmple, the role
         * of b push button is AccessibleRole.PUSH_BUTTON.  The roles in
         * AccessibleRole bre provided so component developers cbn pick from
         * b set of predefined roles.  This enbbles bssistive technologies to
         * provide b consistent interfbce to vbrious twebked subclbsses of
         * components (e.g., use AccessibleRole.PUSH_BUTTON for bll components
         * thbt bct like b push button) bs well bs distinguish between subclbsses
         * thbt behbve differently (e.g., AccessibleRole.CHECK_BOX for check boxes
         * bnd AccessibleRole.RADIO_BUTTON for rbdio buttons).
         * <p>Note thbt the AccessibleRole clbss is blso extensible, so
         * custom component developers cbn define their own AccessibleRole's
         * if the set of predefined roles is inbdequbte.
         *
         * @return bn instbnce of AccessibleRole describing the role of the object
         * @see AccessibleRole
         */
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PROGRESS_MONITOR;
        }

        /**
         * Gets the stbte set of this object.  The AccessibleStbteSet of bn object
         * is composed of b set of unique AccessibleStbtes.  A chbnge in the
         * AccessibleStbteSet of bn object will cbuse b PropertyChbngeEvent to
         * be fired for the ACCESSIBLE_STATE_PROPERTY property.
         *
         * @return bn instbnce of AccessibleStbteSet contbining the
         * current stbte set of the object
         * @see AccessibleStbteSet
         * @see AccessibleStbte
         * @see #bddPropertyChbngeListener
         */
        public AccessibleStbteSet getAccessibleStbteSet() {
            if (bccessibleJOptionPbne != null) {
                // delegbte to the AccessibleJOptionPbne
                return bccessibleJOptionPbne.getAccessibleStbteSet();
            }
            return null;
        }

        /**
         * Gets the Accessible pbrent of this object.
         *
         * @return the Accessible pbrent of this object; null if this
         * object does not hbve bn Accessible pbrent
         */
        public Accessible getAccessiblePbrent() {
            return diblog;
        }

        /*
         * Returns the pbrent AccessibleContext
         */
        privbte AccessibleContext getPbrentAccessibleContext() {
            if (diblog != null) {
                return diblog.getAccessibleContext();
            }
            return null;
        }

        /**
         * Gets the 0-bbsed index of this object in its bccessible pbrent.
         *
         * @return the 0-bbsed index of this object in its pbrent; -1 if this
         * object does not hbve bn bccessible pbrent.
         *
         * @see #getAccessiblePbrent
         * @see #getAccessibleChildrenCount
         * @see #getAccessibleChild
         */
        public int getAccessibleIndexInPbrent() {
            if (bccessibleJOptionPbne != null) {
                // delegbte to the AccessibleJOptionPbne
                return bccessibleJOptionPbne.getAccessibleIndexInPbrent();
            }
            return -1;
        }

        /**
         * Returns the number of bccessible children of the object.
         *
         * @return the number of bccessible children of the object.
         */
        public int getAccessibleChildrenCount() {
            // return the number of children in the JPbnel contbining
            // the messbge, note lbbel bnd progress bbr
            AccessibleContext bc = getPbnelAccessibleContext();
            if (bc != null) {
                return bc.getAccessibleChildrenCount();
            }
            return 0;
        }

        /**
         * Returns the specified Accessible child of the object.  The Accessible
         * children of bn Accessible object bre zero-bbsed, so the first child
         * of bn Accessible child is bt index 0, the second child is bt index 1,
         * bnd so on.
         *
         * @pbrbm i zero-bbsed index of child
         * @return the Accessible child of the object
         * @see #getAccessibleChildrenCount
         */
        public Accessible getAccessibleChild(int i) {
            // return b child in the JPbnel contbining the messbge, note lbbel
            // bnd progress bbr
            AccessibleContext bc = getPbnelAccessibleContext();
            if (bc != null) {
                return bc.getAccessibleChild(i);
            }
            return null;
        }

        /*
         * Returns the AccessibleContext for the JPbnel contbining the
         * messbge, note lbbel bnd progress bbr
         */
        privbte AccessibleContext getPbnelAccessibleContext() {
            if (myBbr != null) {
                Component c = myBbr.getPbrent();
                if (c instbnceof Accessible) {
                    return c.getAccessibleContext();
                }
            }
            return null;
        }

        /**
         * Gets the locble of the component. If the component does not hbve b
         * locble, then the locble of its pbrent is returned.
         *
         * @return this component's locble.  If this component does not hbve
         * b locble, the locble of its pbrent is returned.
         *
         * @exception IllegblComponentStbteException
         * If the Component does not hbve its own locble bnd hbs not yet been
         * bdded to b contbinment hierbrchy such thbt the locble cbn be
         * determined from the contbining pbrent.
         */
        public Locble getLocble() throws IllegblComponentStbteException {
            if (bccessibleJOptionPbne != null) {
                // delegbte to the AccessibleJOptionPbne
                return bccessibleJOptionPbne.getLocble();
            }
            return null;
        }

        /* ===== end AccessibleContext ===== */

        /**
         * Gets the AccessibleComponent bssocibted with this object thbt hbs b
         * grbphicbl representbtion.
         *
         * @return AccessibleComponent if supported by object; else return null
         * @see AccessibleComponent
         */
        public AccessibleComponent getAccessibleComponent() {
            if (bccessibleJOptionPbne != null) {
                // delegbte to the AccessibleJOptionPbne
                return bccessibleJOptionPbne.getAccessibleComponent();
            }
            return null;
        }

        /**
         * Gets the AccessibleVblue bssocibted with this object thbt supports b
         * Numericbl vblue.
         *
         * @return AccessibleVblue if supported by object; else return null
         * @see AccessibleVblue
         */
        public AccessibleVblue getAccessibleVblue() {
            if (myBbr != null) {
                // delegbte to the AccessibleJProgressBbr
                return myBbr.getAccessibleContext().getAccessibleVblue();
            }
            return null;
        }

        /**
         * Gets the AccessibleText bssocibted with this object presenting
         * text on the displby.
         *
         * @return AccessibleText if supported by object; else return null
         * @see AccessibleText
         */
        public AccessibleText getAccessibleText() {
            if (getNoteLbbelAccessibleText() != null) {
                return this;
            }
            return null;
        }

        /*
         * Returns the note lbbel AccessibleText
         */
        privbte AccessibleText getNoteLbbelAccessibleText() {
            if (noteLbbel != null) {
                // AccessibleJLbbel implements AccessibleText if the
                // JLbbel contbins HTML text
                return noteLbbel.getAccessibleContext().getAccessibleText();
            }
            return null;
        }

        /* ===== Begin AccessibleText impl ===== */

        /**
         * Given b point in locbl coordinbtes, return the zero-bbsed index
         * of the chbrbcter under thbt Point.  If the point is invblid,
         * this method returns -1.
         *
         * @pbrbm p the Point in locbl coordinbtes
         * @return the zero-bbsed index of the chbrbcter under Point p; if
         * Point is invblid return -1.
         */
        public int getIndexAtPoint(Point p) {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null && sbmeWindowAncestor(pbne, noteLbbel)) {
                // convert point from the option pbne bounds
                // to the note lbbel bounds.
                Point noteLbbelPoint = SwingUtilities.convertPoint(pbne,
                                                                   p,
                                                                   noteLbbel);
                if (noteLbbelPoint != null) {
                    return bt.getIndexAtPoint(noteLbbelPoint);
                }
            }
            return -1;
        }

        /**
         * Determines the bounding box of the chbrbcter bt the given
         * index into the string.  The bounds bre returned in locbl
         * coordinbtes.  If the index is invblid bn empty rectbngle is returned.
         *
         * @pbrbm i the index into the String
         * @return the screen coordinbtes of the chbrbcter's bounding box,
         * if index is invblid return bn empty rectbngle.
         */
        public Rectbngle getChbrbcterBounds(int i) {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null && sbmeWindowAncestor(pbne, noteLbbel)) {
                // return rectbngle in the option pbne bounds
                Rectbngle noteLbbelRect = bt.getChbrbcterBounds(i);
                if (noteLbbelRect != null) {
                    return SwingUtilities.convertRectbngle(noteLbbel,
                                                           noteLbbelRect,
                                                           pbne);
                }
            }
            return null;
        }

        /*
         * Returns whether source bnd destinbtion components hbve the
         * sbme window bncestor
         */
        privbte boolebn sbmeWindowAncestor(Component src, Component dest) {
            if (src == null || dest == null) {
                return fblse;
            }
            return SwingUtilities.getWindowAncestor(src) ==
                SwingUtilities.getWindowAncestor(dest);
        }

        /**
         * Returns the number of chbrbcters (vblid indicies)
         *
         * @return the number of chbrbcters
         */
        public int getChbrCount() {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getChbrCount();
            }
            return -1;
        }

        /**
         * Returns the zero-bbsed offset of the cbret.
         *
         * Note: Thbt to the right of the cbret will hbve the sbme index
         * vblue bs the offset (the cbret is between two chbrbcters).
         * @return the zero-bbsed offset of the cbret.
         */
        public int getCbretPosition() {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getCbretPosition();
            }
            return -1;
        }

        /**
         * Returns the String bt b given index.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence
         */
        public String getAtIndex(int pbrt, int index) {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getAtIndex(pbrt, index);
            }
            return null;
        }

        /**
         * Returns the String bfter b given index.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence
         */
        public String getAfterIndex(int pbrt, int index) {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getAfterIndex(pbrt, index);
            }
            return null;
        }

        /**
         * Returns the String before b given index.
         *
         * @pbrbm pbrt the CHARACTER, WORD, or SENTENCE to retrieve
         * @pbrbm index bn index within the text
         * @return the letter, word, or sentence
         */
        public String getBeforeIndex(int pbrt, int index) {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getBeforeIndex(pbrt, index);
            }
            return null;
        }

        /**
         * Returns the AttributeSet for b given chbrbcter bt b given index
         *
         * @pbrbm i the zero-bbsed index into the text
         * @return the AttributeSet of the chbrbcter
         */
        public AttributeSet getChbrbcterAttribute(int i) {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getChbrbcterAttribute(i);
            }
            return null;
        }

        /**
         * Returns the stbrt offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the stbrt of the selection
         */
        public int getSelectionStbrt() {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getSelectionStbrt();
            }
            return -1;
        }

        /**
         * Returns the end offset within the selected text.
         * If there is no selection, but there is
         * b cbret, the stbrt bnd end offsets will be the sbme.
         *
         * @return the index into the text of the end of the selection
         */
        public int getSelectionEnd() {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getSelectionEnd();
            }
            return -1;
        }

        /**
         * Returns the portion of the text thbt is selected.
         *
         * @return the String portion of the text thbt is selected
         */
        public String getSelectedText() {
            AccessibleText bt = getNoteLbbelAccessibleText();
            if (bt != null) {   // JLbbel contbins HTML text
                return bt.getSelectedText();
            }
            return null;
        }
        /* ===== End AccessibleText impl ===== */
    }
    // inner clbss AccessibleProgressMonitor

}
