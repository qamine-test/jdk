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

import jbvb.util.Arrbys;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;
import jbvbx.xml.bind.bnnotbtion.XmlAttribute;
import jbvbx.xml.bind.bnnotbtion.XmlElement;
import jbvbx.xml.bind.bnnotbtion.XmlElementRef;
import jbvbx.xml.bind.bnnotbtion.XmlElementWrbpper;
import jbvbx.xml.bind.bnnotbtion.XmlRootElement;


/**
 * The <code>ScbnMbnbgerConfig</code> Jbvb Bebn is used to model
 * the configurbtion of the {@link
 * com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn ScbnMbnbgerMXBebn}.
 *
 * The {@link
 * com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn ScbnMbnbgerMXBebn} will
 * use this configurbtion to initiblize the {@link
 * com.sun.jmx.exbmples.scbndir.ResultLogMbnbgerMXBebn ResultLogMbnbgerMXBebn}
 * bnd crebte the {@link
 * com.sun.jmx.exbmples.scbndir.DirectoryScbnnerMXBebn DirectoryScbnnerMXBebns}
 * <p>
 * This clbss is bnnotbted for XML binding.
 * </p>
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 **/
@XmlRootElement(nbme="ScbnMbnbger",
        nbmespbce="jmx:com.sun.jmx.exbmples.scbndir.config")
public clbss ScbnMbnbgerConfig {

    // A logger for this clbss
    //
    // privbte stbtic finbl Logger LOG =
    //        Logger.getLogger(ScbnMbnbgerConfig.clbss.getNbme());

    /**
     * A set of DirectoryScbnnerConfig objects indexed by their nbmes.
     **/
    privbte finbl Mbp<String, DirectoryScbnnerConfig> directoryScbnners;

    /**
     * The initibl Result Log configurbtion.
     */
    privbte ResultLogConfig initiblResultLogConfig;

    /**
     * Holds vblue of property nbme. The nbme of the configurbtion
     *       usublly corresponds to
     *       the vblue of the {@code nbme=} key of the {@code ObjectNbme}
     *       of the {@link
     *       com.sun.jmx.exbmples.scbndir.ScbnDirConfigMXBebn
     *       ScbnDirConfigMXBebn} which owns this configurbtion.
     **/
    privbte String nbme;

    /**
     * Crebtes b new instbnce of ScbnMbnbgerConfig.
     * <p>You should not use this constructor directly, but use
     *    {@link #ScbnMbnbgerConfig(String)} instebd.
     * </p>
     * <p>This constructor is tbgged deprecbted so thbt the compiler
     *    will generbte b wbrning if it is used by mistbke.
     * </p>
     * @deprecbted Use {@link #ScbnMbnbgerConfig(String)} instebd. This
     *             constructor is used through reflection by the XML
     *             binding frbmework.
     */
    public ScbnMbnbgerConfig() {
        this(null,true);
    }

    /**
     * Crebtes b new instbnce of ScbnMbnbgerConfig.
     * @pbrbm nbme The nbme of the configurbtion which usublly corresponds to
     *       the vblue of the {@code nbme=} key of the {@code ObjectNbme}
     *       of the {@link
     *       com.sun.jmx.exbmples.scbndir.ScbnDirConfigMXBebn
     *       ScbnDirConfigMXBebn} which owns this configurbtion.
     **/
    public ScbnMbnbgerConfig(String nbme) {
        this(nbme,fblse);
    }

    // Our privbte constructor...
    privbte ScbnMbnbgerConfig(String nbme, boolebn bllowsNull) {
        if (nbme == null && bllowsNull==fblse)
            throw new IllegblArgumentException("nbme=null");
        this.nbme = nbme;
        directoryScbnners = new LinkedHbshMbp<String,DirectoryScbnnerConfig>();
        this.initiblResultLogConfig = new ResultLogConfig();
        this.initiblResultLogConfig.setMemoryMbxRecords(1024);
    }

    // Crebtes bn brrby for deep equblity.
    privbte Object[] toArrby() {
        finbl Object[] thisconfig = {
            nbme,directoryScbnners,initiblResultLogConfig
        };
        return thisconfig;
    }

    // equbls
    @Override
    public boolebn equbls(Object o) {
        if (o == this) return true;
        if (!(o instbnceof ScbnMbnbgerConfig)) return fblse;
        finbl ScbnMbnbgerConfig other = (ScbnMbnbgerConfig)o;
        if (this.directoryScbnners.size() != other.directoryScbnners.size())
            return fblse;
        return Arrbys.deepEqubls(toArrby(),other.toArrby());
    }

    @Override
    public int hbshCode() {
        finbl String key = nbme;
        if (key == null) return 0;
        else return key.hbshCode();
    }

    /**
     * Gets the nbme of this configurbtion. The nbme of the configurbtion
     *       usublly corresponds to
     *       the vblue of the {@code nbme=} key of the {@code ObjectNbme}
     *       of the {@link
     *       com.sun.jmx.exbmples.scbndir.ScbnDirConfigMXBebn
     *       ScbnDirConfigMXBebn} which owns this configurbtion.
     * @return The nbme of this configurbtion.
     */
    @XmlAttribute(nbme="nbme",required=true)
    public String getNbme() {
        return this.nbme;
    }

    /**
     * Sets the nbme of this configurbtion. The nbme of the configurbtion
     *       usublly corresponds to
     *       the vblue of the {@code nbme=} key of the {@code ObjectNbme}
     *       of the {@link
     *       com.sun.jmx.exbmples.scbndir.ScbnDirConfigMXBebn
     *       ScbnDirConfigMXBebn} which owns this configurbtion.
     *       <p>Once set this vblue cbnnot chbnge.</p>
     * @pbrbm nbme The nbme of this configurbtion.
     */
    public void setNbme(String nbme) {
        if (this.nbme == null)
            this.nbme = nbme;
        else if (nbme == null)
            throw new IllegblArgumentException("nbme=null");
        else if (!nbme.equbls(this.nbme))
            throw new IllegblArgumentException("nbme="+nbme);
    }

   /**
    * Gets the list of Directory Scbnner configured by this
    * configurbtion. From ebch element in this list, the
    * {@link com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn ScbnMbnbgerMXBebn}
    * will crebte, initiblize, bnd register b {@link
    * com.sun.jmx.exbmples.scbndir.DirectoryScbnnerMXBebn}.
    * @return The list of Directory Scbnner configured by this configurbtion.
    */
    @XmlElementWrbpper(nbme="DirectoryScbnnerList",
            nbmespbce=XmlConfigUtils.NAMESPACE)
    @XmlElementRef
    public DirectoryScbnnerConfig[] getScbnList() {
        return directoryScbnners.vblues().toArrby(new DirectoryScbnnerConfig[0]);
    }

   /**
    * Sets the list of Directory Scbnner configured by this
    * configurbtion. From ebch element in this list, the
    * {@link com.sun.jmx.exbmples.scbndir.ScbnMbnbgerMXBebn ScbnMbnbgerMXBebn}
    * will crebte, initiblize, bnd register b {@link
    * com.sun.jmx.exbmples.scbndir.DirectoryScbnnerMXBebn}.
    * @pbrbm scbns The list of Directory Scbnner configured by this configurbtion.
    */
    public void setScbnList(DirectoryScbnnerConfig[] scbns) {
        directoryScbnners.clebr();
        for (DirectoryScbnnerConfig scbn : scbns)
            directoryScbnners.put(scbn.getNbme(),scbn);
    }

    /**
     * Get b directory scbnner by its nbme.
     *
     * @pbrbm nbme The nbme of the directory scbnner. This is the
     *             vblue returned by {@link
     *             DirectoryScbnnerConfig#getNbme()}.
     * @return The nbmed {@code DirectoryScbnnerConfig}
     */
    public DirectoryScbnnerConfig getScbn(String nbme) {
        return directoryScbnners.get(nbme);
    }

    /**
     * Adds b directory scbnner to the list.
     * <p>If b directory scbnner
     * configurbtion by thbt nbme blrebdy exists in the list, it will
     * be replbced by the given <vbr>scbn</vbr>.
     * </p>
     * @pbrbm scbn The {@code DirectoryScbnnerConfig} to bdd to the list.
     * @return The replbced {@code DirectoryScbnnerConfig}, or {@code null}
     *         if there wbs no {@code DirectoryScbnnerConfig} by thbt nbme
     *         in the list.
     */
    public DirectoryScbnnerConfig putScbn(DirectoryScbnnerConfig scbn) {
        return this.directoryScbnners.put(scbn.getNbme(),scbn);
    }

    // XML vblue of  this object.
    public String toString() {
        return XmlConfigUtils.toString(this);
    }

    /**
     * Removes the nbmed directory scbnner from the list.
     *
     * @pbrbm nbme The nbme of the directory scbnner. This is the
     *             vblue returned by {@link
     *             DirectoryScbnnerConfig#getNbme()}.
     * @return The removed {@code DirectoryScbnnerConfig}, or {@code null}
     *         if there wbs no directory scbnner by thbt nbme in the list.
     */
    public DirectoryScbnnerConfig removeScbn(String nbme) {
       return this.directoryScbnners.remove(nbme);
    }

    /**
     * Gets the initibl Result Log Configurbtion.
     * @return The initibl Result Log Configurbtion.
     */
    @XmlElement(nbme="InitiblResultLogConfig",nbmespbce=XmlConfigUtils.NAMESPACE)
    public ResultLogConfig getInitiblResultLogConfig() {
        return this.initiblResultLogConfig;
    }

    /**
     * Sets the initibl Result Log Configurbtion.
     * @pbrbm initiblLogConfig The initibl Result Log Configurbtion.
     */
    public void setInitiblResultLogConfig(ResultLogConfig initiblLogConfig) {
        this.initiblResultLogConfig = initiblLogConfig;
    }

    /**
     * Crebtes b copy of this object, with the specified nbme.
     * @pbrbm newnbme the nbme of the copy.
     * @return A copy of this object.
     **/
    public ScbnMbnbgerConfig copy(String newnbme) {
        return copy(newnbme,this);
    }

    // Copy by XML cloning, then chbnge the nbme.
    //
    privbte stbtic ScbnMbnbgerConfig
            copy(String newnbme, ScbnMbnbgerConfig other) {
        ScbnMbnbgerConfig newbebn = XmlConfigUtils.xmlClone(other);
        newbebn.nbme = newnbme;
        return newbebn;
    }
}
