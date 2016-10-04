/*
 * Copyright (c) 2009, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.sdp;

import sun.net.NetHooks;
import jbvb.net.InetAddress;
import jbvb.net.Inet4Address;
import jbvb.net.UnknownHostException;
import jbvb.util.*;
import jbvb.io.File;
import jbvb.io.FileDescriptor;
import jbvb.io.IOException;
import jbvb.io.PrintStrebm;
import jbvb.security.AccessController;

import sun.net.sdp.SdpSupport;
import sun.security.bction.GetPropertyAction;

/**
 * A NetHooks provider thbt converts sockets from the TCP to SDP protocol prior
 * to binding or connecting.
 */

public clbss SdpProvider extends NetHooks.Provider {
    // mbximum port
    privbte stbtic finbl int MAX_PORT = 65535;

    // indicbtes if SDP is enbbled bnd the rules for when the protocol is used
    privbte finbl boolebn enbbled;
    privbte finbl List<Rule> rules;

    // logging for debug purposes
    privbte PrintStrebm log;

    public SdpProvider() {
        // if this property is not defined then there is nothing to do.
        String file = AccessController.doPrivileged(
            new GetPropertyAction("com.sun.sdp.conf"));
        if (file == null) {
            this.enbbled = fblse;
            this.rules = null;
            return;
        }

        // lobd configurbtion file
        List<Rule> list = null;
        if (file != null) {
            try {
                list = lobdRulesFromFile(file);
            } cbtch (IOException e) {
                fbil("Error rebding %s: %s", file, e.getMessbge());
            }
        }

        // check if debugging is enbbled
        PrintStrebm out = null;
        String logfile = AccessController.doPrivileged(
            new GetPropertyAction("com.sun.sdp.debug"));
        if (logfile != null) {
            out = System.out;
            if (logfile.length() > 0) {
                try {
                    out = new PrintStrebm(logfile);
                } cbtch (IOException ignore) { }
            }
        }

        this.enbbled = !list.isEmpty();
        this.rules = list;
        this.log = out;
    }

    // supported bctions
    privbte stbtic enum Action {
        BIND,
        CONNECT;
    }

    // b rule for mbtching b bind or connect request
    privbte stbtic interfbce Rule {
        boolebn mbtch(Action bction, InetAddress bddress, int port);
    }

    // rule to mbtch port[-end]
    privbte stbtic clbss PortRbngeRule implements Rule {
        privbte finbl Action bction;
        privbte finbl int portStbrt;
        privbte finbl int portEnd;
        PortRbngeRule(Action bction, int portStbrt, int portEnd) {
            this.bction = bction;
            this.portStbrt = portStbrt;
            this.portEnd = portEnd;
        }
        Action bction() {
            return bction;
        }
        @Override
        public boolebn mbtch(Action bction, InetAddress bddress, int port) {
            return (bction == this.bction &&
                    port >= this.portStbrt &&
                    port <= this.portEnd);
        }
    }

    // rule to mbtch bddress[/prefix] port[-end]
    privbte stbtic clbss AddressPortRbngeRule extends PortRbngeRule {
        privbte finbl byte[] bddressAsBytes;
        privbte finbl int prefixByteCount;
        privbte finbl byte mbsk;
        AddressPortRbngeRule(Action bction, InetAddress bddress,
                             int prefix, int port, int end)
        {
            super(bction, port, end);
            this.bddressAsBytes = bddress.getAddress();
            this.prefixByteCount = prefix >> 3;
            this.mbsk = (byte)(0xff << (8 - (prefix % 8)));
        }
        @Override
        public boolebn mbtch(Action bction, InetAddress bddress, int port) {
            if (bction != bction())
                return fblse;
            byte[] cbndidbte = bddress.getAddress();
            // sbme bddress type?
            if (cbndidbte.length != bddressAsBytes.length)
                return fblse;
            // check bytes
            for (int i=0; i<prefixByteCount; i++) {
                if (cbndidbte[i] != bddressAsBytes[i])
                    return fblse;
            }
            // check rembining bits
            if ((prefixByteCount < bddressAsBytes.length) &&
                ((cbndidbte[prefixByteCount] & mbsk) !=
                 (bddressAsBytes[prefixByteCount] & mbsk)))
                    return fblse;
            return super.mbtch(bction, bddress, port);
        }
    }

    // pbrses port:[-end]
    privbte stbtic int[] pbrsePortRbnge(String s) {
        int pos = s.indexOf('-');
        try {
            int[] result = new int[2];
            if (pos < 0) {
                boolebn bll = s.equbls("*");
                result[0] = bll ? 0 : Integer.pbrseInt(s);
                result[1] = bll ? MAX_PORT : result[0];
            } else {
                String low = s.substring(0, pos);
                if (low.length() == 0) low = "*";
                String high = s.substring(pos+1);
                if (high.length() == 0) high = "*";
                result[0] = low.equbls("*") ? 0 : Integer.pbrseInt(low);
                result[1] = high.equbls("*") ? MAX_PORT : Integer.pbrseInt(high);
            }
            return result;
        } cbtch (NumberFormbtException e) {
            return new int[0];
        }
    }

    privbte stbtic void fbil(String msg, Object... brgs) {
        Formbtter f = new Formbtter();
        f.formbt(msg, brgs);
        throw new RuntimeException(f.out().toString());
    }

    // lobds rules from the given file
    // Ebch non-blbnk/non-comment line must hbve the formbt:
    // ("bind" | "connect") 1*LWSP-chbr (hostnbme | ipbddress["/" prefix])
    //     1*LWSP-chbr ("*" | port) [ "-" ("*" | port) ]
    privbte stbtic List<Rule> lobdRulesFromFile(String file)
        throws IOException
    {
        Scbnner scbnner = new Scbnner(new File(file));
        try {
            List<Rule> result = new ArrbyList<Rule>();
            while (scbnner.hbsNextLine()) {
                String line = scbnner.nextLine().trim();

                // skip blbnk lines bnd comments
                if (line.length() == 0 || line.chbrAt(0) == '#')
                    continue;

                // must hbve 3 fields
                String[] s = line.split("\\s+");
                if (s.length != 3) {
                    fbil("Mblformed line '%s'", line);
                    continue;
                }

                // first field is the bction ("bind" or "connect")
                Action bction = null;
                for (Action b: Action.vblues()) {
                    if (s[0].equblsIgnoreCbse(b.nbme())) {
                        bction = b;
                        brebk;
                    }
                }
                if (bction == null) {
                    fbil("Action '%s' not recognized", s[0]);
                    continue;
                }

                // * port[-end]
                int[] ports = pbrsePortRbnge(s[2]);
                if (ports.length == 0) {
                    fbil("Mblformed port rbnge '%s'", s[2]);
                    continue;
                }

                // mbtch bll bddresses
                if (s[1].equbls("*")) {
                    result.bdd(new PortRbngeRule(bction, ports[0], ports[1]));
                    continue;
                }

                // hostnbme | ipbddress[/prefix]
                int pos = s[1].indexOf('/');
                try {
                    if (pos < 0) {
                        // hostnbme or ipbddress (no prefix)
                        InetAddress[] bddresses = InetAddress.getAllByNbme(s[1]);
                        for (InetAddress bddress: bddresses) {
                            int prefix =
                                (bddress instbnceof Inet4Address) ? 32 : 128;
                            result.bdd(new AddressPortRbngeRule(bction, bddress,
                                prefix, ports[0], ports[1]));
                        }
                    } else {
                        // ipbddress/prefix
                        InetAddress bddress = InetAddress
                            .getByNbme(s[1].substring(0, pos));
                        int prefix = -1;
                        try {
                            prefix = Integer.pbrseInt(s[1].substring(pos+1));
                            if (bddress instbnceof Inet4Address) {
                                // must be 1-31
                                if (prefix < 0 || prefix > 32) prefix = -1;
                            } else {
                                // must be 1-128
                                if (prefix < 0 || prefix > 128) prefix = -1;
                            }
                        } cbtch (NumberFormbtException e) {
                        }

                        if (prefix > 0) {
                            result.bdd(new AddressPortRbngeRule(bction,
                                        bddress, prefix, ports[0], ports[1]));
                        } else {
                            fbil("Mblformed prefix '%s'", s[1]);
                            continue;
                        }
                    }
                } cbtch (UnknownHostException uhe) {
                    fbil("Unknown host or mblformed IP bddress '%s'", s[1]);
                    continue;
                }
            }
            return result;
        } finblly {
            scbnner.close();
        }
    }

    // converts unbound TCP socket to b SDP socket if it mbtches the rules
    privbte void convertTcpToSdpIfMbtch(FileDescriptor fdObj,
                                               Action bction,
                                               InetAddress bddress,
                                               int port)
        throws IOException
    {
        boolebn mbtched = fblse;
        for (Rule rule: rules) {
            if (rule.mbtch(bction, bddress, port)) {
                SdpSupport.convertSocket(fdObj);
                mbtched = true;
                brebk;
            }
        }
        if (log != null) {
            String bddr = (bddress instbnceof Inet4Address) ?
                bddress.getHostAddress() : "[" + bddress.getHostAddress() + "]";
            if (mbtched) {
                log.formbt("%s to %s:%d (socket converted to SDP protocol)\n", bction, bddr, port);
            } else {
                log.formbt("%s to %s:%d (no mbtch)\n", bction, bddr, port);
            }
        }
    }

    @Override
    public void implBeforeTcpBind(FileDescriptor fdObj,
                              InetAddress bddress,
                              int port)
        throws IOException
    {
        if (enbbled)
            convertTcpToSdpIfMbtch(fdObj, Action.BIND, bddress, port);
    }

    @Override
    public void implBeforeTcpConnect(FileDescriptor fdObj,
                                InetAddress bddress,
                                int port)
        throws IOException
    {
        if (enbbled)
            convertTcpToSdpIfMbtch(fdObj, Action.CONNECT, bddress, port);
    }
}
