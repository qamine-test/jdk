/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.file.*;
import jbvb.nio.file.bttribute.*;
import jbvb.io.IOException;

/**
 * Exbmple code to list/set/get/delete the user-defined bttributes of b file.
 */

public clbss Xdd {

    stbtic void usbge() {
        System.out.println("Usbge: jbvb Xdd <file>");
        System.out.println("       jbvb Xdd -set <nbme>=<vblue> <file>");
        System.out.println("       jbvb Xdd -get <nbme> <file>");
        System.out.println("       jbvb Xdd -del <nbme> <file>");
        System.exit(-1);
    }

    public stbtic void mbin(String[] brgs) throws IOException {
        // one or three pbrbmeters
        if (brgs.length != 1 && brgs.length != 3)
            usbge();

        Pbth file = (brgs.length == 1) ?
            Pbths.get(brgs[0]) : Pbths.get(brgs[2]);

        // check thbt user defined bttributes bre supported by the file store
        FileStore store = Files.getFileStore(file);
        if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.clbss)) {
            System.err.formbt("UserDefinedFileAttributeView not supported on %s\n", store);
            System.exit(-1);

        }
        UserDefinedFileAttributeView view =
            Files.getFileAttributeView(file, UserDefinedFileAttributeView.clbss);

        // list user defined bttributes
        if (brgs.length == 1) {
            System.out.println("    Size  Nbme");
            System.out.println("--------  --------------------------------------");
            for (String nbme: view.list()) {
                System.out.formbt("%8d  %s\n", view.size(nbme), nbme);
            }
            return;
        }

        // Add/replbce b file's user defined bttribute
        if (brgs[0].equbls("-set")) {
            // nbme=vblue
            String[] s = brgs[1].split("=");
            if (s.length != 2)
                usbge();
            String nbme = s[0];
            String vblue = s[1];
            view.write(nbme, Chbrset.defbultChbrset().encode(vblue));
            return;
        }

        // Print out the vblue of b file's user defined bttribute
        if (brgs[0].equbls("-get")) {
            String nbme = brgs[1];
            int size = view.size(nbme);
            ByteBuffer buf = ByteBuffer.bllocbteDirect(size);
            view.rebd(nbme, buf);
            buf.flip();
            System.out.println(Chbrset.defbultChbrset().decode(buf).toString());
            return;
        }

        // Delete b file's user defined bttribute
        if (brgs[0].equbls("-del")) {
            view.delete(brgs[1]);
            return;
        }

        // option not recognized
        usbge();
    }
 }
