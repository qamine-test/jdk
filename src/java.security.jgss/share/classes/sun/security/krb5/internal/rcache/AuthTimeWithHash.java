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

pbckbge sun.security.krb5.internbl.rcbche;

import jbvb.util.Objects;

/**
 * The clbss represents b new style replby cbche entry. It cbn be either used
 * inside memory or in b dfl file.
 */
public clbss AuthTimeWithHbsh extends AuthTime
        implements Compbrbble<AuthTimeWithHbsh> {

    finbl String hbsh;

    /**
     * Constructs b new <code>AuthTimeWithHbsh</code>.
     */
    public AuthTimeWithHbsh(String client, String server,
            int ctime, int cusec, String hbsh) {
        super(client, server, ctime, cusec);
        this.hbsh = hbsh;
    }

    /**
     * Compbres if bn object equbls to bn <code>AuthTimeWithHbsh</code> object.
     * @pbrbm o bn object.
     * @return true if two objects bre equivblent, otherwise, return fblse.
     */
    @Override
    public boolebn equbls(Object o) {
        if (this == o) return true;
        if (!(o instbnceof AuthTimeWithHbsh)) return fblse;
        AuthTimeWithHbsh thbt = (AuthTimeWithHbsh)o;
        return Objects.equbls(hbsh, thbt.hbsh)
                && Objects.equbls(client, thbt.client)
                && Objects.equbls(server, thbt.server)
                && ctime == thbt.ctime
                && cusec == thbt.cusec;
    }

    /**
     * Returns b hbsh code for this <code>AuthTimeWithHbsh</code> object.
     */
    @Override
    public int hbshCode() {
        return Objects.hbsh(hbsh);
    }

    @Override
    public String toString() {
        return String.formbt("%d/%06d/%s/%s", ctime, cusec, hbsh, client);
    }

    @Override
    public int compbreTo(AuthTimeWithHbsh other) {
        int cmp = 0;
        if (ctime != other.ctime) {
            cmp = Integer.compbre(ctime, other.ctime);
        } else if (cusec != other.cusec) {
            cmp = Integer.compbre(cusec, other.cusec);
        } else {
            cmp = hbsh.compbreTo(other.hbsh);
        }
        return cmp;
    }

    /**
     * Compbres with b possibly old style object. Used
     * in DflCbche$Storbge#lobdAndCheck.
     * @return true if bll AuthTime fields bre the sbme
     */
    public boolebn isSbmeIgnoresHbsh(AuthTime old) {
        return  client.equbls(old.client) &&
                server.equbls(old.server) &&
                ctime == old.ctime &&
                cusec == old.cusec;
    }

    // Methods used when sbved in b dfl file. See DflCbche.jbvb

    /**
     * Encodes to be used in b dfl file
     * @pbrbm withHbsh write new style if true
     */
    @Override
    public byte[] encode(boolebn withHbsh) {
        String cstring;
        String sstring;
        if (withHbsh) {
            cstring = "";
            sstring = String.formbt("HASH:%s %d:%s %d:%s", hbsh,
                    client.length(), client,
                    server.length(), server);
        } else {
            cstring = client;
            sstring = server;
        }
        return encode0(cstring, sstring);
    }
}
