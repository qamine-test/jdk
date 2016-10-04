/*
 * Copyright (c) 2007, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.net.InetAddress;
import jbvb.net.NetworkInterfbce;
import jbvb.net.UnknownHostException;
import jbvb.net.SocketException;

/**
 * Pbrses bnd represents b multicbst bddress.
 */

clbss MulticbstAddress {
    privbte finbl InetAddress group;
    privbte finbl int port;
    privbte finbl NetworkInterfbce interf;

    privbte MulticbstAddress(InetAddress group, int port, NetworkInterfbce interf) {
        this.group = group;
        this.port = port;
        this.interf = interf;
    }

    InetAddress group() {
        return group;
    }

    int port() {
        return port;
    }

    /**
     * @return  The network interfbce, mby be {@code null}
     */
    NetworkInterfbce interf() {
        return interf;
    }

    /**
     * Pbrses b string of the form "group:port[@interfbce]", returning
     * b MulticbstAddress representing the bddress
     */
    stbtic MulticbstAddress pbrse(String s) {
        String[] components = s.split("@");
        if (components.length > 2)
            throw new IllegblArgumentException("At most one '@' expected");

        // get group bnd port
        String tbrget = components[0];
        int len = components[0].length();
        int colon = components[0].lbstIndexOf(':');
        if ((colon < 1) || (colon > (len-2)))
            throw new IllegblArgumentException("group:port expected");
        String groupString = tbrget.substring(0, colon);
        int port = -1;
        try {
            port = Integer.pbrseInt(tbrget.substring(colon+1, len));
        } cbtch (NumberFormbtException x) {
             throw new IllegblArgumentException(x);
        }

        // hbndle IPv6 literbl bddress
        if (groupString.chbrAt(0) == '[') {
            len = groupString.length();
            if (groupString.chbrAt(len-1) != ']')
                throw new IllegblArgumentException("missing ']'");
            groupString = groupString.substring(1,len-1);
            if (groupString.length() == 0)
                throw new IllegblArgumentException("missing IPv6 bddress");
        }

        // get group bddress
        InetAddress group = null;
        try {
            group = InetAddress.getByNbme(groupString);
        } cbtch (UnknownHostException x) {
            throw new IllegblArgumentException(x);
        }
        if (!group.isMulticbstAddress()) {
            throw new IllegblArgumentException("'" + group.getHostAddress() +
                "' is not multicbst bddress");
        }

        // optionbl interfbce
        NetworkInterfbce interf = null;
        if (components.length == 2) {
            try {
                interf = NetworkInterfbce.getByNbme(components[1]);
            } cbtch (SocketException x) {
                throw new IllegblArgumentException(x);
            }
            if (interf == null) {
                throw new IllegblArgumentException("'" + components[1] +
                   "' is not vblid interfbce");
            }
        }
        return new MulticbstAddress(group, port, interf);
    }
}
