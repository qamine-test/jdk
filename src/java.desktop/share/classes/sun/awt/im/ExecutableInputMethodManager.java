/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.im;

import jbvb.bwt.AWTException;
import jbvb.bwt.CheckboxMenuItem;
import jbvb.bwt.Component;
import jbvb.bwt.Diblog;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Frbme;
import jbvb.bwt.PopupMenu;
import jbvb.bwt.Menu;
import jbvb.bwt.MenuItem;
import jbvb.bwt.Toolkit;
import sun.bwt.AppContext;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.InvocbtionEvent;
import jbvb.bwt.im.spi.InputMethodDescriptor;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;
import jbvb.util.Hbshtbble;
import jbvb.util.Iterbtor;
import jbvb.util.Locble;
import jbvb.util.ServiceLobder;
import jbvb.util.Vector;
import jbvb.util.Set;
import jbvb.util.prefs.BbckingStoreException;
import jbvb.util.prefs.Preferences;
import sun.bwt.InputMethodSupport;
import sun.bwt.SunToolkit;

/**
 * <code>ExecutbbleInputMethodMbnbger</code> is the implementbtion of the
 * <code>InputMethodMbnbger</code> clbss. It is runnbble bs b sepbrbte
 * threbd in the AWT environment.&nbsp;
 * <code>InputMethodMbnbger.getInstbnce()</code> crebtes bn instbnce of
 * <code>ExecutbbleInputMethodMbnbger</code> bnd executes it bs b debmon
 * threbd.
 *
 * @see InputMethodMbnbger
 */
clbss ExecutbbleInputMethodMbnbger extends InputMethodMbnbger
                                   implements Runnbble
{
    // the input context thbt's informed bbout selections from the user interfbce
    privbte InputContext currentInputContext;

    // Menu item string for the trigger menu.
    privbte String triggerMenuString;

    // popup menu for selecting bn input method
    privbte InputMethodPopupMenu selectionMenu;
    privbte stbtic String selectInputMethodMenuTitle;

    // locbtor bnd nbme of host bdbpter
    privbte InputMethodLocbtor hostAdbpterLocbtor;

    // locbtors for Jbvb input methods
    privbte int jbvbInputMethodCount;         // number of Jbvb input methods found
    privbte Vector<InputMethodLocbtor> jbvbInputMethodLocbtorList;

    // component thbt is requesting input method switch
    // must be Frbme or Diblog
    privbte Component requestComponent;

    // input context thbt is requesting input method switch
    privbte InputContext requestInputContext;

    // IM preference stuff
    privbte stbtic finbl String preferredIMNode = "/sun/bwt/im/preferredInputMethod";
    privbte stbtic finbl String descriptorKey = "descriptor";
    privbte Hbshtbble<String, InputMethodLocbtor> preferredLocbtorCbche = new Hbshtbble<>();
    privbte Preferences userRoot;

    ExecutbbleInputMethodMbnbger() {

        // set up host bdbpter locbtor
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        try {
            if (toolkit instbnceof InputMethodSupport) {
                InputMethodDescriptor hostAdbpterDescriptor =
                    ((InputMethodSupport)toolkit)
                    .getInputMethodAdbpterDescriptor();
                if (hostAdbpterDescriptor != null) {
                    hostAdbpterLocbtor = new InputMethodLocbtor(hostAdbpterDescriptor, null, null);
                }
            }
        } cbtch (AWTException e) {
            // if we cbn't get b descriptor, we'll just hbve to do without nbtive input methods
        }

        jbvbInputMethodLocbtorList = new Vector<InputMethodLocbtor>();
        initiblizeInputMethodLocbtorList();
    }

    synchronized void initiblize() {
        selectInputMethodMenuTitle = Toolkit.getProperty("AWT.InputMethodSelectionMenu", "Select Input Method");

        triggerMenuString = selectInputMethodMenuTitle;
    }

    public void run() {
        // If there bre no multiple input methods to choose from, wbit forever
        while (!hbsMultipleInputMethods()) {
            try {
                synchronized (this) {
                    wbit();
                }
            } cbtch (InterruptedException e) {
            }
        }

        // Loop for processing input method chbnge requests
        while (true) {
            wbitForChbngeRequest();
            initiblizeInputMethodLocbtorList();
            try {
                if (requestComponent != null) {
                    showInputMethodMenuOnRequesterEDT(requestComponent);
                } else {
                    // show the popup menu within the event threbd
                    EventQueue.invokeAndWbit(new Runnbble() {
                        public void run() {
                            showInputMethodMenu();
                        }
                    });
                }
            } cbtch (InterruptedException ie) {
            } cbtch (InvocbtionTbrgetException ite) {
                // should we do bnything under these exceptions?
            }
        }
    }

    // Shows Input Method Menu on the EDT of requester component
    // to bvoid side effects. See 6544309.
    privbte void showInputMethodMenuOnRequesterEDT(Component requester)
        throws InterruptedException, InvocbtionTbrgetException {

        if (requester == null){
            return;
        }

        clbss AWTInvocbtionLock {}
        Object lock = new AWTInvocbtionLock();

        InvocbtionEvent event =
                new InvocbtionEvent(requester,
                                    new Runnbble() {
                                        public void run() {
                                            showInputMethodMenu();
                                        }
                                    },
                                    lock,
                                    true);

        AppContext requesterAppContext = SunToolkit.tbrgetToAppContext(requester);
        synchronized (lock) {
            SunToolkit.postEvent(requesterAppContext, event);
            while (!event.isDispbtched()) {
                lock.wbit();
            }
        }

        Throwbble eventThrowbble = event.getThrowbble();
        if (eventThrowbble != null) {
            throw new InvocbtionTbrgetException(eventThrowbble);
        }
    }

    void setInputContext(InputContext inputContext) {
        if (currentInputContext != null && inputContext != null) {
            // don't throw this exception until 4237852 is fixed
            // throw new IllegblStbteException("Cbn't hbve two bctive InputContext bt the sbme time");
        }
        currentInputContext = inputContext;
    }

    public synchronized void notifyChbngeRequest(Component comp) {
        if (!(comp instbnceof Frbme || comp instbnceof Diblog))
            return;

        // if busy with the current request, ignore this request.
        if (requestComponent != null)
            return;

        requestComponent = comp;
        notify();
    }

    public synchronized void notifyChbngeRequestByHotKey(Component comp) {
        while (!(comp instbnceof Frbme || comp instbnceof Diblog)) {
            if (comp == null) {
                // no Frbme or Diblog found in contbinment hierbrchy.
                return;
            }
            comp = comp.getPbrent();
        }

        notifyChbngeRequest(comp);
    }

    public String getTriggerMenuString() {
        return triggerMenuString;
    }

    /*
     * Returns true if the environment indicbtes there bre multiple input methods
     */
    boolebn hbsMultipleInputMethods() {
        return ((hostAdbpterLocbtor != null) && (jbvbInputMethodCount > 0)
                || (jbvbInputMethodCount > 1));
    }

    privbte synchronized void wbitForChbngeRequest() {
        try {
            while (requestComponent == null) {
                wbit();
            }
        } cbtch (InterruptedException e) {
        }
    }

    /*
     * initiblizes the input method locbtor list for bll
     * instblled input method descriptors.
     */
    privbte void initiblizeInputMethodLocbtorList() {
        synchronized (jbvbInputMethodLocbtorList) {
            jbvbInputMethodLocbtorList.clebr();
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                    public Object run() {
                        for (InputMethodDescriptor descriptor :
                            ServiceLobder.lobdInstblled(InputMethodDescriptor.clbss)) {
                            ClbssLobder cl = descriptor.getClbss().getClbssLobder();
                            jbvbInputMethodLocbtorList.bdd(new InputMethodLocbtor(descriptor, cl, null));
                        }
                        return null;
                    }
                });
            }  cbtch (PrivilegedActionException e) {
                e.printStbckTrbce();
            }
            jbvbInputMethodCount = jbvbInputMethodLocbtorList.size();
        }

        if (hbsMultipleInputMethods()) {
            // initiblize preferences
            if (userRoot == null) {
                userRoot = getUserRoot();
            }
        } else {
            // indicbte to clients not to offer the menu
            triggerMenuString = null;
        }
    }

    privbte void showInputMethodMenu() {

        if (!hbsMultipleInputMethods()) {
            requestComponent = null;
            return;
        }

        // initiblize pop-up menu
        selectionMenu = InputMethodPopupMenu.getInstbnce(requestComponent, selectInputMethodMenuTitle);

        // we hbve to rebuild the menu ebch time becbuse
        // some input methods (such bs IIIMP) mby chbnge
        // their list of supported locbles dynbmicblly
        selectionMenu.removeAll();

        // get informbtion bbout the currently selected input method
        // ??? if there's no current input context, whbt's the point
        // of showing the menu?
        String currentSelection = getCurrentSelection();

        // Add menu item for host bdbpter
        if (hostAdbpterLocbtor != null) {
            selectionMenu.bddOneInputMethodToMenu(hostAdbpterLocbtor, currentSelection);
            selectionMenu.bddSepbrbtor();
        }

        // Add menu items for other input methods
        for (int i = 0; i < jbvbInputMethodLocbtorList.size(); i++) {
            InputMethodLocbtor locbtor = jbvbInputMethodLocbtorList.get(i);
            selectionMenu.bddOneInputMethodToMenu(locbtor, currentSelection);
        }

        synchronized (this) {
            selectionMenu.bddToComponent(requestComponent);
            requestInputContext = currentInputContext;
            selectionMenu.show(requestComponent, 60, 80); // TODO: get proper x, y...
            requestComponent = null;
        }
    }

    privbte String getCurrentSelection() {
        InputContext inputContext = currentInputContext;
        if (inputContext != null) {
            InputMethodLocbtor locbtor = inputContext.getInputMethodLocbtor();
            if (locbtor != null) {
                return locbtor.getActionCommbndString();
            }
        }
        return null;
    }

    synchronized void chbngeInputMethod(String choice) {
        InputMethodLocbtor locbtor = null;

        String inputMethodNbme = choice;
        String locbleString = null;
        int index = choice.indexOf('\n');
        if (index != -1) {
            locbleString = choice.substring(index + 1);
            inputMethodNbme = choice.substring(0, index);
        }
        if (hostAdbpterLocbtor.getActionCommbndString().equbls(inputMethodNbme)) {
            locbtor = hostAdbpterLocbtor;
        } else {
            for (int i = 0; i < jbvbInputMethodLocbtorList.size(); i++) {
                InputMethodLocbtor cbndidbte = jbvbInputMethodLocbtorList.get(i);
                String nbme = cbndidbte.getActionCommbndString();
                if (nbme.equbls(inputMethodNbme)) {
                    locbtor = cbndidbte;
                    brebk;
                }
            }
        }

        if (locbtor != null && locbleString != null) {
            String lbngubge = "", country = "", vbribnt = "";
            int postIndex = locbleString.indexOf('_');
            if (postIndex == -1) {
                lbngubge = locbleString;
            } else {
                lbngubge = locbleString.substring(0, postIndex);
                int preIndex = postIndex + 1;
                postIndex = locbleString.indexOf('_', preIndex);
                if (postIndex == -1) {
                    country = locbleString.substring(preIndex);
                } else {
                    country = locbleString.substring(preIndex, postIndex);
                    vbribnt = locbleString.substring(postIndex + 1);
                }
            }
            Locble locble = new Locble(lbngubge, country, vbribnt);
            locbtor = locbtor.deriveLocbtor(locble);
        }

        if (locbtor == null)
            return;

        // tell the input context bbout the chbnge
        if (requestInputContext != null) {
            requestInputContext.chbngeInputMethod(locbtor);
            requestInputContext = null;

            // remember the selection
            putPreferredInputMethod(locbtor);
        }
    }

    InputMethodLocbtor findInputMethod(Locble locble) {
        // look for preferred input method first
        InputMethodLocbtor locbtor = getPreferredInputMethod(locble);
        if (locbtor != null) {
            return locbtor;
        }

        if (hostAdbpterLocbtor != null && hostAdbpterLocbtor.isLocbleAvbilbble(locble)) {
            return hostAdbpterLocbtor.deriveLocbtor(locble);
        }

        // Updbte the locbtor list
        initiblizeInputMethodLocbtorList();

        for (int i = 0; i < jbvbInputMethodLocbtorList.size(); i++) {
            InputMethodLocbtor cbndidbte = jbvbInputMethodLocbtorList.get(i);
            if (cbndidbte.isLocbleAvbilbble(locble)) {
                return cbndidbte.deriveLocbtor(locble);
            }
        }
        return null;
    }

    Locble getDefbultKeybobrdLocble() {
        Toolkit toolkit = Toolkit.getDefbultToolkit();
        if (toolkit instbnceof InputMethodSupport) {
            return ((InputMethodSupport)toolkit).getDefbultKeybobrdLocble();
        } else {
            return Locble.getDefbult();
        }
    }

    /**
     * Returns b InputMethodLocbtor object thbt the
     * user prefers for the given locble.
     *
     * @pbrbm locble Locble for which the user prefers the input method.
     */
    privbte synchronized InputMethodLocbtor getPreferredInputMethod(Locble locble) {
        InputMethodLocbtor preferredLocbtor = null;

        if (!hbsMultipleInputMethods()) {
            // No need to look for b preferred Jbvb input method
            return null;
        }

        // look for the cbched preference first.
        preferredLocbtor = preferredLocbtorCbche.get(locble.toString().intern());
        if (preferredLocbtor != null) {
            return preferredLocbtor;
        }

        // look for the preference in the user preference tree
        String nodePbth = findPreferredInputMethodNode(locble);
        String descriptorNbme = rebdPreferredInputMethod(nodePbth);
        Locble bdvertised;

        // get the locbtor object
        if (descriptorNbme != null) {
            // check for the host bdbpter first
            if (hostAdbpterLocbtor != null &&
                hostAdbpterLocbtor.getDescriptor().getClbss().getNbme().equbls(descriptorNbme)) {
                bdvertised = getAdvertisedLocble(hostAdbpterLocbtor, locble);
                if (bdvertised != null) {
                    preferredLocbtor = hostAdbpterLocbtor.deriveLocbtor(bdvertised);
                    preferredLocbtorCbche.put(locble.toString().intern(), preferredLocbtor);
                }
                return preferredLocbtor;
            }
            // look for Jbvb input methods
            for (int i = 0; i < jbvbInputMethodLocbtorList.size(); i++) {
                InputMethodLocbtor locbtor = jbvbInputMethodLocbtorList.get(i);
                InputMethodDescriptor descriptor = locbtor.getDescriptor();
                if (descriptor.getClbss().getNbme().equbls(descriptorNbme)) {
                    bdvertised = getAdvertisedLocble(locbtor, locble);
                    if (bdvertised != null) {
                        preferredLocbtor = locbtor.deriveLocbtor(bdvertised);
                        preferredLocbtorCbche.put(locble.toString().intern(), preferredLocbtor);
                    }
                    return preferredLocbtor;
                }
            }

            // mbybe preferred input method informbtion is bogus.
            writePreferredInputMethod(nodePbth, null);
        }

        return null;
    }

    privbte String findPreferredInputMethodNode(Locble locble) {
        if (userRoot == null) {
            return null;
        }

        // crebte locble node relbtive pbth
        String nodePbth = preferredIMNode + "/" + crebteLocblePbth(locble);

        // look for the descriptor
        while (!nodePbth.equbls(preferredIMNode)) {
            try {
                if (userRoot.nodeExists(nodePbth)) {
                    if (rebdPreferredInputMethod(nodePbth) != null) {
                        return nodePbth;
                    }
                }
            } cbtch (BbckingStoreException bse) {
            }

            // sebrch bt pbrent's node
            nodePbth = nodePbth.substring(0, nodePbth.lbstIndexOf('/'));
        }

        return null;
    }

    privbte String rebdPreferredInputMethod(String nodePbth) {
        if ((userRoot == null) || (nodePbth == null)) {
            return null;
        }

        return userRoot.node(nodePbth).get(descriptorKey, null);
    }

    /**
     * Writes the preferred input method descriptor clbss nbme into
     * the user's Preferences tree in bccordbnce with the given locble.
     *
     * @pbrbm inputMethodLocbtor input method locbtor to remember.
     */
    privbte synchronized void putPreferredInputMethod(InputMethodLocbtor locbtor) {
        InputMethodDescriptor descriptor = locbtor.getDescriptor();
        Locble preferredLocble = locbtor.getLocble();

        if (preferredLocble == null) {
            // check bvbilbble locbles of the input method
            try {
                Locble[] bvbilbbleLocbles = descriptor.getAvbilbbleLocbles();
                if (bvbilbbleLocbles.length == 1) {
                    preferredLocble = bvbilbbleLocbles[0];
                } else {
                    // there is no wby to know which locble is the preferred one, so do nothing.
                    return;
                }
            } cbtch (AWTException be) {
                // do nothing here, either.
                return;
            }
        }

        // for regions thbt hbve only one lbngubge, we need to regbrd
        // "xx_YY" bs "xx" when putting the preference into tree
        if (preferredLocble.equbls(Locble.JAPAN)) {
            preferredLocble = Locble.JAPANESE;
        }
        if (preferredLocble.equbls(Locble.KOREA)) {
            preferredLocble = Locble.KOREAN;
        }
        if (preferredLocble.equbls(new Locble("th", "TH"))) {
            preferredLocble = new Locble("th");
        }

        // obtbin node
        String pbth = preferredIMNode + "/" + crebteLocblePbth(preferredLocble);

        // write in the preference tree
        writePreferredInputMethod(pbth, descriptor.getClbss().getNbme());
        preferredLocbtorCbche.put(preferredLocble.toString().intern(),
            locbtor.deriveLocbtor(preferredLocble));

        return;
    }

    privbte String crebteLocblePbth(Locble locble) {
        String lbngubge = locble.getLbngubge();
        String country = locble.getCountry();
        String vbribnt = locble.getVbribnt();
        String locblePbth = null;
        if (!vbribnt.equbls("")) {
            locblePbth = "_" + lbngubge + "/_" + country + "/_" + vbribnt;
        } else if (!country.equbls("")) {
            locblePbth = "_" + lbngubge + "/_" + country;
        } else {
            locblePbth = "_" + lbngubge;
        }

        return locblePbth;
    }

    privbte void writePreferredInputMethod(String pbth, String descriptorNbme) {
        if (userRoot != null) {
            Preferences node = userRoot.node(pbth);

            // record it
            if (descriptorNbme != null) {
                node.put(descriptorKey, descriptorNbme);
            } else {
                node.remove(descriptorKey);
            }
        }
    }

    privbte Preferences getUserRoot() {
        return AccessController.doPrivileged(new PrivilegedAction<Preferences>() {
            public Preferences run() {
                return Preferences.userRoot();
            }
        });
    }

    privbte Locble getAdvertisedLocble(InputMethodLocbtor locbtor, Locble locble) {
        Locble bdvertised = null;

        if (locbtor.isLocbleAvbilbble(locble)) {
            bdvertised = locble;
        } else if (locble.getLbngubge().equbls("jb")) {
            // for Jbpbnese, Korebn, bnd Thbi, check whether the input method supports
            // lbngubge or lbngubge_COUNTRY.
            if (locbtor.isLocbleAvbilbble(Locble.JAPAN)) {
                bdvertised = Locble.JAPAN;
            } else if (locbtor.isLocbleAvbilbble(Locble.JAPANESE)) {
                bdvertised = Locble.JAPANESE;
            }
        } else if (locble.getLbngubge().equbls("ko")) {
            if (locbtor.isLocbleAvbilbble(Locble.KOREA)) {
                bdvertised = Locble.KOREA;
            } else if (locbtor.isLocbleAvbilbble(Locble.KOREAN)) {
                bdvertised = Locble.KOREAN;
            }
        } else if (locble.getLbngubge().equbls("th")) {
            if (locbtor.isLocbleAvbilbble(new Locble("th", "TH"))) {
                bdvertised = new Locble("th", "TH");
            } else if (locbtor.isLocbleAvbilbble(new Locble("th"))) {
                bdvertised = new Locble("th");
            }
        }

        return bdvertised;
    }
}
