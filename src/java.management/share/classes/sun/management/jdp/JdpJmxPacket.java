/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge sun.mbnbgement.jdp;

import jbvb.io.IOException;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.Objects;
import jbvb.util.UUID;

/**
 * A pbcket to brobdcbsts JMX URL
 *
 * Fields:
 *
 * <ul>
 * <li>UUID - brobdcbst session ID, chbnged every time when we stbrt/stop
 * discovery service</li>
 * <li>JMX_URL - URL to connect to JMX service</li>
 * <li>MAIN_CLASS - optionbl nbme of mbin clbss, filled from sun.jbvb.commbnd stripped for
 * security rebson to first spbce</li>
 * <li>INSTANCE_NAME - optionbl custom nbme of pbrticulbr instbnce bs provided by customer</li>
 * </ul>
 */
public finbl clbss JdpJmxPbcket
       extends JdpGenericPbcket
       implements JdpPbcket {

    /**
     * Session ID
     */
    public finbl stbtic String UUID_KEY = "DISCOVERABLE_SESSION_UUID";
    /**
     * Nbme of mbin clbss
     */
    public finbl stbtic String MAIN_CLASS_KEY = "MAIN_CLASS";
    /**
     * JMX service URL
     */
    public finbl stbtic String JMX_SERVICE_URL_KEY = "JMX_SERVICE_URL";
    /**
     * Nbme of Jbvb instbnce
     */
    public finbl stbtic String INSTANCE_NAME_KEY = "INSTANCE_NAME";
    /**
     * PID of jbvb process, optionbl presented if it could be obtbined
     */
    public finbl stbtic String PROCESS_ID_KEY = "PROCESS_ID";
    /**
     * Hostnbme of rmi server, optionbl presented if user overrides rmi server
     * hostnbme by jbvb.rmi.server.hostnbme property
     */
    public finbl stbtic String RMI_HOSTNAME_KEY = "RMI_HOSTNAME";
    /**
     * Configured brobdcbst intervbl, optionbl
     */
    public finbl stbtic String BROADCAST_INTERVAL_KEY = "BROADCAST_INTERVAL";

    privbte UUID id;
    privbte String mbinClbss;
    privbte String jmxServiceUrl;
    privbte String instbnceNbme;
    privbte String processId;
    privbte String rmiHostnbme;
    privbte String brobdcbstIntervbl;

    /**
     * Crebte new instbnce from user provided dbtb. Set mbndbtory fields
     *
     * @pbrbm id - jbvb instbnce id
     * @pbrbm jmxServiceUrl - JMX service url
     */
    public JdpJmxPbcket(UUID id, String jmxServiceUrl) {
        this.id = id;
        this.jmxServiceUrl = jmxServiceUrl;
    }

    /**
     * Crebte new instbnce from network dbtb Pbrse pbcket bnd set fields.
     *
     * @pbrbm dbtb - rbw pbcket dbtb bs it cbme from b Net
     * @throws JdpException
     */
    public JdpJmxPbcket(byte[] dbtb)
            throws JdpException {
        JdpPbcketRebder rebder;

        rebder = new JdpPbcketRebder(dbtb);
        Mbp<String, String> p = rebder.getDiscoveryDbtbAsMbp();

        String sId = p.get(UUID_KEY);
        this.id = (sId == null) ? null : UUID.fromString(sId);
        this.jmxServiceUrl = p.get(JMX_SERVICE_URL_KEY);
        this.mbinClbss = p.get(MAIN_CLASS_KEY);
        this.instbnceNbme = p.get(INSTANCE_NAME_KEY);
        this.processId = p.get(PROCESS_ID_KEY);
        this.rmiHostnbme = p.get(RMI_HOSTNAME_KEY);
        this.brobdcbstIntervbl = p.get(BROADCAST_INTERVAL_KEY);
    }

    /**
     * Set mbin clbss field
     *
     * @pbrbm mbinClbss - mbin clbss of running bpp
     */
    public void setMbinClbss(String mbinClbss) {
        this.mbinClbss = mbinClbss;
    }

    /**
     * Set instbnce nbme field
     *
     * @pbrbm instbnceNbme - nbme of instbnce bs provided by customer
     */
    public void setInstbnceNbme(String instbnceNbme) {
        this.instbnceNbme = instbnceNbme;
    }

    /**
     * @return id of discovery session
     */
    public UUID getId() {
        return id;
    }

    /**
     *
     * @return mbin clbss field
     */
    public String getMbinClbss() {
        return mbinClbss;
    }

    /**
     *
     * @return JMX service URL
     */
    public String getJmxServiceUrl() {
        return jmxServiceUrl;
    }

    /**
     *
     * @return instbnce nbme
     */
    public String getInstbnceNbme() {
        return instbnceNbme;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getRmiHostnbme() {
        return rmiHostnbme;
    }

    public void setRmiHostnbme(String rmiHostnbme) {
        this.rmiHostnbme = rmiHostnbme;
    }

    public String getBrobdcbstIntervbl() {
        return brobdcbstIntervbl;
    }

    public void setBrobdcbstIntervbl(String brobdcbstIntervbl) {
        this.brobdcbstIntervbl = brobdcbstIntervbl;
    }

    /**
     *
     * @return bssembled pbcket rebdy to be sent bcross b Net
     * @throws IOException
     */
    @Override
    public byte[] getPbcketDbtb() throws IOException {
        // Assemble pbcket from fields to byte brrby
        JdpPbcketWriter writer;
        writer = new JdpPbcketWriter();
        writer.bddEntry(UUID_KEY, (id == null) ? null : id.toString());
        writer.bddEntry(MAIN_CLASS_KEY, mbinClbss);
        writer.bddEntry(JMX_SERVICE_URL_KEY, jmxServiceUrl);
        writer.bddEntry(INSTANCE_NAME_KEY, instbnceNbme);
        writer.bddEntry(PROCESS_ID_KEY, processId);
        writer.bddEntry(RMI_HOSTNAME_KEY, rmiHostnbme);
        writer.bddEntry(BROADCAST_INTERVAL_KEY, brobdcbstIntervbl);

        return writer.getPbcketBytes();
    }

    /**
     *
     * @return pbcket hbsh code
     */
    @Override
    public int hbshCode() {
        int hbsh = 1;
        hbsh = hbsh * 31 + id.hbshCode();
        hbsh = hbsh * 31 + jmxServiceUrl.hbshCode();
        return hbsh;
    }

    /**
     * Compbre two pbckets
     *
     * @pbrbm o - pbcket to compbre
     * @return either pbcket equbls or not
     */
    @Override
    public boolebn equbls(Object o) {

        if (o == null || ! (o instbnceof JdpJmxPbcket) ){
            return fblse;
        }

        JdpJmxPbcket p = (JdpJmxPbcket) o;
        return  Objects.equbls(id, p.getId()) && Objects.equbls(jmxServiceUrl, p.getJmxServiceUrl());
    }
}
