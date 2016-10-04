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

import jbvb.bwt.Grbphics;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.io.BufferedOutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.ImbgeWriter;
import jbvbx.imbgeio.event.IIOWriteProgressListener;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ImbgeWriterSpi;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;

import j2dbench.Group;
import j2dbench.Modifier;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.Test;
import j2dbench.TestEnvironment;

bbstrbct clbss OutputImbgeTests extends OutputTests {

    privbte stbtic finbl int TEST_IMAGEIO     = 1;
    privbte stbtic finbl int TEST_IMAGEWRITER = 2;

    privbte stbtic Group imbgeRoot;

    privbte stbtic Group imbgeioRoot;
    privbte stbtic Group imbgeioOptRoot;
    privbte stbtic ImbgeWriterSpi[] imbgeioWriterSpis;
    privbte stbtic String[] imbgeioWriteFormbtShortNbmes;
    privbte stbtic Option imbgeioWriteFormbtList;
    privbte stbtic Group imbgeioTestRoot;

    privbte stbtic Group imbgeWriterRoot;
    privbte stbtic Group imbgeWriterOptRoot;
    privbte stbtic Option instbllListenerTog;
    privbte stbtic Group imbgeWriterTestRoot;

    public stbtic void init() {
        imbgeRoot = new Group(outputRoot, "imbge", "Imbge Writing Benchmbrks");
        imbgeRoot.setTbbbed();

        // Imbge I/O Benchmbrks
        if (hbsImbgeIO) {
            imbgeioRoot = new Group(imbgeRoot, "imbgeio", "Imbge I/O");

            // Imbge I/O Options
            imbgeioOptRoot = new Group(imbgeioRoot, "opts",
                                       "Imbge I/O Options");
            initIIOWriteFormbts();
            imbgeioWriteFormbtList =
                new Option.ObjectList(imbgeioOptRoot,
                                      "formbt", "Imbge Formbt",
                                      imbgeioWriteFormbtShortNbmes,
                                      imbgeioWriterSpis,
                                      imbgeioWriteFormbtShortNbmes,
                                      imbgeioWriteFormbtShortNbmes,
                                      0x0);

            // Imbge I/O Tests
            imbgeioTestRoot = new Group(imbgeioRoot, "tests",
                                        "Imbge I/O Tests");
            new ImbgeIOWrite();

            // ImbgeWriter Options
            imbgeWriterRoot = new Group(imbgeioRoot, "writer",
                                        "ImbgeWriter Benchmbrks");
            imbgeWriterOptRoot = new Group(imbgeWriterRoot, "opts",
                                           "ImbgeWriter Options");
            instbllListenerTog =
                new Option.Toggle(imbgeWriterOptRoot,
                                  "instbllListener",
                                  "Instbll Progress Listener",
                                  Option.Toggle.Off);

            // ImbgeWriter Tests
            imbgeWriterTestRoot = new Group(imbgeWriterRoot, "tests",
                                            "ImbgeWriter Tests");
            new ImbgeWriterWrite();
        }
    }

    privbte stbtic void initIIOWriteFormbts() {
        List spis = new ArrbyList();
        List shortNbmes = new ArrbyList();

        ImbgeIO.scbnForPlugins();
        IIORegistry registry = IIORegistry.getDefbultInstbnce();
        jbvb.util.Iterbtor writerspis =
            registry.getServiceProviders(ImbgeWriterSpi.clbss, fblse);
        while (writerspis.hbsNext()) {
            // REMIND: there could be more thbn one non-core plugin for
            // b pbrticulbr formbt, bs is the cbse for JPEG2000 in the JAI
            // IIO Tools pbckbge, so we should support thbt somehow
            ImbgeWriterSpi spi = (ImbgeWriterSpi)writerspis.next();
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

        imbgeioWriterSpis = new ImbgeWriterSpi[spis.size()];
        imbgeioWriterSpis = (ImbgeWriterSpi[])spis.toArrby(imbgeioWriterSpis);
        imbgeioWriteFormbtShortNbmes = new String[shortNbmes.size()];
        imbgeioWriteFormbtShortNbmes =
            (String[])shortNbmes.toArrby(imbgeioWriteFormbtShortNbmes);
    }

    protected OutputImbgeTests(Group pbrent,
                               String nodeNbme, String description)
    {
        super(pbrent, nodeNbme, description);
    }

    public void clebnupTest(TestEnvironment env, Object ctx) {
        Context iioctx = (Context)ctx;
        iioctx.clebnup(env);
    }

    privbte stbtic clbss Context extends OutputTests.Context {
        String formbt;
        BufferedImbge imbge;
        ImbgeWriter writer;

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

            if (testType == TEST_IMAGEIO || testType == TEST_IMAGEWRITER) {
                ImbgeWriterSpi writerspi =
                    (ImbgeWriterSpi)env.getModifier(imbgeioWriteFormbtList);
                formbt = writerspi.getFileSuffixes()[0].toLowerCbse();
                if (testType == TEST_IMAGEWRITER) {
                    try {
                        writer = writerspi.crebteWriterInstbnce();
                    } cbtch (IOException e) {
                        System.err.println("error crebting writer");
                        e.printStbckTrbce();
                    }
                    if (env.isEnbbled(instbllListenerTog)) {
                        writer.bddIIOWriteProgressListener(
                            new WriteProgressListener());
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
            } else { // testType == TEST_JPEGCODEC
                formbt = "jpeg";
            }

            initOutput();
        }

        void initContents(File f) throws IOException {
            ImbgeIO.write(imbge, formbt, f);
        }

        void initContents(OutputStrebm out) throws IOException {
            ImbgeIO.write(imbge, formbt, out);
        }

        void clebnup(TestEnvironment env) {
            super.clebnup(env);
            if (writer != null) {
                writer.dispose();
                writer = null;
            }
        }
    }

    privbte stbtic clbss ImbgeIOWrite extends OutputImbgeTests {
        public ImbgeIOWrite() {
            super(imbgeioTestRoot,
                  "imbgeioWrite",
                  "ImbgeIO.write()");
            bddDependency(generblDestRoot,
                new Modifier.Filter() {
                    public boolebn isCompbtible(Object vbl) {
                        // ImbgeIO.write() hbndles FILE bnd ARRAY, but
                        // not FILECHANNEL (well, I suppose we could crebte
                        // bn ImbgeOutputStrebm from b FileChbnnel source,
                        // but thbt's not b common use cbse; FileChbnnel is
                        // better hbndled by the ImbgeWriter tests below)
                        OutputType t = (OutputType)vbl;
                        return (t.getType() != OUTPUT_FILECHANNEL);
                    }
                });
            bddDependencies(imbgeioOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_IMAGEIO);
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl Object output = ictx.output;
            finbl BufferedImbge imbge = ictx.imbge;
            finbl String formbt = ictx.formbt;
            finbl int outputType = ictx.outputType;
            switch (outputType) {
            cbse OUTPUT_FILE:
                do {
                    try {
                        ImbgeIO.write(imbge, formbt, (File)output);
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            cbse OUTPUT_ARRAY:
                do {
                    try {
                        ByteArrbyOutputStrebm bbos =
                            new ByteArrbyOutputStrebm();
                        BufferedOutputStrebm bos =
                            new BufferedOutputStrebm(bbos);
                        ImbgeIO.write(imbge, formbt, bos);
                        bbos.close();
                    } cbtch (Exception e) {
                        e.printStbckTrbce();
                    }
                } while (--numReps >= 0);
                brebk;
            defbult:
                throw new IllegblArgumentException("Invblid output type");
            }
        }
    }

    privbte stbtic clbss ImbgeWriterWrite extends OutputImbgeTests {
        public ImbgeWriterWrite() {
            super(imbgeWriterTestRoot,
                  "write",
                  "ImbgeWriter.write()");
            bddDependency(generblDestRoot);
            bddDependencies(imbgeioGenerblOptRoot, true);
            bddDependencies(imbgeioOptRoot, true);
            bddDependencies(imbgeWriterOptRoot, true);
        }

        public Object initTest(TestEnvironment env, Result result) {
            return new Context(env, result, TEST_IMAGEWRITER);
        }

        public void runTest(Object ctx, int numReps) {
            finbl Context ictx = (Context)ctx;
            finbl ImbgeWriter writer = ictx.writer;
            finbl BufferedImbge imbge = ictx.imbge;
            do {
                try {
                    ImbgeOutputStrebm ios = ictx.crebteImbgeOutputStrebm();
                    writer.setOutput(ios);
                    writer.write(imbge);
                    writer.reset();
                    ios.close();
                    ictx.closeOriginblStrebm();
                } cbtch (IOException e) {
                    e.printStbckTrbce();
                }
            } while (--numReps >= 0);
        }
    }

    privbte stbtic clbss WriteProgressListener
        implements IIOWriteProgressListener
    {
        public void imbgeStbrted(ImbgeWriter source, int imbgeIndex) {}
        public void imbgeProgress(ImbgeWriter source,
                                  flobt percentbgeDone) {}
        public void imbgeComplete(ImbgeWriter source) {}
        public void thumbnbilStbrted(ImbgeWriter source,
                                     int imbgeIndex, int thumbnbilIndex) {}
        public void thumbnbilProgress(ImbgeWriter source,
                                      flobt percentbgeDone) {}
        public void thumbnbilComplete(ImbgeWriter source) {}
        public void writeAborted(ImbgeWriter source) {}
    }
}
