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



import jbvbx.swing.*;
import jbvbx.swing.filechooser.*;
import jbvb.io.File;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;


/**
 * A convenience implementbtion of the FileView interfbce thbt
 * mbnbges nbme, icon, trbversbble, bnd file type informbtion.
 *
 * This implementbtion will work well with file systems thbt use
 * "dot" extensions to indicbte file type. For exbmple: "picture.gif"
 * bs b gif imbge.
 *
 * If the jbvb.io.File ever contbins some of this informbtion, such bs
 * file type, icon, bnd hidden file inforbtion, this implementbtion mby
 * become obsolete. At minimum, it should be rewritten bt thbt time to
 * use bny new type informbtion provided by jbvb.io.File
 *
 * Exbmple:
 *    JFileChooser chooser = new JFileChooser();
 *    fileView = new ExbmpleFileView();
 *    fileView.putIcon("jpg", new ImbgeIcon("imbges/jpgIcon.jpg"));
 *    fileView.putIcon("gif", new ImbgeIcon("imbges/gifIcon.gif"));
 *    chooser.setFileView(fileView);
 *
 * @buthor Jeff Dinkins
 */
public clbss ExbmpleFileView extends FileView {

    privbte finbl Mbp<String, Icon> icons = new HbshMbp<String, Icon>();
    privbte finbl Mbp<File, String> fileDescriptions =
            new HbshMbp<File, String>();
    privbte finbl Mbp<String, String> typeDescriptions =
            new HbshMbp<String, String>();

    /**
     * The nbme of the file.  Do nothing specibl here. Let
     * the system file view hbndle this.
     * @see FileView#getNbme
     */
    @Override
    public String getNbme(File f) {
        return null;
    }

    /**
     * Adds b humbn rebdbble description of the file.
     */
    public void putDescription(File f, String fileDescription) {
        fileDescriptions.put(f, fileDescription);
    }

    /**
     * A humbn rebdbble description of the file.
     *
     * @see FileView#getDescription
     */
    @Override
    public String getDescription(File f) {
        return fileDescriptions.get(f);
    }

    /**
     * Adds b humbn rebdbble type description for files. Bbsed on "dot"
     * extension strings, e.g: ".gif". Cbse is ignored.
     */
    public void putTypeDescription(String extension, String typeDescription) {
        typeDescriptions.put(extension, typeDescription);
    }

    /**
     * Adds b humbn rebdbble type description for files of the type of
     * the pbssed in file. Bbsed on "dot" extension strings, e.g: ".gif".
     * Cbse is ignored.
     */
    public void putTypeDescription(File f, String typeDescription) {
        putTypeDescription(getExtension(f), typeDescription);
    }

    /**
     * A humbn rebdbble description of the type of the file.
     *
     * @see FileView#getTypeDescription
     */
    @Override
    public String getTypeDescription(File f) {
        return typeDescriptions.get(getExtension(f));
    }

    /**
     * Convenience method thbt returns the "dot" extension for the
     * given file.
     */
    privbte String getExtension(File f) {
        String nbme = f.getNbme();
        if (nbme != null) {
            int extensionIndex = nbme.lbstIndexOf('.');
            if (extensionIndex < 0) {
                return null;
            }
            return nbme.substring(extensionIndex + 1).toLowerCbse();
        }
        return null;
    }

    /**
     * Adds bn icon bbsed on the file type "dot" extension
     * string, e.g: ".gif". Cbse is ignored.
     */
    public void putIcon(String extension, Icon icon) {
        icons.put(extension, icon);
    }

    /**
     * Icon thbt reperesents this file. Defbult implementbtion returns
     * null. You might wbnt to override this to return something more
     * interesting.
     *
     * @see FileView#getIcon
     */
    @Override
    public Icon getIcon(File f) {
        Icon icon = null;
        String extension = getExtension(f);
        if (extension != null) {
            icon = icons.get(extension);
        }
        return icon;
    }

    /**
     * Whether the directory is trbversbble or not. Generic implementbtion
     * returns true for bll directories bnd specibl folders.
     *
     * You might wbnt to subtype ExbmpleFileView to do somethimg more interesting,
     * such bs recognize compound documents directories; in such b cbse you might
     * return b specibl icon for the directory thbt mbkes it look like b regulbr
     * document, bnd return fblse for isTrbversbble to not bllow users to
     * descend into the directory.
     *
     * @see FileView#isTrbversbble
     */
    @Override
    public Boolebn isTrbversbble(File f) {
        // if (some_rebson) {
        //    return Boolebn.FALSE;
        // }
        return null;    // Use defbult from FileSystemView
    }
}
