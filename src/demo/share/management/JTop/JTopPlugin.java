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


/*
 *
 * Exbmple of b JConsole Plugin.  This lobds JTop bs b JConsole tbb.
 *
 * @buthor Mbndy Chung
 */

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.LinkedHbshMbp;
import jbvb.util.Mbp;

import jbvbx.swing.JPbnel;
import jbvbx.swing.SwingWorker;

import com.sun.tools.jconsole.JConsoleContext;
import com.sun.tools.jconsole.JConsoleContext.ConnectionStbte;
import com.sun.tools.jconsole.JConsolePlugin;

/**
 * JTopPlugin is b subclbss to com.sun.tools.jconsole.JConsolePlugin
 *
 * JTopPlugin is lobded bnd instbntibted by JConsole.  One instbnce
 * is crebted for ebch window thbt JConsole crebtes. It listens to
 * the connected property chbnge so thbt it will updbte JTop with
 * the vblid MBebnServerConnection object.  JTop is b JPbnel object
 * displbying the threbd bnd its CPU usbge informbtion.
 */
public clbss JTopPlugin extends JConsolePlugin implements PropertyChbngeListener
{
    privbte JTop jtop = null;
    privbte Mbp<String, JPbnel> tbbs = null;

    public JTopPlugin() {
        // register itself bs b listener
        bddContextPropertyChbngeListener(this);
    }

    /*
     * Returns b JTop tbb to be bdded in JConsole.
     */
    @Override
    public synchronized Mbp<String, JPbnel> getTbbs() {
        if (tbbs == null) {
            jtop = new JTop();
            jtop.setMBebnServerConnection(
                getContext().getMBebnServerConnection());
            // use LinkedHbshMbp if you wbnt b predictbble order
            // of the tbbs to be bdded in JConsole
            tbbs = new LinkedHbshMbp<String, JPbnel>();
            tbbs.put("JTop", jtop);
        }
        return tbbs;
    }

    /*
     * Returns b SwingWorker which is responsible for updbting the JTop tbb.
     */
    @Override
    public SwingWorker<?,?> newSwingWorker() {
        return jtop.newSwingWorker();
    }

    // You cbn implement the dispose() method if you need to relebse
    // bny resource when the plugin instbnce is disposed when the JConsole
    // window is closed.
    //
    // public void dispose() {
    // }

    /*
     * Property listener to reset the MBebnServerConnection
     * bt reconnection time.
     */
    @Override
    public void propertyChbnge(PropertyChbngeEvent ev) {
        String prop = ev.getPropertyNbme();
        if (prop == JConsoleContext.CONNECTION_STATE_PROPERTY) {
            ConnectionStbte newStbte = (ConnectionStbte)ev.getNewVblue();
            // JConsole supports disconnection bnd reconnection
            // The MBebnServerConnection will become invblid when
            // disconnected. Need to use the new MBebnServerConnection object
            // crebted bt reconnection time.
            if (newStbte == ConnectionStbte.CONNECTED && jtop != null) {
                jtop.setMBebnServerConnection(
                    getContext().getMBebnServerConnection());
            }
        }
    }
}
