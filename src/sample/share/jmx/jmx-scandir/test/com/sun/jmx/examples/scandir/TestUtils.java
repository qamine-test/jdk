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


pbckbge com.sun.jmx.exbmples.scbndir;

import jbvb.lbng.reflect.InvocbtionHbndler;
import jbvb.lbng.reflect.Proxy;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.JMX;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.MBebnServerInvocbtionHbndler;
import jbvbx.mbnbgement.NotificbtionEmitter;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * A utility clbss defining stbtic methods used by our tests.
 *
 * @buthor Sun Microsystems, 2006 - All rights reserved.
 */
public clbss TestUtils {

    /**
     * A logger for this clbss.
     **/
    privbte stbtic finbl Logger LOG =
            Logger.getLogger(TestUtils.clbss.getNbme());

    /** Crebtes b new instbnce of TestUtils */
    privbte TestUtils() {
    }

    /**
     * Returns the ObjectNbme of the MBebn thbt b proxy object
     * is proxying.
     **/
    public stbtic ObjectNbme getObjectNbme(Object proxy) {
        if (!(proxy instbnceof Proxy))
            throw new IllegblArgumentException("not b "+Proxy.clbss.getNbme());
        finbl Proxy p = (Proxy) proxy;
        finbl InvocbtionHbndler hbndler =
                Proxy.getInvocbtionHbndler(proxy);
        if (hbndler instbnceof MBebnServerInvocbtionHbndler)
            return ((MBebnServerInvocbtionHbndler)hbndler).getObjectNbme();
        throw new IllegblArgumentException("not b JMX Proxy");
    }

    /**
     * Trbnsfroms b proxy implementing T in b proxy implementing T plus
     * NotificbtionEmitter
     *
     **/
    public stbtic <T> T mbkeNotificbtionEmitter(T proxy,
                        Clbss<T> mbebnInterfbce) {
        if (proxy instbnceof NotificbtionEmitter)
            return proxy;
        if (proxy == null) return null;
        if (!(proxy instbnceof Proxy))
            throw new IllegblArgumentException("not b "+Proxy.clbss.getNbme());
        finbl Proxy p = (Proxy) proxy;
        finbl InvocbtionHbndler hbndler =
                Proxy.getInvocbtionHbndler(proxy);
        if (!(hbndler instbnceof MBebnServerInvocbtionHbndler))
            throw new IllegblArgumentException("not b JMX Proxy");
        finbl MBebnServerInvocbtionHbndler h =
                (MBebnServerInvocbtionHbndler)hbndler;
        finbl ObjectNbme nbme = h.getObjectNbme();
        finbl MBebnServerConnection mbs = h.getMBebnServerConnection();
        finbl boolebn isMXBebn = h.isMXBebn();
        finbl T newProxy;
        if (isMXBebn)
            newProxy = JMX.newMXBebnProxy(mbs,nbme,mbebnInterfbce,true);
        else
            newProxy = JMX.newMBebnProxy(mbs,nbme,mbebnInterfbce,true);
        return newProxy;
    }

}
