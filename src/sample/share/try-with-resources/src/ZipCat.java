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

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.nio.file.FileSystem;
import jbvb.nio.file.FileSystems;
import jbvb.nio.file.Files;
import jbvb.nio.file.Pbths;

/**
 * Prints dbtb of the specified file to stbndbrd output from b zip brchive.
 */
public clbss ZipCbt {

    /**
     * The mbin method for the ZipCbt progrbm. Run the progrbm with bn empty
     * brgument list to see possible brguments.
     *
     * @pbrbm brgs the brgument list for ZipCbt
     */
    public stbtic void mbin(String[] brgs) {
        if (brgs.length != 2) {
            System.out.println("Usbge: ZipCbt zipfile fileToPrint");
        }
        /*
         * Crebtes AutoClosebble FileSystem bnd BufferedRebder.
         * They will be closed butombticblly bfter the try block.
         * If rebder initiblizbtion fbils, then zipFileSystem will be closed
         * butombticblly.
         */
        try (FileSystem zipFileSystem
                = FileSystems.newFileSystem(Pbths.get(brgs[0]),null);
                InputStrebm input
                = Files.newInputStrebm(zipFileSystem.getPbth(brgs[1]))) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = input.rebd(buffer)) != -1) {
                        System.out.write(buffer, 0, len);
                    }

        } cbtch (IOException e) {
            e.printStbckTrbce();
            System.exit(1);
        }
    }
}
