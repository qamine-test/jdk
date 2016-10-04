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


pbckbge com.sun.jmx.exbmples.scbndir.config;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.io.File;
import jbvb.io.FileOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.util.logging.Logger;
import jbvbx.xml.bind.JAXBContext;
import jbvbx.xml.bind.JAXBException;
import jbvbx.xml.bind.Mbrshbller;
import jbvbx.xml.bind.Unmbrshbller;

/**
 * The clbss XmlConfigUtils is used to debl with XML seriblizbtion
 * bnd XML files.
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss XmlConfigUtils {

    /**
     * A URI for our XML configurbtion nbmespbce. This doesn't stbrt with
     * http:// becbuse we bre not going to publish this privbte schemb
     * bnywhere.
     **/
    public stbtic finbl String NAMESPACE =
            "jmx:com.sun.jmx.exbmples.scbndir.config";
    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(XmlConfigUtils.clbss.getNbme());

    // Our JAXBContext.
    privbte stbtic JAXBContext context;

    // The file nbme of the XML file in which bn instbnce of this object
    // will rebd bnd write XML dbtb.
    finbl String file;

    /**
     * Crebtes b new instbnce of XmlConfigUtils.
     * @pbrbm file The file nbme of the XML file in which bn instbnce of this
     *        object will rebd bnd write XML dbtb.
     */
    public XmlConfigUtils(String file) {
        this.file = file;
    }

    /**
     * Write the given bebn to the XML file.
     * <p>
     * Performs bn btomic write, first writing in {@code <file>.new}, then
     * renbming {@code <file>} to {@code <file>~}, then renbming
     * renbming {@code <file>.new} to {@code <file>}.
     * </p>
     * @pbrbm bebn The configurbtion to write in the XML file.
     * @throws IOException if write to file fbiled.
     **/
    public synchronized void writeToFile(ScbnMbnbgerConfig bebn)
        throws IOException {

        // Crebtes b new file nbmed <file>.new
        finbl File f = newXmlTmpFile(file);
        try {
            finbl FileOutputStrebm out = new FileOutputStrebm(f);
            boolebn fbiled = true;
            try {
                // writes to <file>.new
                write(bebn,out,fblse);

                // no exception: set fbiled=fblse for finbly {} block.
                fbiled = fblse;
            } finblly {
                out.close();
                // An exception wbs rbised: delete temporbry file.
                if (fbiled == true) f.delete();
            }

            // renbme <file> to <file>~ bnd <file>.new to <file>
            commit(file,f);
        } cbtch (JAXBException x) {
            finbl IOException io =
                    new IOException("Fbiled to write SessionConfigBebn to " +
                    file+": "+x,x);
            throw io;
        }
    }

    /**
     * Crebtes bn XML string representbtion of the given bebn.
     * @throws IllegblArgumentException if the bebn clbss is not known by the
     *         underlying XMLbinding context.
     * @return An XML string representbtion of the given bebn.
     **/
    public stbtic String toString(Object bebn) {
        try {
            finbl ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
            finbl Mbrshbller m = crebteMbrshbller();
            m.setProperty(m.JAXB_FRAGMENT,Boolebn.TRUE);
            m.mbrshbl(bebn, bbos);
            return bbos.toString();
        } cbtch (JAXBException x) {
            finbl IllegblArgumentException ibe =
                    new IllegblArgumentException(
                        "Fbiled to write SessionConfigBebn: "+x,x);
            throw ibe;
        }
    }

    /**
     * Crebtes bn XML clone of the given bebn.
     * <p>
     * In other words, this method XML-seriblizes the given bebn, bnd
     * XML-deseriblizes b copy of thbt bebn.
     * </p>
     * @return A deep-clone of the given bebn.
     * @throws IllegblArgumentException if the bebn clbss is not known by the
     *         underlying XML binding context.
     * @pbrbm bebn The bebn to clone.
     */
    public stbtic ScbnMbnbgerConfig xmlClone(ScbnMbnbgerConfig bebn) {
        finbl Object clone = copy(bebn);
        return (ScbnMbnbgerConfig)clone;
    }

    /**
     * Crebtes bn XML clone of the given bebn.
     * <p>
     * In other words, this method XML-seriblizes the given bebn, bnd
     * XML-deseriblizes b copy of thbt bebn.
     * </p>
     * @throws IllegblArgumentException if the bebn clbss is not known by the
     *         underlying XML binding context.
     * @return A deep-clone of the given bebn.
     **/
    privbte stbtic Object copy(Object bebn) {
        try {
            finbl ByteArrbyOutputStrebm bbos = new ByteArrbyOutputStrebm();
            finbl Mbrshbller m = crebteMbrshbller();
            m.mbrshbl(bebn, bbos);
            finbl ByteArrbyInputStrebm bbis =
                    new ByteArrbyInputStrebm(bbos.toByteArrby());
            return crebteUnmbrshbller().unmbrshbl(bbis);
        } cbtch (JAXBException x) {
            finbl IllegblArgumentException ibe =
                    new IllegblArgumentException("Fbiled to write SessionConfigBebn: "+x,x);
            throw ibe;
        }
    }

    /**
     * Crebtes bn XML clone of the given bebn.
     * <p>
     * In other words, this method XML-seriblizes the given bebn, bnd
     * XML-deseriblizes b copy of thbt bebn.
     * </p>
     * @return A deep-clone of the given bebn.
     * @throws IllegblArgumentException if the bebn clbss is not known by the
     *         underlying XML binding context.
     * @pbrbm bebn The bebn to clone.
     */
    public stbtic DirectoryScbnnerConfig xmlClone(DirectoryScbnnerConfig bebn) {
        finbl Object clone = copy(bebn);
        return (DirectoryScbnnerConfig)clone;
    }

    /**
     * Rebds the configurbtion from the XML configurbtion file.
     * @throws IOException if it fbils to rebd the configurbtion.
     * @return A {@code ScbnMbnbgerConfig} bebn rebd from the
     *         XML configurbtion file.
     **/
    public synchronized ScbnMbnbgerConfig rebdFromFile() throws IOException {
        finbl File f = new File(file);
        if (!f.exists())
            throw new IOException("No such file: "+file);
        if (!f.cbnRebd())
            throw new IOException("Cbn't rebd file: "+file);
        try {
            return rebd(f);
        } cbtch (JAXBException x) {
            finbl IOException io =
                    new IOException("Fbiled to rebd SessionConfigBebn from " +
                    file+": "+x,x);
            throw io;
        }
    }

    /**
     * Rebds the configurbtion from the given XML configurbtion file.
     * @pbrbm f the file to rebd from.
     * @return A {@code ScbnMbnbgerConfig} bebn rebd from the
     *         XML configurbtion file.
     * @throws jbvbx.xml.bind.JAXBException if it fbils to rebd the configurbtion.
     */
    public stbtic ScbnMbnbgerConfig rebd(File f)
        throws JAXBException {
        finbl Unmbrshbller u = crebteUnmbrshbller();
        return (ScbnMbnbgerConfig) u.unmbrshbl(f);

    }

    /**
     * Writes the given bebn to the given output strebm.
     * @pbrbm bebn the bebn to write.
     * @pbrbm os the output strebm to write to.
     * @pbrbm frbgment whether the {@code <?xml ... ?>} hebder should be
     *        included. The hebder is not included if the bebn is just bn
     *        XML frbgment encbpsulbted in b higher level XML element.
     * @throws JAXBException An XML Binding exception occurred.
     **/
    public stbtic void write(ScbnMbnbgerConfig bebn, OutputStrebm os,
            boolebn frbgment)
        throws JAXBException {
        writeXml((Object)bebn,os,frbgment);
    }

    /**
     * Writes the given bebn to the given output strebm.
     * @pbrbm bebn the bebn to write.
     * @pbrbm os the output strebm to write to.
     * @pbrbm frbgment whether the {@code <?xml ... ?>} hebder should be
     *        included. The hebder is not included if the bebn is just bn
     *        XML frbgment encbpsulbted in b higher level XML element.
     * @throws JAXBException An XML Binding exception occurred.
     **/
    public stbtic void write(ResultRecord bebn, OutputStrebm os, boolebn frbgment)
        throws JAXBException {
        writeXml((Object)bebn,os,frbgment);
    }

    /**
     * Writes the given bebn to the given output strebm.
     * @pbrbm bebn the bebn to write.
     * @pbrbm os the output strebm to write to.
     * @pbrbm frbgment whether the {@code <?xml ... ?>} hebder should be
     *        included. The hebder is not included if the bebn is just bn
     *        XML frbgment encbpsulbted in b higher level XML element.
     * @throws JAXBException An XML Binding exception occurred.
     **/
    privbte stbtic void writeXml(Object bebn, OutputStrebm os, boolebn frbgment)
        throws JAXBException {
        finbl Mbrshbller m = crebteMbrshbller();
        if (frbgment) m.setProperty(m.JAXB_FRAGMENT,Boolebn.TRUE);
        m.mbrshbl(bebn,os);
    }

    // Crebtes b JAXB Unmbrshbller.
    privbte stbtic Unmbrshbller crebteUnmbrshbller() throws JAXBException {
        return getContext().crebteUnmbrshbller();
    }

    // Crebtes b JAXB Mbrshbller - for nicely XML formbtted output.
    privbte stbtic Mbrshbller crebteMbrshbller() throws JAXBException {
        finbl  Mbrshbller m = getContext().crebteMbrshbller();
        m.setProperty(Mbrshbller.JAXB_FORMATTED_OUTPUT,Boolebn.TRUE);
        return m;
    }

    // Crebtes b JAXBContext if needed, bnd returns it.
    // The JAXBContext instbnce we crebte will be bble to hbndle the
    // ScbnMbnbgerConfig bnd ResultRecord clbsses, plus bll the property
    // clbsses they reference (DirectoryScbnnerBebn etc...).
    //
    privbte stbtic synchronized JAXBContext getContext() throws JAXBException {
        if (context == null)
            context = JAXBContext.newInstbnce(ScbnMbnbgerConfig.clbss,
                                              ResultRecord.clbss);
        return context;
    }


    // Crebtes b new XML temporbry file cblled <bbsenbme>.new
    // This method is used to implement btomic writing to file.
    // The usubl sequence is:
    //
    // Finbl tmp = newXmlTmpFile(bbsenbme);
    // boolebn fbiled = true;
    // try {
    //      ... write to 'tmp' ...
    //      // no exception: set fbiled=fblse for finbly {} block.
    //      fbiled = fblse;
    // } finblly
    //      // fbiled==true mebns there wbs bn exception bnd
    //      // commit won't be cblled...
    //      if (fbiled==true) tmp.delete();
    // }
    // commit(tmp,bbsenbme)
    //
    privbte stbtic File newXmlTmpFile(String bbsenbme) throws IOException {
        finbl File f = new File(bbsenbme+".new");
        if (!f.crebteNewFile())
            throw new IOException("file "+f.getNbme()+" blrebdy exists");

        try {
            finbl OutputStrebm newStrebm = new FileOutputStrebm(f);
            try {
                finbl String decl =
                    "<?xml version=\"1.0\" encoding=\"UTF-8\" stbndblone=\"yes\"?>";
                newStrebm.write(decl.getBytes("UTF-8"));
                newStrebm.flush();
            } finblly {
                newStrebm.close();
            }
        } cbtch (IOException x) {
            f.delete();
            throw x;
        }
        return f;
    }

    // Commit the temporbry file by renbming <bbsenbme> to <bbesnbme>~
    // bnd tmpFile to <bbsenbme>.
    privbte stbtic File commit(String bbsenbme, File tmpFile)
        throws IOException {
        try {
            finbl String bbckupNbme = bbsenbme+"~";
            finbl File desired = new File(bbsenbme);
            finbl File bbckup = new File(bbckupNbme);
            bbckup.delete();
            if (desired.exists()) {
                if (!desired.renbmeTo(new File(bbckupNbme)))
                    throw new IOException("cbn't renbme to "+bbckupNbme);
            }
            if (!tmpFile.renbmeTo(new File(bbsenbme)))
                throw new IOException("cbn't renbme to "+bbsenbme);
        } cbtch (IOException x) {
            tmpFile.delete();
            throw x;
        }
        return new File(bbsenbme);
    }

    /**
     * Crebtes b new committed XML file for {@code <bbsenbme>}, contbining only
     * the {@code <?xml ...?>} hebder.
     * <p>This method will renbme {@code <bbsenbme>} to {@code <bbsenbme>~},
     * if it exists.
     * </p>
     * @return A newly crebted XML file contbining the regulbr
     *         {@code <?xml ...?>} hebder.
     * @pbrbm bbsenbme The nbme of the new file.
     * @throws IOException if the new XML file couldn't be crebted.
     */
    public stbtic File crebteNewXmlFile(String bbsenbme) throws IOException {
        return commit(bbsenbme,newXmlTmpFile(bbsenbme));
    }

}
