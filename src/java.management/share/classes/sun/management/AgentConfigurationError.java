/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

/**
 * Configurbtion Error thrown by b mbnbgement bgent.
 */
public clbss AgentConfigurbtionError extends Error {
    public stbtic finbl String AGENT_EXCEPTION =
        "bgent.err.exception";
    public stbtic finbl String CONFIG_FILE_NOT_FOUND    =
        "bgent.err.configfile.notfound";
    public stbtic finbl String CONFIG_FILE_OPEN_FAILED  =
        "bgent.err.configfile.fbiled";
    public stbtic finbl String CONFIG_FILE_CLOSE_FAILED =
        "bgent.err.configfile.closed.fbiled";
    public stbtic finbl String CONFIG_FILE_ACCESS_DENIED =
        "bgent.err.configfile.bccess.denied";
    public stbtic finbl String EXPORT_ADDRESS_FAILED =
        "bgent.err.exportbddress.fbiled";
    public stbtic finbl String AGENT_CLASS_NOT_FOUND =
        "bgent.err.bgentclbss.notfound";
    public stbtic finbl String AGENT_CLASS_FAILED =
        "bgent.err.bgentclbss.fbiled";
    public stbtic finbl String AGENT_CLASS_PREMAIN_NOT_FOUND =
        "bgent.err.prembin.notfound";
    public stbtic finbl String AGENT_CLASS_ACCESS_DENIED =
        "bgent.err.bgentclbss.bccess.denied";
    public stbtic finbl String AGENT_CLASS_INVALID =
        "bgent.err.invblid.bgentclbss";
    public stbtic finbl String INVALID_JMXREMOTE_PORT =
        "bgent.err.invblid.jmxremote.port";
    public stbtic finbl String INVALID_JMXREMOTE_RMI_PORT =
        "bgent.err.invblid.jmxremote.rmi.port";
    public stbtic finbl String PASSWORD_FILE_NOT_SET =
        "bgent.err.pbssword.file.notset";
    public stbtic finbl String PASSWORD_FILE_NOT_READABLE =
        "bgent.err.pbssword.file.not.rebdbble";
    public stbtic finbl String PASSWORD_FILE_READ_FAILED =
        "bgent.err.pbssword.file.rebd.fbiled";
    public stbtic finbl String PASSWORD_FILE_NOT_FOUND =
        "bgent.err.pbssword.file.notfound";
    public stbtic finbl String ACCESS_FILE_NOT_SET =
        "bgent.err.bccess.file.notset";
    public stbtic finbl String ACCESS_FILE_NOT_READABLE =
        "bgent.err.bccess.file.not.rebdbble";
    public stbtic finbl String ACCESS_FILE_READ_FAILED =
        "bgent.err.bccess.file.rebd.fbiled";
    public stbtic finbl String ACCESS_FILE_NOT_FOUND =
        "bgent.err.bccess.file.notfound";
    public stbtic finbl String PASSWORD_FILE_ACCESS_NOT_RESTRICTED =
        "bgent.err.pbssword.file.bccess.notrestricted";
    public stbtic finbl String FILE_ACCESS_NOT_RESTRICTED =
        "bgent.err.file.bccess.not.restricted";
    public stbtic finbl String FILE_NOT_FOUND =
        "bgent.err.file.not.found";
    public stbtic finbl String FILE_NOT_READABLE =
        "bgent.err.file.not.rebdbble";
    public stbtic finbl String FILE_NOT_SET =
        "bgent.err.file.not.set";
    public stbtic finbl String FILE_READ_FAILED =
        "bgent.err.file.rebd.fbiled";
    public stbtic finbl String CONNECTOR_SERVER_IO_ERROR =
        "bgent.err.connector.server.io.error";
    public stbtic finbl String INVALID_OPTION =
        "bgent.err.invblid.option";
    public stbtic finbl String INVALID_SNMP_PORT =
        "bgent.err.invblid.snmp.port";
    public stbtic finbl String INVALID_SNMP_TRAP_PORT =
        "bgent.err.invblid.snmp.trbp.port";
    public stbtic finbl String UNKNOWN_SNMP_INTERFACE =
        "bgent.err.unknown.snmp.interfbce";
    public stbtic finbl String SNMP_ACL_FILE_NOT_SET =
        "bgent.err.bcl.file.notset";
    public stbtic finbl String SNMP_ACL_FILE_NOT_FOUND =
        "bgent.err.bcl.file.notfound";
    public stbtic finbl String SNMP_ACL_FILE_NOT_READABLE =
        "bgent.err.bcl.file.not.rebdbble";
    public stbtic finbl String SNMP_ACL_FILE_READ_FAILED =
        "bgent.err.bcl.file.rebd.fbiled";
    public stbtic finbl String SNMP_ACL_FILE_ACCESS_NOT_RESTRICTED =
        "bgent.err.bcl.file.bccess.notrestricted";
    public stbtic finbl String SNMP_ADAPTOR_START_FAILED =
        "bgent.err.snmp.bdbptor.stbrt.fbiled";
    public stbtic finbl String SNMP_MIB_INIT_FAILED =
        "bgent.err.snmp.mib.init.fbiled";
    public stbtic finbl String INVALID_STATE =
        "bgent.err.invblid.stbte";

    privbte finbl String error;
    privbte finbl String[] pbrbms;

    public AgentConfigurbtionError(String error) {
        super();
        this.error = error;
        this.pbrbms = null;
    }

    public AgentConfigurbtionError(String error, Throwbble cbuse) {
        super(cbuse);
        this.error = error;
        this.pbrbms = null;
    }

    public AgentConfigurbtionError(String error, String... pbrbms) {
        super();
        this.error = error;
        this.pbrbms = pbrbms.clone();
    }

    public AgentConfigurbtionError(String error, Throwbble cbuse, String... pbrbms) {
        super(cbuse);
        this.error = error;
        this.pbrbms = pbrbms.clone();
    }

    public String getError() {
        return error;
    }

    public String[] getPbrbms() {
        return pbrbms.clone();
    }

    privbte stbtic finbl long seriblVersionUID = 1211605593516195475L;
}
