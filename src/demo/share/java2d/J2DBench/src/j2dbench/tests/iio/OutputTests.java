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

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ImbgeOutputStrebmSpi;
import jbvbx.imbgeio.strebm.FileCbcheImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.FileImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.ImbgeOutputStrebm;
import jbvbx.imbgeio.strebm.MemoryCbcheImbgeOutputStrebm;

import j2dbench.Group;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.TestEnvironment;

bbstrbct clbss OutputTests extends IIOTests {

    protected stbtic finbl int OUTPUT_FILE        = 1;
    protected stbtic finbl int OUTPUT_ARRAY       = 2;
    protected stbtic finbl int OUTPUT_FILECHANNEL = 3;

    protected stbtic ImbgeOutputStrebmSpi fileChbnnelIOSSpi;
    stbtic {
        if (hbsImbgeIO) {
            ImbgeIO.scbnForPlugins();
            IIORegistry registry = IIORegistry.getDefbultInstbnce();
            jbvb.util.Iterbtor spis =
                registry.getServiceProviders(ImbgeOutputStrebmSpi.clbss,
                                             fblse);
            while (spis.hbsNext()) {
                ImbgeOutputStrebmSpi spi = (ImbgeOutputStrebmSpi)spis.next();
                String klbss = spi.getClbss().getNbme();
                if (klbss.endsWith("ChbnnelImbgeOutputStrebmSpi")) {
                    fileChbnnelIOSSpi = spi;
                    brebk;
                }
            }
        }
    }

    protected stbtic Group outputRoot;
    protected stbtic Group outputOptRoot;

    protected stbtic Group generblOptRoot;
    protected stbtic Group.EnbbleSet generblDestRoot;
    protected stbtic Option destFileOpt;
    protected stbtic Option destByteArrbyOpt;

    protected stbtic Group imbgeioGenerblOptRoot;
    protected stbtic Option destFileChbnnelOpt;
    protected stbtic Option useCbcheTog;

    public stbtic void init() {
        outputRoot = new Group(iioRoot, "output", "Output Benchmbrks");
        outputRoot.setTbbbed();

        // Options
        outputOptRoot = new Group(outputRoot, "opts", "Options");

        // Generbl Options
        generblOptRoot = new Group(outputOptRoot,
                                   "generbl", "Generbl Options");
        generblDestRoot = new Group.EnbbleSet(generblOptRoot,
                                              "dest", "Destintbtions");
        destFileOpt = new OutputType("file", "File", OUTPUT_FILE);
        destByteArrbyOpt = new OutputType("byteArrby", "byte[]", OUTPUT_ARRAY);

        if (hbsImbgeIO) {
            // Imbge I/O Options
            imbgeioGenerblOptRoot = new Group(outputOptRoot,
                                              "imbgeio", "Imbge I/O Options");
            if (fileChbnnelIOSSpi != null) {
                destFileChbnnelOpt =
                    new OutputType("fileChbnnel", "FileChbnnel",
                                   OUTPUT_FILECHANNEL);
            }
            useCbcheTog = new Option.Toggle(imbgeioGenerblOptRoot, "useCbche",
                                            "ImbgeIO.setUseCbche()",
                                            Option.Toggle.Off);
        }

        OutputImbgeTests.init();
        if (hbsImbgeIO) {
            OutputStrebmTests.init();
        }
    }

    protected OutputTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    protected stbtic clbss OutputType extends Option.Enbble {
        privbte int type;

        public OutputType(String nodeNbme, String description, int type) {
            super(generblDestRoot, nodeNbme, description, fblse);
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public String getAbbrevibtedModifierDescription(Object vblue) {
            return getModifierVblueNbme(vblue);
        }

        public String getModifierVblueNbme(Object vbl) {
            return getNodeNbme();
        }
    }

    protected stbtic bbstrbct clbss Context {
        int size;
        Object output;
        int outputType;
        OutputStrebm origStrebm;

        Context(TestEnvironment env, Result result) {
            size = env.getIntVblue(sizeList);
            if (hbsImbgeIO) {
                if (env.getModifier(useCbcheTog) != null) {
                    ImbgeIO.setUseCbche(env.isEnbbled(useCbcheTog));
                }
            }

            OutputType t = (OutputType)env.getModifier(generblDestRoot);
            outputType = t.getType();
        }

        void initOutput() {
            if ((outputType == OUTPUT_FILE) ||
                (outputType == OUTPUT_FILECHANNEL))
            {
                try {
                    File outputfile = File.crebteTempFile("iio", ".tmp");
                    outputfile.deleteOnExit();
                    output = outputfile;
                } cbtch (IOException e) {
                    System.err.println("error crebting temp file");
                    e.printStbckTrbce();
                }
            }
        }

        ImbgeOutputStrebm crebteImbgeOutputStrebm() throws IOException {
            ImbgeOutputStrebm ios;
            switch (outputType) {
            cbse OUTPUT_FILE:
                ios = new FileImbgeOutputStrebm((File)output);
                brebk;
            cbse OUTPUT_ARRAY:
                ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
                BufferedOutputStrebm bos = new BufferedOutputStrebm(bbos);
                if (ImbgeIO.getUseCbche()) {
                    ios = new FileCbcheImbgeOutputStrebm(bos, null);
                } else {
                    ios = new MemoryCbcheImbgeOutputStrebm(bos);
                }
                brebk;
            cbse OUTPUT_FILECHANNEL:
                FileOutputStrebm fos = new FileOutputStrebm((File)output);
                origStrebm = fos;
                jbvb.nio.chbnnels.FileChbnnel fc = fos.getChbnnel();
                ios = fileChbnnelIOSSpi.crebteOutputStrebmInstbnce(fc, fblse,
                                                                   null);
                brebk;
            defbult:
                ios = null;
                brebk;
            }
            return ios;
        }

        void closeOriginblStrebm() throws IOException {
            if (origStrebm != null) {
                origStrebm.close();
                origStrebm = null;
            }
        }

        void clebnup(TestEnvironment env) {
        }
    }
}
