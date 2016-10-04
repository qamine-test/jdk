/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.net;

/**
 * Constbnts used by the SOCKS protocol implementbtion.
 */

interfbce SocksConsts {
    stbtic finbl int PROTO_VERS4                = 4;
    stbtic finbl int PROTO_VERS         = 5;
    stbtic finbl int DEFAULT_PORT               = 1080;

    stbtic finbl int NO_AUTH            = 0;
    stbtic finbl int GSSAPI             = 1;
    stbtic finbl int USER_PASSW         = 2;
    stbtic finbl int NO_METHODS         = -1;

    stbtic finbl int CONNECT            = 1;
    stbtic finbl int BIND                       = 2;
    stbtic finbl int UDP_ASSOC          = 3;

    stbtic finbl int IPV4                       = 1;
    stbtic finbl int DOMAIN_NAME                = 3;
    stbtic finbl int IPV6                       = 4;

    stbtic finbl int REQUEST_OK         = 0;
    stbtic finbl int GENERAL_FAILURE    = 1;
    stbtic finbl int NOT_ALLOWED                = 2;
    stbtic finbl int NET_UNREACHABLE    = 3;
    stbtic finbl int HOST_UNREACHABLE   = 4;
    stbtic finbl int CONN_REFUSED               = 5;
    stbtic finbl int TTL_EXPIRED                = 6;
    stbtic finbl int CMD_NOT_SUPPORTED  = 7;
    stbtic finbl int ADDR_TYPE_NOT_SUP  = 8;
}
