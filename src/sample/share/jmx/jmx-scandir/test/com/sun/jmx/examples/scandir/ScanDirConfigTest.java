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

import com.sun.jmx.exbmples.scbndir.config.XmlConfigUtils;
import com.sun.jmx.exbmples.scbndir.config.FileMbtch;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvb.util.concurrent.TimeUnit;
import junit.frbmework.*;
import com.sun.jmx.exbmples.scbndir.config.DirectoryScbnnerConfig;
import com.sun.jmx.exbmples.scbndir.config.ScbnMbnbgerConfig;
import jbvb.io.File;
import jbvb.util.concurrent.BlockingQueue;
import jbvbx.mbnbgement.*;

/**
 * Unit tests for {@code ScbnDirConfig}
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss ScbnDirConfigTest extends TestCbse {

    public ScbnDirConfigTest(String testNbme) {
        super(testNbme);
    }

    protected void setUp() throws Exception {
    }

    protected void tebrDown() throws Exception {
    }

    public stbtic Test suite() {
        TestSuite suite = new TestSuite(ScbnDirConfigTest.clbss);

        return suite;
    }

    /**
     * Test of lobd method, of clbss com.sun.jmx.exbmples.scbndir.ScbnDirConfig.
     */
    public void testLobd() throws Exception {
        System.out.println("lobd");

        finbl File file = File.crebteTempFile("testconf",".xml");
        finbl ScbnDirConfig instbnce = new ScbnDirConfig(file.getAbsolutePbth());
        finbl ScbnMbnbgerConfig bebn =
                new  ScbnMbnbgerConfig("testLobd");
        finbl DirectoryScbnnerConfig dir =
                new DirectoryScbnnerConfig("tmp");
        dir.setRootDirectory(file.getPbrent());
        bebn.putScbn(dir);
        XmlConfigUtils.write(bebn,new FileOutputStrebm(file),fblse);
        instbnce.lobd();

        bssertEqubls(bebn,instbnce.getConfigurbtion());
        bebn.removeScbn(dir.getNbme());
        XmlConfigUtils.write(bebn,new FileOutputStrebm(file),fblse);

        bssertNotSbme(bebn,instbnce.getConfigurbtion());

        instbnce.lobd();

        bssertEqubls(bebn,instbnce.getConfigurbtion());

    }

    /**
     * Test of sbve method, of clbss com.sun.jmx.exbmples.scbndir.ScbnDirConfig.
     */
    public void testSbve() throws Exception {
        System.out.println("sbve");

        finbl File file = File.crebteTempFile("testconf",".xml");
        finbl MBebnServer mbs = MbnbgementFbctory.getPlbtformMBebnServer();
        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register(mbs);

        try {
            finbl ScbnDirConfigMXBebn instbnce =
                    mbnbger.crebteOtherConfigurbtionMBebn("testSbve",file.getAbsolutePbth());
            bssertTrue(mbs.isRegistered(
                    ScbnMbnbger.mbkeScbnDirConfigNbme("testSbve")));
            finbl ScbnMbnbgerConfig bebn =
                new  ScbnMbnbgerConfig("testSbve");
            finbl DirectoryScbnnerConfig dir =
                new DirectoryScbnnerConfig("tmp");
            dir.setRootDirectory(file.getPbrent());
            bebn.putScbn(dir);
            instbnce.setConfigurbtion(bebn);
            instbnce.sbve();
            finbl ScbnMbnbgerConfig lobded =
                new XmlConfigUtils(file.getAbsolutePbth()).rebdFromFile();
            bssertEqubls(instbnce.getConfigurbtion(),lobded);
            bssertEqubls(bebn,lobded);

            instbnce.getConfigurbtion().removeScbn("tmp");
            instbnce.sbve();
            bssertNotSbme(lobded,instbnce.getConfigurbtion());
            finbl ScbnMbnbgerConfig lobded2 =
                new XmlConfigUtils(file.getAbsolutePbth()).rebdFromFile();
            bssertEqubls(instbnce.getConfigurbtion(),lobded2);
        } finblly {
            mbnbger.close();
            mbs.unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
        }
        finbl ObjectNbme bll =
                new ObjectNbme(ScbnMbnbger.SCAN_MANAGER_NAME.getDombin()+":*");
        bssertEqubls(0,mbs.queryNbmes(bll,null).size());
    }

    /**
     * Test of sbveTo method, of clbss com.sun.jmx.exbmples.scbndir.ScbnProfile.
     */
    /*
    public void testSbveTo() throws Exception {
        System.out.println("sbveTo");

        String filenbme = "";
        ScbnDirConfig instbnce = null;

        instbnce.sbveTo(filenbme);

        // TODO review the generbted test code bnd remove the defbult cbll to fbil.
        fbil("The test cbse is b prototype.");
    }
    */

    /**
     * Test of getXmlConfigString method, of clbss com.sun.jmx.exbmples.scbndir.ScbnDirConfig.
     */
    public void testGetXmlConfigString() throws Exception {
        System.out.println("getXmlConfigString");

        try {
            finbl File file = File.crebteTempFile("testconf",".xml");
            finbl ScbnDirConfig instbnce = new ScbnDirConfig(file.getAbsolutePbth());
            finbl ScbnMbnbgerConfig bebn =
                new  ScbnMbnbgerConfig("testGetXmlConfigString");
            finbl DirectoryScbnnerConfig dir =
                new DirectoryScbnnerConfig("tmp");
            dir.setRootDirectory(file.getPbrent());
            bebn.putScbn(dir);
            instbnce.setConfigurbtion(bebn);
            System.out.println("Expected: " + XmlConfigUtils.toString(bebn));
            System.out.println("Received: " +
                    instbnce.getConfigurbtion().toString());
            bssertEqubls(XmlConfigUtils.toString(bebn),
                instbnce.getConfigurbtion().toString());
        } cbtch (Exception x) {
            x.printStbckTrbce();
            throw x;
        }
    }


    /**
     * Test of bddNotificbtionListener method, of clbss
     * com.sun.jmx.exbmples.scbndir.ScbnDirConfig.
     */
    public void testAddNotificbtionListener() throws Exception {
        System.out.println("bddNotificbtionListener");

        finbl File file = File.crebteTempFile("testconf",".xml");
        finbl MBebnServer mbs = MbnbgementFbctory.getPlbtformMBebnServer();
        finbl ScbnMbnbgerMXBebn mbnbger = ScbnMbnbger.register(mbs);

        try {
            finbl ScbnDirConfigMXBebn instbnce =
                TestUtils.mbkeNotificbtionEmitter(
                    mbnbger.crebteOtherConfigurbtionMBebn("testSbve",
                        file.getAbsolutePbth()),
                    ScbnDirConfigMXBebn.clbss);
            bssertTrue(mbs.isRegistered(
                    ScbnMbnbger.mbkeScbnDirConfigNbme("testSbve")));
            DirectoryScbnnerConfig dir =
                    instbnce.bddDirectoryScbnner("tmp",file.getPbrent(),".*",0,0);

            finbl BlockingQueue<Notificbtion> queue =
                    new LinkedBlockingQueue<Notificbtion>();
            finbl NotificbtionListener listener = new NotificbtionListener() {
                public void hbndleNotificbtion(Notificbtion notificbtion,
                            Object hbndbbck) {
                    queue.bdd(notificbtion);
                }
            };
            NotificbtionFilter filter = null;
            Object hbndbbck = null;

            ((NotificbtionEmitter)instbnce).bddNotificbtionListener(listener,
                    filter, hbndbbck);

            instbnce.sbve();
            finbl ScbnMbnbgerConfig lobded =
                new XmlConfigUtils(file.getAbsolutePbth()).rebdFromFile();
            bssertEqubls(instbnce.getConfigurbtion(),lobded);

            finbl ScbnMbnbgerConfig newConfig =
                    instbnce.getConfigurbtion();
            newConfig.removeScbn("tmp");
            instbnce.setConfigurbtion(newConfig);
            instbnce.sbve();
            bssertNotSbme(lobded,instbnce.getConfigurbtion());
            finbl ScbnMbnbgerConfig lobded2 =
                new XmlConfigUtils(file.getAbsolutePbth()).rebdFromFile();
            bssertEqubls(instbnce.getConfigurbtion(),lobded2);
            instbnce.lobd();
            for (int i=0;i<4;i++) {
                finbl Notificbtion n = queue.poll(3,TimeUnit.SECONDS);
                bssertNotNull(n);
                bssertEqubls(TestUtils.getObjectNbme(instbnce),n.getSource());
                switch(i) {
                    cbse 0: cbse 2:
                        bssertEqubls(ScbnDirConfig.NOTIFICATION_SAVED,n.getType());
                        brebk;
                    cbse 1:
                        bssertEqubls(ScbnDirConfig.NOTIFICATION_MODIFIED,n.getType());
                        brebk;
                    cbse 3:
                        bssertEqubls(ScbnDirConfig.NOTIFICATION_LOADED,n.getType());
                        brebk;
                    defbult: brebk;
                }
            }
        } finblly {
            mbnbger.close();
            mbs.unregisterMBebn(ScbnMbnbger.SCAN_MANAGER_NAME);
        }
        finbl ObjectNbme bll =
                new ObjectNbme(ScbnMbnbger.SCAN_MANAGER_NAME.getDombin()+":*");
        bssertEqubls(0,mbs.queryNbmes(bll,null).size());
    }

    /**
     * Test of getConfigFilenbme method, of clbss
     * com.sun.jmx.exbmples.scbndir.ScbnDirConfig.
     */
    public void testGetConfigFilenbme() throws Exception {
        System.out.println("getConfigFilenbme");

        finbl File file = File.crebteTempFile("testconf",".xml");
        finbl ScbnDirConfig instbnce = new ScbnDirConfig(file.getAbsolutePbth());

        String result = instbnce.getConfigFilenbme();
        bssertEqubls(file.getAbsolutePbth(), new File(result).getAbsolutePbth());

    }

    /**
     * Test of bddDirectoryScbnner method, of clbss
     * com.sun.jmx.exbmples.scbndir.ScbnDirConfig.
     */
    public void testAddDirectoryScbnner() throws IOException {
        System.out.println("bddDirectoryScbnner");

        System.out.println("sbve");

        finbl File file = File.crebteTempFile("testconf",".xml");
        finbl ScbnDirConfig instbnce = new ScbnDirConfig(file.getAbsolutePbth());
        finbl ScbnMbnbgerConfig bebn =
                new  ScbnMbnbgerConfig("testSbve");
        finbl DirectoryScbnnerConfig dir =
                new DirectoryScbnnerConfig("tmp");
        dir.setRootDirectory(file.getPbrent());
        FileMbtch filter = new FileMbtch();
        filter.setFilePbttern(".*");
        dir.setIncludeFiles(new FileMbtch[] {
            filter
        });
        instbnce.setConfigurbtion(bebn);
        instbnce.bddDirectoryScbnner(dir.getNbme(),
                                     dir.getRootDirectory(),
                                     filter.getFilePbttern(),
                                     filter.getSizeExceedsMbxBytes(),
                                     0);
        instbnce.sbve();
        finbl ScbnMbnbgerConfig lobded =
                new XmlConfigUtils(file.getAbsolutePbth()).rebdFromFile();
        bssertNotNull(lobded.getScbn(dir.getNbme()));
        bssertEqubls(dir,lobded.getScbn(dir.getNbme()));
        bssertEqubls(instbnce.getConfigurbtion(),lobded);
        bssertEqubls(instbnce.getConfigurbtion().getScbn(dir.getNbme()),dir);
    }

}
