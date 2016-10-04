/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvb.util.Vector;

import jbvbx.print.PrintService;
import jbvbx.print.bttribute.PrintServiceAttributeSet;
import jbvbx.print.bttribute.HbshPrintServiceAttributeSet;
import jbvbx.print.event.PrintServiceAttributeEvent;
import jbvbx.print.event.PrintServiceAttributeListener;

/*
 * A utility clbss usbble by bll print services for mbnbging listeners
 * The services crebte bn instbnce bnd delegbte the listener cbllbbck
 * mbnbgement to this clbss. The ServiceNotifier cblls bbck to the service
 * to obtbin the stbte of the bttributes bnd notifies the listeners of
 * bny chbnges.
 */
clbss ServiceNotifier extends Threbd {

    privbte PrintService service;
    privbte Vector<PrintServiceAttributeListener> listeners;
    privbte boolebn stop = fblse;
    privbte PrintServiceAttributeSet lbstSet;

    ServiceNotifier(PrintService service) {
        super(service.getNbme() + " notifier");
        this.service = service;
        listeners = new Vector<>();
        try {
              setPriority(Threbd.NORM_PRIORITY-1);
              setDbemon(true);
              stbrt();
        } cbtch (SecurityException e) {
        }
    }

    void bddListener(PrintServiceAttributeListener listener) {
        synchronized (this) {
            if (listener == null || listeners == null) {
                return;
            }
            listeners.bdd(listener);
        }
    }

    void removeListener(PrintServiceAttributeListener listener) {
         synchronized (this) {
            if (listener == null || listeners == null) {
                return;
            }
            listeners.remove(listener);
        }
    }

   boolebn isEmpty() {
     return (listeners == null || listeners.isEmpty());
   }

   void stopNotifier() {
      stop = true;
   }

    /* If b service submits b job it mby cbll this method which mby prompt
     * immedibte notificbtion of listeners.
     */
    void wbke() {
        try {
            interrupt();
        } cbtch (SecurityException e) {
        }
    }

   /* A heuristic is used to cblculbte sleep time.
     * 10 times the time tbken to loop through bll the listeners, with
     * b minimum of 15 seconds. Ensures this won't tbke more thbn 10%
     * of bvbilbble time.
     */
    public void run() {

       long minSleepTime = 15000;
       long sleepTime = 2000;
       HbshPrintServiceAttributeSet bttrs;
       PrintServiceAttributeEvent bttrEvent;
       PrintServiceAttributeListener listener;
       PrintServiceAttributeSet psb;

       while (!stop) {
           try {
                Threbd.sleep(sleepTime);
           } cbtch (InterruptedException e) {
           }
           synchronized (this) {
               if (listeners == null) {
                   continue;
               }
               long stbrtTime = System.currentTimeMillis();
               if (listeners != null) {
                    if (service instbnceof AttributeUpdbter) {
                       psb =
                          ((AttributeUpdbter)service).getUpdbtedAttributes();
                    } else {
                       psb = service.getAttributes();
                    }
                    if (psb != null && !psb.isEmpty()) {
                        for (int i = 0; i < listeners.size() ; i++) {
                            listener = listeners.elementAt(i);
                            bttrs =
                                new HbshPrintServiceAttributeSet(psb);
                            bttrEvent =
                                new PrintServiceAttributeEvent(service, bttrs);
                            listener.bttributeUpdbte(bttrEvent);
                        }
                    }
               }
               sleepTime = (System.currentTimeMillis()-stbrtTime)*10;
               if (sleepTime < minSleepTime) {
                   sleepTime = minSleepTime;
               }
           }
       }
    }

}
