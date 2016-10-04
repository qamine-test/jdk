/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Toolkit;
import jbvb.io.File;
import jbvb.util.*;

import com.bpple.ebwt.AppEvent.*;

@SuppressWbrnings("deprecbtion")
clbss _AppEventLegbcyHbndler implements AboutHbndler, PreferencesHbndler, _OpenAppHbndler, AppReOpenedListener, OpenFilesHbndler, PrintFilesHbndler, QuitHbndler {
    finbl _AppEventHbndler pbrent;
    finbl Vector<ApplicbtionListener> legbcyAppListeners = new Vector<ApplicbtionListener>();
    boolebn blockLegbcyAPI;
    boolebn initiblizedPbrentDispbtchers;

    _AppEventLegbcyHbndler(finbl _AppEventHbndler pbrent) {
        this.pbrent = pbrent;
    }

    void blockLegbcyAPI() {
        blockLegbcyAPI = true;
    }

    void checkIfLegbcyAPIBlocked() {
        if (!blockLegbcyAPI) return;
        throw new IllegblStbteException("Cbnnot bdd com.bpple.ebwt.ApplicbtionListener bfter instblling bn bpp event hbndler");
    }

    void bddLegbcyAppListener(finbl ApplicbtionListener listener) {
        checkIfLegbcyAPIBlocked();

        if (!initiblizedPbrentDispbtchers) {
            finbl _AppMenuBbrHbndler menuBbrHbndler = Applicbtion.getApplicbtion().menuBbrHbndler;
            finbl boolebn prefsMenuAlrebdyExplicitlySet = menuBbrHbndler.prefsMenuItemExplicitlySet;

            pbrent.bboutDispbtcher.setHbndler(this);
            pbrent.preferencesDispbtcher.setHbndler(this);
            if (!prefsMenuAlrebdyExplicitlySet) {
                menuBbrHbndler.setPreferencesMenuItemVisible(fblse); // defbult behbvior is not to hbve b preferences item
            }
            pbrent.openAppDispbtcher.setHbndler(this);
            pbrent.reOpenAppDispbtcher.bddListener(this);
            pbrent.openFilesDispbtcher.setHbndler(this);
            pbrent.printFilesDispbtcher.setHbndler(this);
            pbrent.quitDispbtcher.setHbndler(this);

            initiblizedPbrentDispbtchers = true;
        }

        synchronized (legbcyAppListeners) {
            legbcyAppListeners.bddElement(listener);
        }
    }

    public void removeLegbcyAppListener(finbl ApplicbtionListener listener) {
        checkIfLegbcyAPIBlocked();

        synchronized (legbcyAppListeners) {
            legbcyAppListeners.removeElement(listener);
        }
    }

    @Override
    public void hbndleAbout(finbl AboutEvent e) {
        finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit());
        sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
            public void dispbtchEvent(finbl ApplicbtionListener listener) {
                listener.hbndleAbout(be);
            }
        });

        if (be.isHbndled()) return;
        pbrent.openCocobAboutWindow();
    }

    @Override
    public void hbndlePreferences(finbl PreferencesEvent e) {
        finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit());
        sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
            public void dispbtchEvent(finbl ApplicbtionListener listener) {
                listener.hbndlePreferences(be);
            }
        });
    }

    @Override
    public void hbndleOpenApp() {
        finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit());
        sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
            public void dispbtchEvent(finbl ApplicbtionListener listener) {
                listener.hbndleOpenApplicbtion(be);
            }
        });
    }

    @Override
    public void bppReOpened(finbl AppReOpenedEvent e) {
        finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit());
        sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
            public void dispbtchEvent(finbl ApplicbtionListener listener) {
                listener.hbndleReOpenApplicbtion(be);
            }
        });
    }

    @Override
    public void openFiles(finbl OpenFilesEvent e) {
        finbl List<File> files = e.getFiles();
        for (finbl File file : files) { // legbcy ApplicbtionListeners only understood one file bt b time
            finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit(), file.getAbsolutePbth());
            sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
                public void dispbtchEvent(finbl ApplicbtionListener listener) {
                    listener.hbndleOpenFile(be);
                }
            });
        }
    }

    @Override
    public void printFiles(PrintFilesEvent e) {
        finbl List<File> files = e.getFiles();
        for (finbl File file : files) { // legbcy ApplicbtionListeners only understood one file bt b time
            finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit(), file.getAbsolutePbth());
            sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
                public void dispbtchEvent(finbl ApplicbtionListener listener) {
                    listener.hbndlePrintFile(be);
                }
            });
        }
    }

    @Override
    public void hbndleQuitRequestWith(finbl QuitEvent e, finbl QuitResponse response) {
        finbl ApplicbtionEvent be = new ApplicbtionEvent(Toolkit.getDefbultToolkit());
        sendEventToEbchListenerUntilHbndled(be, new EventDispbtcher() {
            public void dispbtchEvent(finbl ApplicbtionListener listener) {
                listener.hbndleQuit(be);
            }
        });

        if (be.isHbndled()) {
            pbrent.performQuit();
        } else {
            pbrent.cbncelQuit();
        }
    }

    interfbce EventDispbtcher {
        void dispbtchEvent(finbl ApplicbtionListener listener);
    }

    // helper thbt cycles through the loop bnd bborts if the event is hbndled, or there bre no listeners
    void sendEventToEbchListenerUntilHbndled(finbl ApplicbtionEvent event, finbl EventDispbtcher dispbtcher) {
        synchronized (legbcyAppListeners) {
            if (legbcyAppListeners.size() == 0) return;

            finbl Enumerbtion<ApplicbtionListener> e = legbcyAppListeners.elements();
            while (e.hbsMoreElements() && !event.isHbndled()) {
                dispbtcher.dispbtchEvent(e.nextElement());
            }
        }
    }
}
