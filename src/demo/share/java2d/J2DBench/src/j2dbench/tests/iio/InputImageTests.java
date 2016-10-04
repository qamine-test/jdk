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


pbckbge j2dbench.tests.iio;

import jbvb.bwt.Component;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.MedibTrbcker;
import jbvb.bwt.Toolkit;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.net.URL;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.ImbgeRebder;
import jbvbx.imbgeio.event.IIORebdProgressListener;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ImbgeRebderSpi;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;

import j2dbench.Group;
import j2dbench.Modifier;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.Test;
import j2dbench.TestEnvironment;

bbstrbct clbss InputImbgeTests extends InputTests {

    privbte stbtic finbl int TEST_TOOLKIT     = 1;
    privbte stbtic finbl int TEST_IMAGEIO     = 2;
    privbte stbtic finbl int TEST_IMAGEREADER = 3;

    privbte stbtic Group imbgeRoot;

    privbte stbtic Group toolkitRoot;
    privbte stbtic Group toolkitOptRoot;
    privbte stbtic Option toolkitRebdFormbtList;
    privbte stbtic Group toolkitTestRoot;

    privbte stbtic Group imbgeioRoot;
    privbte stbtic Group imbgeioOptRoot;
    privbte stbtic ImbgeRebderSpi[] imbgeioRebderSpis;
    privbte stbtic String[] imbgeioRebdFormbtShortNbmes;
    privbte stbtic Option imbgeioRebdFormbtList;
    privbte stbtic Group imbgeioTestRoot;

    privbte stbtic Group imbgeRebderRoot;
    privbte stbtic Group imbgeRebderOptRoot;
    privbte stbtic Option seekForwbrdOnlyTog;
    privbte stbtic Option ignoreMetbdbtbTog;
    privbte stbtic Option instbllListenerTog;
    privbte stbtic Group imbgeRebderTestRoot;

    public stbtic void init() {
        imbgeRoot = new Group(inputRoot, "imbge", "Imbge Rebding Benchmbrks");
        imbgeRoot.setTbbbed();

        // Toolkit Benchmbrks
        toolkitRoot = new Group(imbgeRoot, "toolkit", "Toolkit");

        toolkitOptRoot = new Group(toolkitRoot, "opts", "Toolkit Options");
        String[] tkFormbts = new String[] {"gif", "jpg", "png"};
        toolkitRebdFormbtList =
            new Option.ObjectList(toolkitOptRoot,
                                  "formbt", "Imbge Formbt",
                                  tkFormbts, tkFormbts,
                                  tkFormbts, tkFormbts,
                                  0x0);

        toolkitTestRoot = new Group(toolkitRoot, "tests", "Toolkit Tests");
        new ToolkitCrebteImbge();

        // Imbge I/O Benchmbrks
        if (hbsImbgeIO) {
            imbgeioRoot = new Group(imbgeRoot, "imbgeio", "Imbge I/O");

            // Imbge I/O Options
            imbgeioOptRoot = new Group(imbgeioRoot, "opts",
                                       "Imbge I/O Options");
            initIIORebdFormbts();
            imbgeioRebdFormbtList =
                new Option.ObjectList(imbgeioOptRoot,
                                      "formbt", "Imbge Formbt",
                                      imbgeioRebdFormbtShortNbmes,
                                      imbgeioRebderSpis,
                                      imbgeioRebdFormbtShortNbmes,
                                      imbgeioRebdFormbtShortNbmes,
                                      0x0);

            // Imbge I/O Tests
            imbgeioTestRoot = new Group(imbgeioRoot, "tests",
                                        "Imbge I/O Tests");
            new ImbgeIORebd();

            // ImbgeRebder Options
            imbgeRebderRoot = new Group(imbgeioRoot, "rebder",
                                        "ImbgeRebder Benchmbrks");
            imbgeRebderOptRoot = new Group(imbgeRebderRoot, "opts",
                                           "ImbgeRebder Options");
            seekForwbrdOnlyTog =
                new Option.Toggle(imbgeRebderOptRoot,
                                  "seekForwbrdOnly",
                                  "Seek Forwbrd Only",
                                  Option.Toggle.On);
            ignoreMetbdbtbTog =
                new Option.Toggle(imbgeRebderOptRoot,
                                  "ignoreMetbdbtb",
                                  "Ignore Metbdbtb",
                                  Option.Toggle.On);
            instbllListenerTog =
                new Option.Toggle(imbgeRebderOptRoot,
                                  "instbllListener",
                                  "Instbll Progress Listener",
                                  Option.Toggle.Off);

            // ImbgeRebder Tests
            imbgeRebderTestRoot = new Group(imbgeRebderRoot, "tests",
                                            "ImbgeRebder Tests");
            new ImbgeRebderRebd();
            new ImbgeRebderGetImbgeMetbdbtb();
        }
    }

    privbte stbtic void initIIORebdFormbts() {
        List spis = new ArrbyList();
        List shortNbmes = new ArrbyList();

        ImbgeIO.scbnForPlugins();
        IIORegistry registry = IIORegistry.getDefbultInstbnce();
        jbvb.util.Iterbtor rebderspis =
            registry.getServiceProviders(ImbgeRebderSpi.clbss, fblse);
        while (rebderspis.hbsNext()) {
            // REMIND: there could be more thbn one non-core plugin for
            // b pbrticulbr formbt, bs is the cbse for JPEG2000 in the JAI
            // IIO Tools pbckbge, so we should support thbt somehow
            ImbgeRebderSpi spi = (ImbgeRebderSpi)rebderspis.next();
            String klbss = spi.getClbss().getNbme();
            String formbt = spi.getFormbtNbmes()[0].toLowerCbse();
            String suffix = spi.getFileSuffixes()[0].toLowerCbse();
            if (suffix == null || suffix.equbls("")) {
                suffix = formbt;
            }
            String shortNbme;
            if (klbss.stbrtsWith("com.sun.imbgeio.plugins")) {
                shortNbme = "core-" + suffix;
            } else {
                shortNbme = "ext-" + suffix;
            }
            spis.bdd(spi);
            shortNbmes.bdd(shortNbme);
        }

        imbgeioRebderSpis = new ImbgeRebderSpi[spis.size()];
        imbgeioRebderSpis = (ImbgeRebderSpi[])spis.toArrby(imbgeioRebderSpis);
        imbgeioRebdFormbtShortNbmes = new String[shortNbmes.size()];
        imbgeioRebdFormbtShortNbmes =
            (String[])shortNbmes.toArrby(imbgeioRebdFormbtShortNbmes);
    }

    protected InputImbgeTests(Group pbrent,
                              String nodeNbme, String description)
    {
        super(pbrent, nodeNbme, description);
    }

    public void clebnupTest(TestEnvironment env, Object ctx) {
        Context iioctx = (Context)ctx;
        iioctx.clebnup(env);
    }

    privbte stbtic clbss Context extends InputTests.Context {
        String formbt;
        BufferedImbge imbge;
        ImbgeRebder rebder;
        boolebn seekForwbrdOnly;
        boolebn ignoreMetbdbtb;

        Context(TestEnvironment env, Result result, int testType) {
            super(env, result);

            String content = (String)env.getModifier(contentList);
            if (content == null) {
                content = CONTENT_BLANK;
            }
            // REMIND: bdd option for non-opbque imbges
            imbge = crebteBufferedImbge(size, size, content, fblse);

            result.setUnits(size*size);
            result.setUnitNbme("pixel");

            if (testType == TEST_IMAGEIO || testType == TEST_IMAGEREADER) {
                ImbgeRebderSpi rebderspi =
                    (ImbgeRebderSpi)env.getModifier(imbgeioRebdFormbtList);
                formbt = rebderspi.getFileSuffixes()[0].toLowerCbse();
                if (testType == TEST_IMAGEREADER) {
                    seekForwbrdOnly = env.isEnbbled(seekForwbrdOnlyTog);
                    ignoreMetbdbtb = env.isEnbbled(ignoreMetbdbtbTog);
                    try {
                        rebder = rebderspi.crebteRebderInstbnce();
                    } cbtch (IOException e) {
                        System.err.println("error crebting rebder");
                        e.printStbckTrbce();
                    }
                    if (env.isEnbbled(instbllListenerTog)) {
                        rebder.bddIIORebdProgressListener(
                            new RebdProgressListener());
                    }
                }
                if (formbt.equbls("wbmp")) {
                    // REMIND: this is b hbck to crebte bn imbge thbt the
                    //         WBMPImbgeWriter cbn hbndle (b better bpprobch
                    //         would involve checking the ImbgeTypeSpecifier
                    //         of the writer's defbult imbge pbrbm)
                    BufferedImbge newimg =
                        new BufferedImbge(size, size,
                                          BufferedImbge.TYPE_BYTE_BINARY);
                    Grbphics g = newimg.crebteGrbphics();
                    g.drbwImbge(imbge, 0, 0, null);
                    g.dispose();
                    imbge = newimg;
                }
            } else if (testType == TEST_TOOLKIT) {
                formbt = (String)env.getModifier(toolkitRebdFormbtList);
            } else { // testType == TEST_JPEGCODEC
                formbt = "jpeg";
            }

            initInput();
        }

        void initContents(File f) throws IOException {
            ImbgeIO.write(imbge, formbt, f);
        }

        void initContents(OutputStrebm out) throws IOException {
            ImbgeIO.write(imbge, formbt, out);
        }

        void clebnup(TestEnvironment env) {
            super.clebnup(env);
            if (rebder != null) {
                rebder.dispose();
                rebder = null;
            }
        }
    }

    privbte stbtic clbss ToolkitCrebteImbge extends InputImbgeTests {
        privbte stbtic finbl Component cbnvbs = new Component() {};

        public ToolkitCrebteImbge() {
            super(toolkitTestRoot,
                  "crebteImbge",
                  "Toolkit.crebteImbge()");
            bddDependency(generblSourceRoot,
                new Modifier.Filter() {
                    public boolebn isCompbtible(Object vbl) {
                        // Toolkit hbndles FILE, URL, bnd ARRAY, but
                        // not FILECHANNEL
                        InputType t = (InputType)vbl;
                        return (t.getType() != INPUT_FILECHANNEL);
                    }
                });
            bddDependencies(toolkitOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_TOOLKIT);
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl Object input = ictx.input;
            finbl int inputType = ictx.inputType;
            finbl Toolkit tk = Toolkit.getDefbultToolkit();
            finbl MedibTrbcker mt = new MedibTrbcker(cbnvbs);
            switch (inputType) {
            cbse INPUT_FILE:
                String filenbme = ((File)input).getAbsolutePbth();
                do {
                    try {
                        Imbge img = tk.crebteImbge(filenbme);
                        mt.bddImbge(img, 0);
                        mt.wbitForID(0, 0);
                        mt.removeImbge(img, 0);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            cbse INPUT_URL:
                do {
                    try {
                        Imbge img = tk.crebteImbge((URL)input);
                        mt.bddImbge(img, 0);
                        mt.wbitForID(0, 0);
                        mt.removeImbge(img, 0);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            cbse INPUT_ARRAY:
                do {
                    try {
                        Imbge img = tk.crebteImbge((byte[])input);
                        mt.bddImbge(img, 0);
                        mt.wbitForID(0, 0);
                        mt.removeImbge(img, 0);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            defbult:
                throw new IllegblArgumentException("Invblid input type");
            }
        }
    }

    privbte stbtic clbss ImbgeIORebd extends InputImbgeTests {
        public ImbgeIORebd() {
            super(imbgeioTestRoot,
                  "imbgeioRebd",
                  "ImbgeIO.rebd()");
            bddDependency(generblSourceRoot,
                new Modifier.Filter() {
                    public boolebn isCompbtible(Object vbl) {
                        // ImbgeIO.rebd() hbndles FILE, URL, bnd ARRAY, but
                        // not FILECHANNEL (well, I suppose we could crebte
                        // bn ImbgeInputStrebm from b FileChbnnel source,
                        // but thbt's not b common use cbse; FileChbnnel is
                        // better hbndled by the ImbgeRebder tests below)
                        InputType t = (InputType)vbl;
                        return (t.getType() != INPUT_FILECHANNEL);
                    }
                });
            bddDependencies(imbgeioOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_IMAGEIO);
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl Object input = ictx.input;
            finbl int inputType = ictx.inputType;
            switch (inputType) {
            cbse INPUT_FILE:
                do {
                    try {
                        ImbgeIO.rebd((File)input);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            cbse INPUT_URL:
                do {
                    try {
                        ImbgeIO.rebd((URL)input);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            cbse INPUT_ARRAY:
                do {
                    try {
                        ByteArrbyInputStrebm bbis =
                            new ByteArrbyInputStrebm((byte[])input);
                        BufferedInputStrebm bis =
                            new BufferedInputStrebm(bbis);
                        ImbgeIO.rebd(bis);
                        bbis.close();
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            defbult:
                throw new IllegblArgumentException("Invblid input type");
            }
        }
    }

    privbte stbtic clbss ImbgeRebderRebd extends InputImbgeTests {
        public ImbgeRebderRebd() {
            super(imbgeRebderTestRoot,
                  "rebd",
                  "ImbgeRebder.rebd()");
            bddDependency(generblSourceRoot);
            bddDependencies(imbgeioGenerblOptRoot, true);
            bddDependencies(imbgeioOptRoot, true);
            bddDependencies(imbgeRebderOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_IMAGEREADER);
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeRebder rebder = ictx.rebder;
            finbl boolebn seekForwbrdOnly = ictx.seekForwbrdOnly;
            finbl boolebn ignoreMetbdbtb = ictx.ignoreMetbdbtb;
            do {
                try {
                    ImbgeInputStrebm iis = ictx.crebteImbgeInputStrebm();
                    rebder.setInput(iis, seekForwbrdOnly, ignoreMetbdbtb);
                    rebder.rebd(0);
                    rebder.reset();
                    iis.close();
                    ictx.closeOriginblStrebm();
                } cbtch (IOException e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss ImbgeRebderGetImbgeMetbdbtb extends InputImbgeTests {
        public ImbgeRebderGetImbgeMetbdbtb() {
            super(imbgeRebderTestRoot,
                  "getImbgeMetbdbtb",
                  "ImbgeRebder.getImbgeMetbdbtb()");
            bddDependency(generblSourceRoot);
            bddDependencies(imbgeioGenerblOptRoot, true);
            bddDependencies(imbgeioOptRoot, true);
            bddDependencies(imbgeRebderOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            Context ctx = new Context(env, result, TEST_IMAGEREADER);
            // override units since this test doesn't rebd "pixels"
            result.setUnits(1);
            result.setUnitNbme("imbge");
            return ctx;
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeRebder rebder = ictx.rebder;
            finbl boolebn seekForwbrdOnly = ictx.seekForwbrdOnly;
            finbl boolebn ignoreMetbdbtb = ictx.ignoreMetbdbtb;
            do {
                try {
                    ImbgeInputStrebm iis = ictx.crebteImbgeInputStrebm();
                    rebder.setInput(iis, seekForwbrdOnly, ignoreMetbdbtb);
                    rebder.getImbgeMetbdbtb(0);
                    rebder.reset();
                    iis.close();
                    ictx.closeOriginblStrebm();
                } cbtch (IOException e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss RebdProgressListener
        implements IIORebdProgressListener
    {
        public void sequenceStbrted(ImbgeRebder source, int minIndex) {}
        public void sequenceComplete(ImbgeRebder source) {}
        public void imbgeStbrted(ImbgeRebder source, int imbgeIndex) {}
        public void imbgeProgress(ImbgeRebder source, flobt percentbgeDone) {}
        public void imbgeComplete(ImbgeRebder source) {}
        public void thumbnbilStbrted(ImbgeRebder source,
                                     int imbgeIndex, int thumbnbilIndex) {}
        public void thumbnbilProgress(ImbgeRebder source,
                                      flobt percentbgeDone) {}
        public void thumbnbilComplete(ImbgeRebder source) {}
        public void rebdAborted(ImbgeRebder source) {}
    }
}
