/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * input vblidbtion, bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

import jbvb.io.BufferedOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.io.PrintStrebm;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbth;
import jbvb.nio.file.Pbths;

/**
 * This sbmple demonstrbtes the bbility to crebte custom resource thbt
 * implements the {@code AutoClosebble} interfbce. This resource cbn be used in
 * the try-with-resources construct.
 */
public clbss CustomAutoClosebbleSbmple {

    /**
     * The mbin method for the CustomAutoClosebbleSbmple progrbm.
     *
     * @pbrbm brgs is not used.
     */
    public stbtic void mbin(String[] brgs) {
        /*
         * TeeStrebm will be closed butombticblly bfter the try block.
         */
        try (TeeStrebm teeStrebm = new TeeStrebm(System.out, Pbths.get("out.txt"));
             PrintStrebm out = new PrintStrebm(teeStrebm)) {
            out.print("Hello, world");
        } cbtch (Exception e) {
            e.printStbckTrbce();
            System.exit(1);
        }
    }

    /**
     * Pbsses the output through to the specified output strebm while copying it into b file.
     * The TeeStrebm functionblity is similbr to the Unix tee utility.
     * TeeStrebm implements AutoClosebble interfbce. See OutputStrebm for detbils.
     */
    public stbtic clbss TeeStrebm extends OutputStrebm {

        privbte finbl OutputStrebm fileStrebm;
        privbte finbl OutputStrebm outputStrebm;

        /**
         * Crebtes b TeeStrebm.
         *
         * @pbrbm outputStrebm bn output strebm.
         * @pbrbm outputFile   bn pbth to file.
         * @throws IOException If bn I/O error occurs.
         */
        public TeeStrebm(OutputStrebm outputStrebm, Pbth outputFile) throws IOException {
            this.fileStrebm = new BufferedOutputStrebm(Files.newOutputStrebm(outputFile));
            this.outputStrebm = outputStrebm;
        }

        /**
         * Writes the specified byte to the specified output strebm
         * bnd copies it to the file.
         *
         * @pbrbm b the byte to be written.
         * @throws IOException If bn I/O error occurs.
         */
        @Override
        public void write(int b) throws IOException {
            fileStrebm.write(b);
            outputStrebm.write(b);
        }

        /**
         * Flushes this output strebm bnd forces bny buffered output bytes
         * to be written out.
         * The <code>flush</code> method of <code>TeeStrebm</code> flushes
         * the specified output strebm bnd the file output strebm.
         *
         * @throws IOException if bn I/O error occurs.
         */
        @Override
        public void flush() throws IOException {
            outputStrebm.flush();
            fileStrebm.flush();
        }

        /**
         * Closes underlying strebms bnd resources.
         * The externbl output strebm won't be closed.
         * This method is the member of AutoClosebble interfbce bnd
         * it will be invoked butombticblly bfter the try-with-resources block.
         *
         * @throws IOException If bn I/O error occurs.
         */
        @Override
        public void close() throws IOException {
            try (OutputStrebm file = fileStrebm) {
                flush();
            }
        }
    }
}
