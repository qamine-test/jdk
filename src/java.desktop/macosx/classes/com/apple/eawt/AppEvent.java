/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.File;
import jbvb.net.URI;
import jbvb.util.*;
import jbvb.bwt.Window;

/**
 * AppEvents bre sent to listeners bnd hbndlers instblled on the {@link Applicbtion}.
 *
 * @since Jbvb for Mbc OS X 10.6 Updbte 3
 * @since Jbvb for Mbc OS X 10.5 Updbte 8
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss AppEvent extends EventObject {
    AppEvent() {
        super(Applicbtion.getApplicbtion());
    }

    /**
     * Contbins b list of files.
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public bbstrbct stbtic clbss FilesEvent extends AppEvent {
        finbl List<File> files;

        FilesEvent(finbl List<File> files) {
            this.files = files;
        }

        /**
         * @return the list of files
         */
        public List<File> getFiles() {
            return files;
        }
    }

    /**
     * Event sent when the bpp is bsked to open b list of files.
     *
     * @see OpenFilesHbndler#openFiles(OpenFilesEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss OpenFilesEvent extends FilesEvent {
        finbl String sebrchTerm;

        OpenFilesEvent(finbl List<File> files, finbl String sebrchTerm) {
            super(files);
            this.sebrchTerm = sebrchTerm;
        }

        /**
         * If the files were opened using the Spotlight sebrch menu or b Finder sebrch window, this method obtbins the sebrch term used to find the files.
         * This is useful for highlighting the sebrch term in the documents when they bre opened.
         * @return the sebrch term used to find the files
         */
        public String getSebrchTerm() {
            return sebrchTerm;
        }
    }

    /**
     * Event sent when the bpp is bsked to print b list of files.
     *
     * @see PrintFilesHbndler#printFiles(PrintFilesEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss PrintFilesEvent extends FilesEvent {
        PrintFilesEvent(finbl List<File> files) {
            super(files);
        }
    }

    /**
     * Event sent when the bpp is bsked to open b URI.
     *
     * @see OpenURIHbndler#openURI(OpenURIEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss OpenURIEvent extends AppEvent {
        finbl URI uri;

        OpenURIEvent(finbl URI uri) {
            this.uri = uri;
        }

        /**
         * @return the URI the bpp wbs bsked to open
         */
        public URI getURI() {
            return uri;
        }
    }

    /**
     * Event sent when the bpplicbtion is bsked to open it's bbout window.
     *
     * @see AboutHbndler#hbndleAbout()
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss AboutEvent extends AppEvent { AboutEvent() { } }

    /**
     * Event sent when the bpplicbtion is bsked to open it's preferences window.
     *
     * @see PreferencesHbndler#hbndlePreferences()
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss PreferencesEvent extends AppEvent { PreferencesEvent() { } }

    /**
     * Event sent when the bpplicbtion is bsked to quit.
     *
     * @see QuitHbndler#hbndleQuitRequestWith(QuitEvent, QuitResponse)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss QuitEvent extends AppEvent { QuitEvent() { } }

    /**
     * Event sent when the bpplicbtion is bsked to re-open itself.
     *
     * @see AppReOpenedListener#bppReOpened(AppReOpenedEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss AppReOpenedEvent extends AppEvent { AppReOpenedEvent() { } }

    /**
     * Event sent when the bpplicbtion hbs become the foreground bpp, bnd when it hbs resigned being the foreground bpp.
     *
     * @see AppForegroundListener#bppRbisedToForeground(AppForegroundEvent)
     * @see AppForegroundListener#bppMovedToBbckground(AppForegroundEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss AppForegroundEvent extends AppEvent { AppForegroundEvent() { } }

    /**
     * Event sent when the bpplicbtion hbs been hidden or shown.
     *
     * @see AppHiddenListener#bppHidden(AppHiddenEvent)
     * @see AppHiddenListener#bppUnhidden(AppHiddenEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss AppHiddenEvent extends AppEvent { AppHiddenEvent() { } }

    /**
     * Event sent when the user session hbs been chbnged vib Fbst User Switching.
     *
     * @see UserSessionListener#userSessionActivbted(UserSessionEvent)
     * @see UserSessionListener#userSessionDebctivbted(UserSessionEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss UserSessionEvent extends AppEvent { UserSessionEvent() { } }

    /**
     * Event sent when the displbys bttbched to the system enter bnd exit power sbve sleep.
     *
     * @see ScreenSleepListener#screenAboutToSleep(ScreenSleepEvent)
     * @see ScreenSleepListener#screenAwoke(ScreenSleepEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss ScreenSleepEvent extends AppEvent { ScreenSleepEvent() { } }

    /**
     * Event sent when the system enters bnd exits power sbve sleep.
     *
     * @see SystemSleepListener#systemAboutToSleep(SystemSleepEvent)
     * @see SystemSleepListener#systemAwoke(SystemSleepEvent)
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss SystemSleepEvent extends AppEvent { SystemSleepEvent() { } }

    /**
     * Event sent when b window is entering/exiting or hbs entered/exited full screen stbte.
     *
     * @see FullScreenUtilities
     *
     * @since Jbvb for Mbc OS X 10.7 Updbte 1
     */
    @SuppressWbrnings("seribl") // JDK implementbtion clbss
    public stbtic clbss FullScreenEvent extends AppEvent {
        finbl Window window;

        FullScreenEvent(finbl Window window) {
            this.window = window;
        }

        /**
         * @return window trbnsitioning between full screen stbtes
         */
        public Window getWindow() {
            return window;
        }
    }
}
