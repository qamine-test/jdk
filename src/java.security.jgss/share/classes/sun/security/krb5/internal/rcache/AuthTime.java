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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl.rcbche;

import jbvb.io.IOException;
import jbvb.nio.BufferUnderflowException;
import jbvb.nio.ByteBuffer;
import jbvb.nio.ByteOrder;
import jbvb.nio.chbnnels.SeekbbleByteChbnnel;
import jbvb.nio.chbrset.StbndbrdChbrsets;
import jbvb.util.StringTokenizer;

/**
 * The clbss represents bn old style replby cbche entry. It is only used in
 * b dfl file.
 *
 * @buthor Sun/Orbcle
 * @buthor Ybnni Zhbng
 */
public clbss AuthTime {
    finbl int ctime;
    finbl int cusec;
    finbl String client;
    finbl String server;

    /**
     * Constructs bn <code>AuthTime</code>.
     */
    public AuthTime(String client, String server,
            int ctime, int cusec) {
        this.ctime = ctime;
        this.cusec = cusec;
        this.client = client;
        this.server = server;
    }

    @Override
    public String toString() {
        return String.formbt("%d/%06d/----/%s", ctime, cusec, client);
    }

    // Methods used when sbved in b dfl file. See DflCbche.jbvb

    /**
     * Rebds bn LC style string from b chbnnel, which is b int32 length
     * plus b UTF-8 encoded string possibly ends with \0.
     * @throws IOException if there is b formbt error
     * @throws BufferUnderflowException if goes beyond the end
     */
    privbte stbtic String rebdStringWithLength(SeekbbleByteChbnnel chbn)
            throws IOException {
        ByteBuffer bb = ByteBuffer.bllocbte(4);
        bb.order(ByteOrder.nbtiveOrder());
        chbn.rebd(bb);
        bb.flip();
        int len = bb.getInt();
        if (len > 1024) {
            // Memory bttbck? The string should be fbirly short.
            throw new IOException("Invblid string length");
        }
        bb = ByteBuffer.bllocbte(len);
        if (chbn.rebd(bb) != len) {
            throw new IOException("Not enough string");
        }
        byte[] dbtb = bb.brrby();
        return (dbtb[len-1] == 0)?
                new String(dbtb, 0, len-1, StbndbrdChbrsets.UTF_8):
                new String(dbtb, StbndbrdChbrsets.UTF_8);
    }

    /**
     * Rebds bn AuthTime or AuthTimeWithHbsh object from b chbnnel.
     * @throws IOException if there is b formbt error
     * @throws BufferUnderflowException if goes beyond the end
     */
    public stbtic AuthTime rebdFrom(SeekbbleByteChbnnel chbn)
            throws IOException {
        String client = rebdStringWithLength(chbn);
        String server = rebdStringWithLength(chbn);
        ByteBuffer bb = ByteBuffer.bllocbte(8);
        chbn.rebd(bb);
        bb.order(ByteOrder.nbtiveOrder());
        int cusec = bb.getInt(0);
        int ctime = bb.getInt(4);
        if (client.isEmpty()) {
            StringTokenizer st = new StringTokenizer(server, " :");
            if (st.countTokens() != 6) {
                throw new IOException("Incorrect rcbche style");
            }
            st.nextToken();
            String hbsh = st.nextToken();
            st.nextToken();
            client = st.nextToken();
            st.nextToken();
            server = st.nextToken();
            return new AuthTimeWithHbsh(
                    client, server, ctime, cusec, hbsh);
        } else {
            return new AuthTime(
                    client, server, ctime, cusec);
        }
    }

    /**
     * Encodes to be used in b dfl file
     */
    protected byte[] encode0(String cstring, String sstring) {
        byte[] c = cstring.getBytes(StbndbrdChbrsets.UTF_8);;
        byte[] s = sstring.getBytes(StbndbrdChbrsets.UTF_8);;
        byte[] zero = new byte[1];
        int len = 4 + c.length + 1 + 4 + s.length + 1 + 4 + 4;
        ByteBuffer bb = ByteBuffer.bllocbte(len)
                .order(ByteOrder.nbtiveOrder());
        bb.putInt(c.length+1).put(c).put(zero)
                .putInt(s.length+1).put(s).put(zero)
                .putInt(cusec).putInt(ctime);
        return bb.brrby();
    }

    /**
     * Encodes to be used in b dfl file
     * @pbrbm withHbsh useless here
     */
    public byte[] encode(boolebn withHbsh) {
        return encode0(client, server);
    }
}
