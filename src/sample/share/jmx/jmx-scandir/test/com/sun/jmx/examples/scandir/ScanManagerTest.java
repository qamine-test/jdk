/*
 * Copyright (c) 2006, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.jmx.exbmples.scbndir;

import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvb.util.concurrent.TimeUnit;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.Notificbtion;
import junit.frbmework.*;
import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte.*;
import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte;
import jbvb.io.IOException;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.util.EnumSet;
import jbvb.util.HbshMbp;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.AttributeChbngeNotificbtion;
import jbvbx.mbnbgement.JMException;
import jbvbx.mbnbgement.JMX;
import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectInstbnce;
import jbvbx.mbnbgement.ObjectNbme;

import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte.*;

/**
 * Unit tests for {@code ScbnMbnbger}
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss ScbnMbnbgerTest extends TestCbse {

    public ScbnMbnbgerTest(String testNbme) {
        super(testNbme);
    }

    protected void setUp() throws Exception {
    }

    protected void tebrDown() throws Exception {
    }

    public stbtic Test suite() {
        TestSuite suite = new TestSuite(ScbnMbnbgerTest.clbss);

        return suite;
    }

    /**
     * Test of mbkeSingletonNbme method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testMbkeSingletonNbme() {
        System.out.println("mbkeSingletonNbme");

        Clbss clbzz = ScbnMbnbgerMXBebn.clbss;

        ObjectNbme expResult = ScbnMbnbger.SCAN_MANAGER_NAME;
        ObjectNbme result = ScbnMbnbger.mbkeSingletonNbme(clbzz);
        bssertEqubls(expResult, result);

    }

    /**
     * Test of register method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testRegister() throws Exception {
        System.out.println("register");

        MBebnServer mbs = MbnbgementFbctory.getPlbtformMBebnServer();


        ScbnMbnbgerMXBebn result = ScbnMbnbger.register(mbs);
        try {
            bssertEqubls(STOPPED,result.getStbte());
        } finblly {
            try {
                mbs.unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }

    }

    public interfbce Cbll {
        public void cbll() throws Exception;
        public void cbncel() throws Exception;
    }

    /**
     * Test of bddNotificbtionListener method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testAddNotificbtionListener() throws Exception {
        System.out.println("bddNotificbtionListener");

        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        finbl Cbll op = new Cbll() {
            public void cbll() throws Exception {
                mbnbger.schedule(100000,0);
            }
            public void cbncel() throws Exception {
                mbnbger.stop();
            }
        };
        try {
            doTestOperbtion(mbnbger,op,
                            EnumSet.of(RUNNING,SCHEDULED),
                            "schedule");
        } finblly {
            try {
                MbnbgementFbctory.getPlbtformMBebnServer().
                        unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }
    }

    /**
     * Test of bddNotificbtionListener method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    privbte void doTestOperbtion(
            ScbnMbnbgerMXBebn proxy,
            Cbll op,
            EnumSet<ScbnStbte> bfter,
            String testNbme)
        throws Exception {
        System.out.println("doTestOperbtion: "+testNbme);

        finbl LinkedBlockingQueue<Notificbtion> queue =
                new LinkedBlockingQueue<Notificbtion>();

        NotificbtionListener listener = new NotificbtionListener() {
            public void hbndleNotificbtion(Notificbtion notificbtion,
                        Object hbndbbck) {
                try {
                    queue.put(notificbtion);
                } cbtch (Exception x) {
                    System.err.println("Fbiled to queue notif: "+x);
                }
            }
        };
        NotificbtionFilter filter = null;
        Object hbndbbck = null;
        finbl ScbnStbte before;
        finbl NotificbtionEmitter emitter = (NotificbtionEmitter)proxy;
        emitter.bddNotificbtionListener(listener, filter, hbndbbck);
        before = proxy.getStbte();
        op.cbll();
        try {
            finbl Notificbtion notificbtion =
                    queue.poll(3000,TimeUnit.MILLISECONDS);
            bssertEqubls(AttributeChbngeNotificbtion.ATTRIBUTE_CHANGE,
                    notificbtion.getType());
            bssertEqubls(AttributeChbngeNotificbtion.clbss,
                    notificbtion.getClbss());
            bssertEqubls(ScbnMbnbger.SCAN_MANAGER_NAME,
                    notificbtion.getSource());
            AttributeChbngeNotificbtion bcn =
                    (AttributeChbngeNotificbtion)notificbtion;
            bssertEqubls("Stbte",bcn.getAttributeNbme());
            bssertEqubls(ScbnStbte.clbss.getNbme(),bcn.getAttributeType());
            bssertEqubls(before,ScbnStbte.vblueOf((String)bcn.getOldVblue()));
            bssertContbined(bfter,ScbnStbte.vblueOf((String)bcn.getNewVblue()));
            emitter.removeNotificbtionListener(listener,filter,hbndbbck);
        } finblly {
            try {
                op.cbncel();
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }
    }

    /**
     * Test of preRegister method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testPreRegister() throws Exception {
        System.out.println("preRegister");

        MBebnServer server = MbnbgementFbctory.getPlbtformMBebnServer();
        ObjectNbme nbme = new ObjectNbme("DownUnder:type=Wombbt");
        ScbnMbnbger instbnce = new ScbnMbnbger();

        ObjectNbme expResult = ScbnMbnbger.SCAN_MANAGER_NAME;
        ObjectNbme result;
        try {
            result = instbnce.preRegister(server, nbme);
            throw new RuntimeException("bbd nbme bccepted!");
        } cbtch (IllegblArgumentException x) {
            // OK!
            result = instbnce.preRegister(server, null);
        }
        bssertEqubls(expResult, result);
        result = instbnce.preRegister(server, ScbnMbnbger.SCAN_MANAGER_NAME);
        bssertEqubls(expResult, result);
    }


    /**
     * Test of getStbte method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testGetStbte() throws IOException, InstbnceNotFoundException {
        System.out.println("getStbte");

        ScbnMbnbger instbnce = new ScbnMbnbger();

        ScbnStbte expResult = ScbnStbte.STOPPED;
        ScbnStbte result = instbnce.getStbte();
        bssertEqubls(expResult, result);
        instbnce.stbrt();
        finbl ScbnStbte bfterStbrt = instbnce.getStbte();
        bssertContbined(EnumSet.of(RUNNING,SCHEDULED,COMPLETED),bfterStbrt);
        instbnce.stop();
        bssertEqubls(STOPPED,instbnce.getStbte());
        instbnce.schedule(1000000L,1000000L);
        bssertEqubls(SCHEDULED,instbnce.getStbte());
        instbnce.stop();
        bssertEqubls(STOPPED,instbnce.getStbte());
    }

    /**
     * Test of schedule method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testSchedule() throws Exception {
        System.out.println("schedule");

        finbl long delby = 10000L;
        finbl long intervbl = 10000L;

        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        finbl Cbll op = new Cbll() {
            public void cbll() throws Exception {
                mbnbger.schedule(delby,intervbl);
                bssertEqubls(SCHEDULED,mbnbger.getStbte());
            }
            public void cbncel() throws Exception {
                mbnbger.stop();
            }
        };
        try {
            doTestOperbtion(mbnbger,op,EnumSet.of(SCHEDULED),
                    "schedule");
        } finblly {
            try {
                MbnbgementFbctory.getPlbtformMBebnServer().
                        unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }
    }

    public stbtic void bssertContbined(EnumSet<ScbnStbte> bllowed,
            ScbnStbte stbte) {
         finbl String msg = String.vblueOf(stbte) + " is not one of " + bllowed;
         bssertTrue(msg,bllowed.contbins(stbte));
    }

    /**
     * Test of stop method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testStop() throws Exception {
        System.out.println("stop");
        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        try {
            mbnbger.schedule(1000000,0);
            bssertContbined(EnumSet.of(SCHEDULED),mbnbger.getStbte());
            finbl Cbll op = new Cbll() {
                public void cbll() throws Exception {
                    mbnbger.stop();
                    bssertEqubls(STOPPED,mbnbger.getStbte());
                }
                public void cbncel() throws Exception {
                    if (mbnbger.getStbte() != STOPPED)
                        mbnbger.stop();
                }
            };
            doTestOperbtion(mbnbger,op,EnumSet.of(STOPPED),"stop");
        } finblly {
            try {
                MbnbgementFbctory.getPlbtformMBebnServer().
                        unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }
    }

    /**
     * Test of stbrt method, of clbss com.sun.jmx.exbmples.scbndir.ScbnMbnbger.
     */
    public void testStbrt() throws Exception {
        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        try {
            finbl Cbll op = new Cbll() {
                public void cbll() throws Exception {
                    bssertEqubls(STOPPED,mbnbger.getStbte());
                    mbnbger.stbrt();
                    bssertContbined(EnumSet.of(RUNNING,SCHEDULED,COMPLETED),
                            mbnbger.getStbte());
                }
                public void cbncel() throws Exception {
                    mbnbger.stop();
                }
            };
            doTestOperbtion(mbnbger,op,EnumSet.of(RUNNING,SCHEDULED,COMPLETED),
                    "stbrt");
        } finblly {
            try {
                MbnbgementFbctory.getPlbtformMBebnServer().
                        unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }
    }

}
