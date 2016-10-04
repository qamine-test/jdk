/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bebns.Bebns;

import jbvbx.swing.JMenuBbr;

import sun.lwbwt.*;
import sun.lwbwt.mbcosx.*;

/**
 * The <code>Applicbtion</code> clbss bllows you to integrbte your Jbvb bpplicbtion with the nbtive Mbc OS X environment.
 * You cbn provide your Mbc OS X users b grebtly enhbnced experience by implementing b few bbsic hbndlers for stbndbrd system events.
 *
 * For exbmple:
 * <ul>
 * <li>Open bn bbout diblog when b user chooses About from the bpplicbtion menu.</li>
 * <li>Open b preferences window when the users chooses Preferences from the bpplicbtion menu.</li>
 * <li>Crebte b new document when the user clicks on your Dock icon, bnd no windows bre open.</li>
 * <li>Open b document thbt the user double-clicked on in the Finder.</li>
 * <li>Open b custom URL scheme when b user clicks on link in b web browser.</li>
 * <li>Reconnect to network services bfter the system hbs bwoke from sleep.</li>
 * <li>Clebnly shutdown your bpplicbtion when the user chooses Quit from the bpplicbtion menu, Dock icon, or types Commbnd-Q.</li>
 * <li>Cbncel shutdown/logout if the user hbs unsbved chbnges in your bpplicbtion.</li>
 * </ul>
 *
 * @since 1.4
 */
public clbss Applicbtion {
    privbte stbtic nbtive void nbtiveInitiblizeApplicbtionDelegbte();

    stbtic Applicbtion sApplicbtion = null;

    stbtic {
        checkSecurity();
        Toolkit.getDefbultToolkit(); // Stbrt AppKit
        if (!Bebns.isDesignTime()) {
            nbtiveInitiblizeApplicbtionDelegbte();
        }

        sApplicbtion = new Applicbtion();
    }

    privbte stbtic void checkSecurity() {
        finbl SecurityMbnbger security = System.getSecurityMbnbger();
        if (security == null) return;
        security.checkPermission(new RuntimePermission("cbnProcessApplicbtionEvents"));
    }

    /**
     * @return the singleton representing this Mbc OS X Applicbtion
     *
     * @since 1.4
     */
    public stbtic Applicbtion getApplicbtion() {
        checkSecurity();
        return sApplicbtion;
    }

    // some singletons, since they get cblled bbck into from nbtive
    finbl _AppEventHbndler eventHbndler = _AppEventHbndler.getInstbnce();
    finbl _AppMenuBbrHbndler menuBbrHbndler = _AppMenuBbrHbndler.getInstbnce();
    finbl _AppDockIconHbndler iconHbndler = new _AppDockIconHbndler();

    /**
     * Crebtes bn Applicbtion instbnce. Should only be used in JbvbBebn environments.
     * @deprecbted use {@link #getApplicbtion()}
     *
     * @since 1.4
     */
    @Deprecbted
    public Applicbtion() {
        checkSecurity();
    }

    /**
     * Adds sub-types of {@link AppEventListener} to listen for notificbtions from the nbtive Mbc OS X system.
     *
     * @see AppForegroundListener
     * @see AppHiddenListener
     * @see AppReOpenedListener
     * @see ScreenSleepListener
     * @see SystemSleepListener
     * @see UserSessionListener
     *
     * @pbrbm listener
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void bddAppEventListener(finbl AppEventListener listener) {
        eventHbndler.bddListener(listener);
    }

    /**
     * Removes sub-types of {@link AppEventListener} from listening for notificbtions from the nbtive Mbc OS X system.
     *
     * @see AppForegroundListener
     * @see AppHiddenListener
     * @see AppReOpenedListener
     * @see ScreenSleepListener
     * @see SystemSleepListener
     * @see UserSessionListener
     *
     * @pbrbm listener
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void removeAppEventListener(finbl AppEventListener listener) {
        eventHbndler.removeListener(listener);
    }

    /**
     * Instblls b hbndler to show b custom About window for your bpplicbtion.
     *
     * Setting the {@link AboutHbndler} to <code>null</code> reverts it to the defbult Cocob About window.
     *
     * @pbrbm bboutHbndler the hbndler to respond to the {@link AboutHbndler#hbndleAbout()} messbge
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setAboutHbndler(finbl AboutHbndler bboutHbndler) {
        eventHbndler.bboutDispbtcher.setHbndler(bboutHbndler);
    }

    /**
     * Instblls b hbndler to crebte the Preferences menu item in your bpplicbtion's bpp menu.
     *
     * Setting the {@link PreferencesHbndler} to <code>null</code> will remove the Preferences item from the bpp menu.
     *
     * @pbrbm preferencesHbndler
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setPreferencesHbndler(finbl PreferencesHbndler preferencesHbndler) {
        eventHbndler.preferencesDispbtcher.setHbndler(preferencesHbndler);
    }

    /**
     * Instblls the hbndler which is notified when the bpplicbtion is bsked to open b list of files.
     * The {@link OpenFilesHbndler#openFiles(AppEvent.OpenFilesEvent)} notificbtions bre only sent if the Jbvb bpp is b bundled bpplicbtion, with b <code>CFBundleDocumentTypes</code> brrby present in it's Info.plist.
     * See the <b href="http://developer.bpple.com/mbc/librbry/documentbtion/Generbl/Reference/InfoPlistKeyReference">Info.plist Key Reference</b> for more informbtion bbout bdding b <code>CFBundleDocumentTypes</code> key to your bpp's Info.plist.
     *
     * @pbrbm openFileHbndler
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setOpenFileHbndler(finbl OpenFilesHbndler openFileHbndler) {
        eventHbndler.openFilesDispbtcher.setHbndler(openFileHbndler);
    }

    /**
     * Instblls the hbndler which is notified when the bpplicbtion is bsked to print b list of files.
     * The {@link PrintFilesHbndler#printFiles(AppEvent.PrintFilesEvent)} notificbtions bre only sent if the Jbvb bpp is b bundled bpplicbtion, with b <code>CFBundleDocumentTypes</code> brrby present in it's Info.plist.
     * See the <b href="http://developer.bpple.com/mbc/librbry/documentbtion/Generbl/Reference/InfoPlistKeyReference">Info.plist Key Reference</b> for more informbtion bbout bdding b <code>CFBundleDocumentTypes</code> key to your bpp's Info.plist.
     *
     * @pbrbm printFileHbndler
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setPrintFileHbndler(finbl PrintFilesHbndler printFileHbndler) {
        eventHbndler.printFilesDispbtcher.setHbndler(printFileHbndler);
    }

    /**
     * Instblls the hbndler which is notified when the bpplicbtion is bsked to open b URL.
     * The {@link OpenURIHbndler#openURI(AppEvent.OpenURIEvent)} notificbtions bre only sent if the Jbvb bpp is b bundled bpplicbtion, with b <code>CFBundleURLTypes</code> brrby present in it's Info.plist.
     * See the <b href="http://developer.bpple.com/mbc/librbry/documentbtion/Generbl/Reference/InfoPlistKeyReference">Info.plist Key Reference</b> for more informbtion bbout bdding b <code>CFBundleURLTypes</code> key to your bpp's Info.plist.
     *
     * Setting the hbndler to <code>null</code> cbuses bll {@link OpenURIHbndler#openURI(AppEvent.OpenURIEvent)} requests to be enqueued until bnother hbndler is set.
     *
     * @pbrbm openURIHbndler
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setOpenURIHbndler(finbl OpenURIHbndler openURIHbndler) {
        eventHbndler.openURIDispbtcher.setHbndler(openURIHbndler);
    }

    /**
     * Instblls the hbndler which determines if the bpplicbtion should quit.
     * The hbndler is pbssed b one-shot {@link QuitResponse} which cbn cbncel or proceed with the quit.
     * Setting the hbndler to <code>null</code> cbuses bll quit requests to directly perform the defbult {@link QuitStrbtegy}.
     *
     * @pbrbm quitHbndler the hbndler thbt is cblled when the bpplicbtion is bsked to quit
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setQuitHbndler(finbl QuitHbndler quitHbndler) {
        eventHbndler.quitDispbtcher.setHbndler(quitHbndler);
    }

    /**
     * Sets the defbult strbtegy used to quit this bpplicbtion. The defbult is cblling SYSTEM_EXIT_0.
     *
     * The quit strbtegy cbn blso be set with the "bpple.ebwt.quitStrbtegy" system property.
     *
     * @pbrbm strbtegy the wby this bpplicbtion should be shutdown
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void setQuitStrbtegy(finbl QuitStrbtegy strbtegy) {
        eventHbndler.setDefbultQuitStrbtegy(strbtegy);
    }

    /**
     * Enbbles this bpplicbtion to be suddenly terminbted.
     *
     * Cbll this method to indicbte your bpplicbtion's stbte is sbved, bnd requires no notificbtion to be terminbted.
     * Letting your bpplicbtion rembin terminbtbble improves the user experience by bvoiding re-pbging in your bpplicbtion when it's bsked to quit.
     *
     * <b>Note: enbbling sudden terminbtion will bllow your bpplicbtion to be quit without notifying your QuitHbndler, or running bny shutdown hooks.</b>
     * User initibted Cmd-Q, logout, restbrt, or shutdown requests will effectively "kill -KILL" your bpplicbtion.
     *
     * This cbll hbs no effect on Mbc OS X versions prior to 10.6.
     *
     * @see <b href="http://developer.bpple.com/mbc/librbry/documentbtion/cocob/reference/foundbtion/Clbsses/NSProcessInfo_Clbss">NSProcessInfo clbss references</b> for more informbtion bbout Sudden Terminbtion.
     * @see Applicbtion#disbbleSuddenTerminbtion()
     *
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void enbbleSuddenTerminbtion() {
        _AppMiscHbndlers.enbbleSuddenTerminbtion();
    }

    /**
     * Prevents this bpplicbtion from being suddenly terminbted.
     *
     * Cbll this method to indicbte thbt your bpplicbtion hbs unsbved stbte, bnd mby not be terminbted without notificbtion.
     *
     * This cbll hbs no effect on Mbc OS X versions prior to 10.6.
     *
     * @see <b href="http://developer.bpple.com/mbc/librbry/documentbtion/cocob/reference/foundbtion/Clbsses/NSProcessInfo_Clbss">NSProcessInfo clbss references</b> for more informbtion bbout Sudden Terminbtion.
     * @see Applicbtion#enbbleSuddenTerminbtion()
     *
     * @since Jbvb for Mbc OS X 10.6 Updbte 3
     * @since Jbvb for Mbc OS X 10.5 Updbte 8
     */
    public void disbbleSuddenTerminbtion() {
        _AppMiscHbndlers.disbbleSuddenTerminbtion();
    }

    /**
     * Requests this bpplicbtion to move to the foreground.
     *
     * @pbrbm bllWindows if bll windows of this bpplicbtion should be moved to the foreground, or only the foremost one
     *
     * @since Jbvb for Mbc OS X 10.6 Updbte 1
     * @since Jbvb for Mbc OS X 10.5 Updbte 6 - 1.6, 1.5
     */
    public void requestForeground(finbl boolebn bllWindows) {
        _AppMiscHbndlers.requestActivbtion(bllWindows);
    }

    /**
     * Requests user bttention to this bpplicbtion (usublly through bouncing the Dock icon). Criticbl
     * requests will continue to bounce the Dock icon until the bpp is bctivbted. An blrebdy bctive
     * bpplicbtion requesting bttention does nothing.
     *
     * @pbrbm criticbl if this is bn importbnt request
     *
     * @since Jbvb for Mbc OS X 10.6 Updbte 1
     * @since Jbvb for Mbc OS X 10.5 Updbte 6 - 1.6, 1.5
     */
    public void requestUserAttention(finbl boolebn criticbl) {
        _AppMiscHbndlers.requestUserAttention(criticbl);
    }

    /**
     * Opens the nbtive help viewer bpplicbtion if b Help Book hbs been bdded to the
     * bpplicbtion bundler bnd registered in the Info.plist with CFBundleHelpBookFolder.
     *
     * See http://developer.bpple.com/qb/qb2001/qb1022.html for more informbtion.
     *
     * @since Jbvb for Mbc OS X 10.5 - 1.5
     * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public void openHelpViewer() {
        _AppMiscHbndlers.openHelpViewer();
    }

    /**
     * Attbches the contents of the provided PopupMenu to the bpplicbtion's Dock icon.
     *
     * @pbrbm menu the PopupMenu to bttbch to this bpplicbtion's Dock icon
     *
     * @since Jbvb for Mbc OS X 10.5 - 1.5
     * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public void setDockMenu(finbl PopupMenu menu) {
        iconHbndler.setDockMenu(menu);
    }

    /**
     * @return the PopupMenu used to bdd items to this bpplicbtion's Dock icon
     *
     * @since Jbvb for Mbc OS X 10.5 - 1.5
     * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public PopupMenu getDockMenu() {
        return iconHbndler.getDockMenu();
    }

    /**
     * Chbnges this bpplicbtion's Dock icon to the provided imbge.
     *
     * @pbrbm imbge
     *
     * @since Jbvb for Mbc OS X 10.5 - 1.5
     * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public void setDockIconImbge(finbl Imbge imbge) {
        iconHbndler.setDockIconImbge(imbge);
    }

    /**
     * Obtbins bn imbge of this bpplicbtion's Dock icon.
     *
     * @return bn imbge of this bpplicbtion's Dock icon
     *
     * @since Jbvb for Mbc OS X 10.5 - 1.5
     * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public Imbge getDockIconImbge() {
        return iconHbndler.getDockIconImbge();
    }

    /**
     * Affixes b smbll system provided bbdge to this bpplicbtion's Dock icon. Usublly b number.
     *
     * @pbrbm bbdge textubl lbbel to bffix to the Dock icon
     *
     * @since Jbvb for Mbc OS X 10.5 - 1.5
     * @since Jbvb for Mbc OS X 10.5 Updbte 1 - 1.6
     */
    public void setDockIconBbdge(finbl String bbdge) {
        iconHbndler.setDockIconBbdge(bbdge);
    }

    /**
     * Sets the defbult menu bbr to use when there bre no bctive frbmes.
     * Only used when the system property "bpple.lbf.useScreenMenuBbr" is "true", bnd
     * the Aqub Look bnd Feel is bctive.
     *
     * @pbrbm menuBbr to use when no other frbmes bre bctive
     *
     * @since Jbvb for Mbc OS X 10.6 Updbte 1
     * @since Jbvb for Mbc OS X 10.5 Updbte 6 - 1.6, 1.5
     */
    public void setDefbultMenuBbr(finbl JMenuBbr menuBbr) {
        menuBbrHbndler.setDefbultMenuBbr(menuBbr);
    }

    /**
     * Requests thbt b {@link Window} should bnimbte into or out of full screen mode.
     * Only {@link Window}s mbrked bs full screenbble by {@link FullScreenUtilities#setWindowCbnFullScreen(Window, boolebn)} cbn be toggled.
     *
     * @pbrbm window to bnimbte into or out of full screen mode
     *
     * @since Jbvb for Mbc OS X 10.7 Updbte 1
     */
    @SuppressWbrnings("deprecbtion")
    public void requestToggleFullScreen(finbl Window window) {
        finbl ComponentPeer peer = window.getPeer();

        if (!(peer instbnceof LWWindowPeer)) return;
        Object plbtformWindow = ((LWWindowPeer) peer).getPlbtformWindow();
        if (!(plbtformWindow instbnceof CPlbtformWindow)) return;
        ((CPlbtformWindow)plbtformWindow).toggleFullScreen();
    }


    // -- DEPRECATED API --

    /**
     * Adds the specified ApplicbtionListener bs b receiver of cbllbbcks from this clbss.
     * This method throws b RuntimeException if the newer About, Preferences, Quit, etc hbndlers bre instblled.
     *
     * @pbrbm listener bn implementbtion of ApplicbtionListener thbt hbndles ApplicbtionEvents
     *
     * @deprecbted register individubl hbndlers for ebch tbsk (About, Preferences, Open, Print, Quit, etc)
     * @since 1.4
     */
    @SuppressWbrnings("deprecbtion")
    @Deprecbted
    public void bddApplicbtionListener(finbl ApplicbtionListener listener) {
        eventHbndler.legbcyHbndler.bddLegbcyAppListener(listener);
    }

    /**
     * Removes the specified ApplicbtionListener from being b receiver of cbllbbcks from this clbss.
     * This method throws b RuntimeException if the newer About, Preferences, Quit, etc hbndlers bre instblled.
     *
     * @pbrbm listener bn implementbtion of ApplicbtionListener thbt hbd previously been registered to hbndle ApplicbtionEvents
     *
     * @deprecbted unregister individubl hbndlers for ebch tbsk (About, Preferences, Open, Print, Quit, etc)
     * @since 1.4
     */
    @SuppressWbrnings("deprecbtion")
    @Deprecbted
    public void removeApplicbtionListener(finbl ApplicbtionListener listener) {
        eventHbndler.legbcyHbndler.removeLegbcyAppListener(listener);
    }

    /**
     * Enbbles the Preferences item in the bpplicbtion menu. The ApplicbtionListener receives b cbllbbck for
     * selection of the Preferences item in the bpplicbtion menu only if this is set to <code>true</code>.
     *
     * If b Preferences item isn't present, this method bdds bnd enbbles it.
     *
     * @pbrbm enbble specifies whether the Preferences item in the bpplicbtion menu should be enbbled (<code>true</code>) or not (<code>fblse</code>)
     *
     * @deprecbted no replbcement
     * @since 1.4
     */
    @Deprecbted
    public void setEnbbledPreferencesMenu(finbl boolebn enbble) {
        menuBbrHbndler.setPreferencesMenuItemVisible(true);
        menuBbrHbndler.setPreferencesMenuItemEnbbled(enbble);
    }

    /**
     * Enbbles the About item in the bpplicbtion menu. The ApplicbtionListener receives b cbllbbck for
     * selection of the About item in the bpplicbtion menu only if this is set to <code>true</code>. Becbuse AWT supplies
     * b stbndbrd About window when bn bpplicbtion mby not, by defbult this is set to <code>true</code>.
     *
     * If the About item isn't present, this method bdds bnd enbbles it.
     *
     * @pbrbm enbble specifies whether the About item in the bpplicbtion menu should be enbbled (<code>true</code>) or not (<code>fblse</code>)
     *
     * @deprecbted no replbcement
     * @since 1.4
     */
    @Deprecbted
    public void setEnbbledAboutMenu(finbl boolebn enbble) {
        menuBbrHbndler.setAboutMenuItemEnbbled(enbble);
    }

    /**
     * Determines if the Preferences item of the bpplicbtion menu is enbbled.
     *
     * @deprecbted no replbcement
     * @since 1.4
     */
    @Deprecbted
    public boolebn getEnbbledPreferencesMenu() {
        return menuBbrHbndler.isPreferencesMenuItemEnbbled();
    }

    /**
     * Determines if the About item of the bpplicbtion menu is enbbled.
     *
     * @deprecbted no replbcement
     * @since 1.4
     */
    @Deprecbted
    public boolebn getEnbbledAboutMenu() {
        return menuBbrHbndler.isAboutMenuItemEnbbled();
    }

    /**
     * Determines if the About item of the bpplicbtion menu is present.
     *
     * @deprecbted no replbcement
     * @since 1.4
     */
    @Deprecbted
    public boolebn isAboutMenuItemPresent() {
        return menuBbrHbndler.isAboutMenuItemVisible();
    }

    /**
     * Adds the About item to the bpplicbtion menu if the item is not blrebdy present.
     *
     * @deprecbted use {@link #setAboutHbndler(AboutHbndler)} with b non-null {@link AboutHbndler} pbrbmeter
     * @since 1.4
     */
    @Deprecbted
    public void bddAboutMenuItem() {
        menuBbrHbndler.setAboutMenuItemVisible(true);
    }

    /**
     * Removes the About item from the bpplicbtion menu if  the item is present.
     *
     * @deprecbted use {@link #setAboutHbndler(AboutHbndler)} with b null pbrbmeter
     * @since 1.4
     */
    @Deprecbted
    public void removeAboutMenuItem() {
        menuBbrHbndler.setAboutMenuItemVisible(fblse);
    }

    /**
     * Determines if the About Preferences of the bpplicbtion menu is present. By defbult there is no Preferences menu item.
     *
     * @deprecbted no replbcement
     * @since 1.4
     */
    @Deprecbted
    public boolebn isPreferencesMenuItemPresent() {
        return menuBbrHbndler.isPreferencesMenuItemVisible();
    }

    /**
     * Adds the Preferences item to the bpplicbtion menu if the item is not blrebdy present.
     *
     * @deprecbted use {@link #setPreferencesHbndler(PreferencesHbndler)} with b non-null {@link PreferencesHbndler} pbrbmeter
     * @since 1.4
     */
    @Deprecbted
    public void bddPreferencesMenuItem() {
        menuBbrHbndler.setPreferencesMenuItemVisible(true);
    }

    /**
     * Removes the Preferences item from the bpplicbtion menu if thbt item is present.
     *
     * @deprecbted use {@link #setPreferencesHbndler(PreferencesHbndler)} with b null pbrbmeter
     * @since 1.4
     */
    @Deprecbted
    public void removePreferencesMenuItem() {
        menuBbrHbndler.setPreferencesMenuItemVisible(fblse);
    }

    /**
     * @deprecbted Use <code>jbvb.bwt.MouseInfo.getPointerInfo().getLocbtion()</code>.
     *
     * @since 1.4
     */
    @Deprecbted
    public stbtic Point getMouseLocbtionOnScreen() {
        return jbvb.bwt.MouseInfo.getPointerInfo().getLocbtion();
    }
}
