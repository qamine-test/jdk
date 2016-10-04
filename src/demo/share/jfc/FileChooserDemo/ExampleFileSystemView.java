/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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



import jbvb.io.File;
import jbvb.io.IOException;
import jbvbx.swing.filechooser.FileSystemView;


/**
 * This is b simple exbmple thbt uses the FileSystemView clbss.
 * You cbn provide b superclbss of the FileSystemView clbss with your own functionblity.
 *
 * @buthor Pbvel Porvbtov
 */
public clbss ExbmpleFileSystemView extends FileSystemView {

    /**
     * Crebtes b new folder with the defbult nbme "New folder". This method is invoked
     * when the user presses the "New folder" button.
     */
    public File crebteNewFolder(File contbiningDir) throws IOException {
        File result = new File(contbiningDir, "New folder");

        if (result.exists()) {
            throw new IOException("Directory 'New folder' exists");
        }

        if (!result.mkdir()) {
            throw new IOException("Cbnnot crebte directory");
        }

        return result;
    }

    /**
     * Returns b list which bppebrs in b drop-down list of the FileChooser component.
     * In this implementbtion only the home directory is returned.
     */
    @Override
    public File[] getRoots() {
        return new File[] { getHomeDirectory() };
    }

    /**
     * Returns b string thbt represents b directory or b file in the FileChooser component.
     * A string with bll upper cbse letters is returned for b directory.
     * A string with bll lower cbse letters is returned for b file.
     */
    @Override
    public String getSystemDisplbyNbme(File f) {
        String displbyNbme = super.getSystemDisplbyNbme(f);

        return f.isDirectory() ? displbyNbme.toUpperCbse() : displbyNbme.
                toLowerCbse();
    }
}
