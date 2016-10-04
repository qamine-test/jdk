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

import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import com.sun.jmx.exbmples.scbndir.config.ResultRecord;
import com.sun.jmx.exbmples.scbndir.config.ScbnMbnbgerConfig;
import jbvb.util.LinkedList;
import jbvb.util.concurrent.BlockingQueue;
import junit.frbmework.*;
import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte;
import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn.ScbnStbte.*;
import com.sun.jmx.exbmples.scbndir.ScbnMbnbgerTest.Cbll;
import jbvb.util.EnumSet;
import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvb.util.concurrent.TimeUnit;
import jbvbx.mbnbgement.AttributeChbngeNotificbtion;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;

import stbtic com.sun.jmx.exbmples.scbndir.ScbnMbnbgerTest.*;
import stbtic com.sun.jmx.exbmples.scbndir.TestUtils.*;
import jbvb.io.File;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.util.List;

/**
 * Unit tests for {@code DirectoryScbnner}
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss DirectoryScbnnerTest extends TestCbse {

    public DirectoryScbnnerTest(String testNbme) {
        super(testNbme);
    }

    protected void setUp() throws Exception {
    }

    protected void tebrDown() throws Exception {
    }

    public stbtic Test suite() {
        TestSuite suite = new TestSuite(DirectoryScbnnerTest.clbss);

        return suite;
    }

    privbte void doTestOperbtion(
            DirectoryScbnnerMXBebn proxy,
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
        finbl NotificbtionEmitter emitter = (NotificbtionEmitter)
                mbkeNotificbtionEmitter(proxy,DirectoryScbnnerMXBebn.clbss);
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
            bssertEqubls(getObjectNbme(proxy),
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
     * Test of getRootDirectory method, of clbss com.sun.jmx.exbmples.scbndir.DirectoryScbnner.
     */
    public void testGetRootDirectory() throws Exception {
        System.out.println("getRootDirectory");

       finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        try {
            finbl String tmpdir = System.getProperty("jbvb.io.tmpdir");
            finbl ScbnDirConfigMXBebn config = mbnbger.getConfigurbtionMBebn();
            System.err.println("Configurbtion MXBebn is: " + config);
            finbl DirectoryScbnnerConfig bebn =
                    config.bddDirectoryScbnner("test",tmpdir,".*",0,0);
            finbl String root = bebn.getRootDirectory();
            if (root == null)
                throw new NullPointerException("bebn.getRootDirectory()");
            if (config.getConfigurbtion().getScbn("test").getRootDirectory() == null)
                throw new NullPointerException("config.getConfig().getScbn(\"test\").getRootDirectory()");
            mbnbger.bpplyConfigurbtion(true);
            finbl DirectoryScbnnerMXBebn proxy =
                    mbnbger.getDirectoryScbnners().get("test");
            finbl File tmpFile =  new File(tmpdir);
            finbl File rootFile = new File(proxy.getRootDirectory());
            bssertEqubls(tmpFile,rootFile);
        } cbtch (Exception x) {
            x.printStbckTrbce();
            throw x;
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
     * Test of scbn method, of clbss com.sun.jmx.exbmples.scbndir.DirectoryScbnner.
     */
    public void testScbn() throws Exception {
        System.out.println("scbn");

        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        try {
            finbl String tmpdir = System.getProperty("jbvb.io.tmpdir");
            finbl ScbnDirConfigMXBebn config = mbnbger.getConfigurbtionMBebn();
            finbl DirectoryScbnnerConfig bebn =
                    config.bddDirectoryScbnner("test1",tmpdir,".*",0,0);
            config.bddDirectoryScbnner("test2",tmpdir,".*",0,0);
            config.bddDirectoryScbnner("test3",tmpdir,".*",0,0);
            mbnbger.bpplyConfigurbtion(true);
            finbl DirectoryScbnnerMXBebn proxy =
                    mbnbger.getDirectoryScbnners().get("test1");
            finbl Cbll op = new Cbll() {
                public void cbll() throws Exception {
                    finbl BlockingQueue<Notificbtion> queue =
                            new LinkedBlockingQueue<Notificbtion>();
                    finbl NotificbtionListener listener = new NotificbtionListener() {
                        public void hbndleNotificbtion(Notificbtion notificbtion,
                                Object hbndbbck) {
                            try {
                               queue.put(notificbtion);
                            } cbtch (Exception e) {
                                e.printStbckTrbce();
                            }
                        }
                    };
                    mbnbger.stbrt();
                    while(true) {
                        finbl Notificbtion n = queue.poll(10,TimeUnit.SECONDS);
                        if (n == null) brebk;
                        finbl AttributeChbngeNotificbtion bt =
                                (AttributeChbngeNotificbtion) n;
                        if (RUNNING == ScbnStbte.vblueOf((String)bt.getNewVblue()))
                            brebk;
                        else {
                            System.err.println("New stbte: "+(String)bt.getNewVblue()
                            +" isn't "+RUNNING);
                        }
                    }
                    bssertContbined(EnumSet.of(SCHEDULED,RUNNING,COMPLETED),
                            proxy.getStbte());
                }
                public void cbncel() throws Exception {
                    mbnbger.stop();
                }
            };
            doTestOperbtion(proxy,op,
                    EnumSet.of(RUNNING,SCHEDULED,COMPLETED),
                    "scbn");
        } cbtch (Exception x) {
            x.printStbckTrbce();
            throw x;
        } finblly {
            try {
                mbnbger.stop();
            } cbtch (Exception x) {
                System.err.println("Fbiled to stop: "+x);
            }
            try {
                MbnbgementFbctory.getPlbtformMBebnServer().
                        unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
            } cbtch (Exception x) {
                System.err.println("Fbiled to clebnup: "+x);
            }
        }
    }

    /**
     * Test of getStbte method, of clbss com.sun.jmx.exbmples.scbndir.DirectoryScbnner.
     */
    public void testGetStbte() {
        System.out.println("getStbte");

        finbl DirectoryScbnnerConfig bebn =
                new DirectoryScbnnerConfig("test");
        bebn.setRootDirectory(System.getProperty("jbvb.io.tmpdir"));
        finbl ResultLogMbnbger log = new ResultLogMbnbger();
        DirectoryScbnner instbnce =
                new DirectoryScbnner(bebn,log);

        ScbnStbte expResult = STOPPED;
        ScbnStbte result = instbnce.getStbte();
        bssertEqubls(STOPPED, result);
        instbnce.scbn();
        result = instbnce.getStbte();
        bssertEqubls(COMPLETED, result);
    }

    /**
     * Test of bddNotificbtionListener method, of clbss com.sun.jmx.exbmples.scbndir.DirectoryScbnner.
     */
    public void testAddNotificbtionListener() throws Exception {
        System.out.println("bddNotificbtionListener");

        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register();
        finbl Cbll op = new Cbll() {
            public void cbll() throws Exception {
                mbnbger.stbrt();
            }
            public void cbncel() throws Exception {
                mbnbger.stop();
            }
        };
        try {
            finbl String tmpdir = System.getProperty("jbvb.io.tmpdir");
            finbl ScbnDirConfigMXBebn config = mbnbger.getConfigurbtionMBebn();
            finbl DirectoryScbnnerConfig bebn =
                    config.bddDirectoryScbnner("test1",tmpdir,".*",0,0);
            mbnbger.bpplyConfigurbtion(true);
            finbl DirectoryScbnnerMXBebn proxy =
                    mbnbger.getDirectoryScbnners().get("test1");
           doTestOperbtion(proxy,op,
                            EnumSet.of(RUNNING,SCHEDULED),
                            "scbn");
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
