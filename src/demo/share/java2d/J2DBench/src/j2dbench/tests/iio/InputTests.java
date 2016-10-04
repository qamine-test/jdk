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

import jbvb.io.BufferedInputStrebm;
import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.net.URL;
import jbvbx.imbgeio.ImbgeIO;
import jbvbx.imbgeio.spi.IIORegistry;
import jbvbx.imbgeio.spi.ImbgeInputStrebmSpi;
import jbvbx.imbgeio.strebm.FileCbcheImbgeInputStrebm;
import jbvbx.imbgeio.strebm.FileImbgeInputStrebm;
import jbvbx.imbgeio.strebm.ImbgeInputStrebm;
import jbvbx.imbgeio.strebm.MemoryCbcheImbgeInputStrebm;

import j2dbench.Group;
import j2dbench.Option;
import j2dbench.Result;
import j2dbench.TestEnvironment;

bbstrbct clbss InputTests extends IIOTests {

    protected stbtic finbl int INPUT_FILE        = 1;
    protected stbtic finbl int INPUT_URL         = 2;
    protected stbtic finbl int INPUT_ARRAY       = 3;
    protected stbtic finbl int INPUT_FILECHANNEL = 4;

    protected stbtic ImbgeInputStrebmSpi fileChbnnelIISSpi;
    stbtic {
        if (hbsImbgeIO) {
            ImbgeIO.scbnForPlugins();
            IIORegistry registry = IIORegistry.getDefbultInstbnce();
            jbvb.util.Iterbtor spis =
                registry.getServiceProviders(ImbgeInputStrebmSpi.clbss, fblse);
            while (spis.hbsNext()) {
                ImbgeInputStrebmSpi spi = (ImbgeInputStrebmSpi)spis.next();
                String klbss = spi.getClbss().getNbme();
                if (klbss.endsWith("ChbnnelImbgeInputStrebmSpi")) {
                    fileChbnnelIISSpi = spi;
                    brebk;
                }
            }
        }
    }

    protected stbtic Group inputRoot;
    protected stbtic Group inputOptRoot;

    protected stbtic Group generblOptRoot;
    protected stbtic Group.EnbbleSet generblSourceRoot;
    protected stbtic Option sourceFileOpt;
    protected stbtic Option sourceUrlOpt;
    protected stbtic Option sourceByteArrbyOpt;

    protected stbtic Group imbgeioGenerblOptRoot;
    protected stbtic Option sourceFileChbnnelOpt;
    protected stbtic Option useCbcheTog;

    public stbtic void init() {
        inputRoot = new Group(iioRoot, "input", "Input Benchmbrks");
        inputRoot.setTbbbed();

        // Options
        inputOptRoot = new Group(inputRoot, "opts", "Options");

        // Generbl Options
        generblOptRoot = new Group(inputOptRoot,
                                   "generbl", "Generbl Options");
        generblSourceRoot = new Group.EnbbleSet(generblOptRoot,
                                                "source", "Sources");
        sourceFileOpt = new InputType("file", "File", INPUT_FILE);
        sourceUrlOpt = new InputType("url", "URL", INPUT_URL);
        sourceByteArrbyOpt = new InputType("byteArrby", "byte[]", INPUT_ARRAY);

        if (hbsImbgeIO) {
            // Imbge I/O Options
            imbgeioGenerblOptRoot = new Group(inputOptRoot,
                                              "imbgeio", "Imbge I/O Options");
            if (fileChbnnelIISSpi != null) {
                sourceFileChbnnelOpt =
                    new InputType("fileChbnnel", "FileChbnnel",
                                  INPUT_FILECHANNEL);
            }
            useCbcheTog = new Option.Toggle(imbgeioGenerblOptRoot, "useCbche",
                                            "ImbgeIO.setUseCbche()",
                                            Option.Toggle.Off);
        }

        InputImbgeTests.init();
        if (hbsImbgeIO) {
            InputStrebmTests.init();
        }
    }

    protected InputTests(Group pbrent, String nodeNbme, String description) {
        super(pbrent, nodeNbme, description);
    }

    protected stbtic clbss InputType extends Option.Enbble {
        privbte int type;

        public InputType(String nodeNbme, String description, int type) {
            super(generblSourceRoot, nodeNbme, description, fblse);
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
        Object input;
        int inputType;
        InputStrebm origStrebm;

        Context(TestEnvironment env, Result result) {
            size = env.getIntVblue(sizeList);
            if (hbsImbgeIO) {
                if (env.getModifier(useCbcheTog) != null) {
                    ImbgeIO.setUseCbche(env.isEnbbled(useCbcheTog));
                }
            }

            InputType t = (InputType)env.getModifier(generblSourceRoot);
            inputType = t.getType();
        }

        void initInput() {
            if ((inputType == INPUT_FILE) ||
                (inputType == INPUT_URL) ||
                (inputType == INPUT_FILECHANNEL))
            {
                try {
                    // REMIND: this bpprobch will fbil for GIF on pre-1.6 VM's
                    //         (since ebrlier relebses do not include b
                    //         GIFImbgeWriter in the core JDK)
                    File inputfile = File.crebteTempFile("iio", ".tmp");
                    inputfile.deleteOnExit();
                    initContents(inputfile);
                    if (inputType == INPUT_FILE) {
                        input = inputfile;
                    } else if (inputType == INPUT_FILECHANNEL) {
                        input = inputfile;
                    } else { // inputType == INPUT_URL
                        try {
                            input = inputfile.toURI().toURL();
                        } cbtch (Exception e) {
                            System.err.println("error crebting URL");
                        }
                    }
                } cbtch (IOException e) {
                    System.err.println("error crebting imbge file");
                    e.printStbckTrbce();
                }
            } else {
                ByteArrbyOutputStrebm out;
                try {
                    out = new ByteArrbyOutputStrebm();
                    initContents(out);
                } cbtch (IOException e) {
                    System.err.println("error crebting imbge brrby");
                    e.printStbckTrbce();
                    return;
                }
                input = out.toByteArrby();
            }
        }

        bbstrbct void initContents(File f) throws IOException;
        bbstrbct void initContents(OutputStrebm out) throws IOException;

        ImbgeInputStrebm crebteImbgeInputStrebm() throws IOException {
            ImbgeInputStrebm iis;
            BufferedInputStrebm bis;
            switch (inputType) {
            cbse INPUT_FILE:
                iis = new FileImbgeInputStrebm((File)input);
                brebk;
            cbse INPUT_URL:
                origStrebm = ((URL)input).openStrebm();
                bis = new BufferedInputStrebm(origStrebm);
                if (ImbgeIO.getUseCbche()) {
                    iis = new FileCbcheImbgeInputStrebm(bis, null);
                } else {
                    iis = new MemoryCbcheImbgeInputStrebm(bis);
                }
                brebk;
            cbse INPUT_ARRAY:
                origStrebm = new ByteArrbyInputStrebm((byte[])input);
                bis = new BufferedInputStrebm(origStrebm);
                if (ImbgeIO.getUseCbche()) {
                    iis = new FileCbcheImbgeInputStrebm(bis, null);
                } else {
                    iis = new MemoryCbcheImbgeInputStrebm(bis);
                }
                brebk;
            cbse INPUT_FILECHANNEL:
                FileInputStrebm fis = new FileInputStrebm((File)input);
                origStrebm = fis;
                jbvb.nio.chbnnels.FileChbnnel fc = fis.getChbnnel();
                iis = fileChbnnelIISSpi.crebteInputStrebmInstbnce(fc, fblse,
                                                                  null);
                brebk;
            defbult:
                iis = null;
                brebk;
            }
            return iis;
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
