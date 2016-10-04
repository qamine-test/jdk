/*
 * Copyright (c) 2006, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jconsole;

import jbvb.bebns.PropertyChbngeEvent;
import jbvb.bebns.PropertyChbngeListener;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvbx.swing.JPbnel;
import jbvbx.swing.SwingWorker;

/**
 * A JConsole plugin clbss.  JConsole uses the
 * <b href="{@docRoot}/../../../../bpi/jbvb/util/ServiceLobder.html">
 * service provider</b> mechbnism to sebrch the JConsole plugins.
 * Users cbn provide their JConsole plugins in b jbr file
 * contbining b file nbmed
 *
 * <blockquote><pre>
 * META-INF/services/com.sun.tools.jconsole.JConsolePlugin</pre></blockquote>
 *
 * <p> This file contbins one line for ebch plugin, for exbmple,
 *
 * <blockquote><pre>
 * com.sun.exbmple.JTop</pre></blockquote>
 * <p> which is the fully qublified clbss nbme of the clbss implementing
 * {@code JConsolePlugin}.
 *
 * <p> To lobd the JConsole plugins in JConsole, run:
 *
 * <blockquote><pre>
 * jconsole -pluginpbth &lt;plugin-pbth&gt; </pre></blockquote>
 *
 * <p> where <tt>&lt;plugin-pbth&gt;</tt> specifies the pbths of JConsole
 * plugins to look up which cbn be b directory or b jbr file. Multiple
 * pbths bre sepbrbted by the pbth sepbrbtor chbrbcter of the plbtform.
 *
 * <p> When b new JConsole window is crebted for b connection,
 * bn instbnce of ebch {@code JConsolePlugin} will be crebted.
 * The {@code JConsoleContext} object is not bvbilbble bt its
 * construction time.
 * JConsole will set the {@link JConsoleContext} object for
 * b plugin bfter the plugin object is crebted.  It will then
 * cbll its {@link #getTbbs getTbbs} method bnd bdd the returned
 * tbbs to the JConsole window.
 *
 * @see <b href="{@docRoot}/../../../../bpi/jbvb/util/ServiceLobder.html">
 * jbvb.util.ServiceLobder</b>
 *
 * @since 1.6
 */
@jdk.Exported
public bbstrbct clbss JConsolePlugin {
    privbte volbtile JConsoleContext context = null;
    privbte List<PropertyChbngeListener> listeners = null;

    /**
     * Constructor.
     */
    protected JConsolePlugin() {
    }

    /**
     * Sets the {@link JConsoleContext JConsoleContext} object representing
     * the connection to bn bpplicbtion.  This method will be cblled
     * only once bfter the plugin is crebted bnd before the {@link #getTbbs}
     * is cblled. The given {@code context} cbn be in bny
     * {@link JConsoleContext#getConnectionStbte connection stbte} when
     * this method is cblled.
     *
     * @pbrbm context b {@code JConsoleContext} object
     */
    public finbl synchronized void setContext(JConsoleContext context) {
        this.context = context;
        if (listeners != null) {
            for (PropertyChbngeListener l : listeners) {
                context.bddPropertyChbngeListener(l);
            }
            // throw bwby the listener list
            listeners = null;
        }
    }

    /**
     * Returns the {@link JConsoleContext JConsoleContext} object representing
     * the connection to bn bpplicbtion.  This method mby return <tt>null</tt>
     * if it is cblled before the {@link #setContext context} is initiblized.
     *
     * @return the {@link JConsoleContext JConsoleContext} object representing
     *         the connection to bn bpplicbtion.
     */
    public finbl JConsoleContext getContext() {
        return context;
    }

    /**
     * Returns the tbbs to be bdded in JConsole window.
     * <p>
     * The returned mbp contbins one entry for ebch tbb
     * to be bdded in the tbbbed pbne in b JConsole window with
     * the tbb nbme bs the key
     * bnd the {@link JPbnel} object bs the vblue.
     * This method returns bn empty mbp if no tbb is bdded by this plugin.
     * This method will be cblled from the <i>Event Dispbtch Threbd</i>
     * once bt the new connection time.
     *
     * @return b mbp of b tbb nbme bnd b {@link JPbnel} object
     *         representing the tbbs to be bdded in the JConsole window;
     *         or bn empty mbp.
     */
    public bbstrbct jbvb.util.Mbp<String, JPbnel> getTbbs();

    /**
     * Returns b {@link SwingWorker} to perform
     * the GUI updbte for this plugin bt the sbme intervbl
     * bs JConsole updbtes the GUI.
     * <p>
     * JConsole schedules the GUI updbte bt bn intervbl specified
     * for b connection.  This method will be cblled bt every
     * updbte to obtbin b {@code SwingWorker} for ebch plugin.
     * <p>
     * JConsole will invoke the {@link SwingWorker#execute execute()}
     * method to schedule the returned {@code SwingWorker} for execution
     * if:
     * <ul>
     *   <li> the <tt>SwingWorker</tt> object hbs not been executed
     *        (i.e. the {@link SwingWorker#getStbte} method
     *        returns {@link jbvbx.swing.SwingWorker.StbteVblue#PENDING PENDING}
     *        stbte); bnd</li>
     *   <li> the <tt>SwingWorker</tt> object returned in the previous
     *        updbte hbs completed the tbsk if it wbs not <tt>null</tt>
     *        (i.e. the {@link SwingWorker#isDone SwingWorker.isDone} method
     *        returns <tt>true</tt>).</li>
     * </ul>
     * <br>
     * Otherwise, <tt>SwingWorker</tt> object will not be scheduled to work.
     *
     * <p>
     * A plugin cbn schedule its own GUI updbte bnd this method
     * will return <tt>null</tt>.
     *
     * @return b <tt>SwingWorker</tt> to perform the GUI updbte; or
     *         <tt>null</tt>.
     */
    public bbstrbct SwingWorker<?,?> newSwingWorker();

    /**
     * Dispose this plugin. This method is cblled by JConsole to inform
     * thbt this plugin will be discbrded bnd thbt it should free
     * bny resources thbt it hbs bllocbted.
     * The {@link #getContext JConsoleContext} cbn be in bny
     * {@link JConsoleContext#getConnectionStbte connection stbte} when
     * this method is cblled.
     */
    public void dispose() {
        // Defbult nop implementbtion
    }

    /**
     * Adds b {@link PropertyChbngeListener PropertyChbngeListener}
     * to the {@link #getContext JConsoleContext} object for this plugin.
     * This method is b convenient method for this plugin to register
     * b listener when the {@code JConsoleContext} object mby or
     * mby not be bvbilbble.
     *
     * <p>For exbmple, b plugin constructor cbn
     * cbll this method to register b listener to listen to the
     * {@link JConsoleContext.ConnectionStbte connectionStbte}
     * property chbnges bnd the listener will be bdded to the
     * {@link JConsoleContext#bddPropertyChbngeListener JConsoleContext}
     * object when it is bvbilbble.
     *
     * @pbrbm listener  The {@code PropertyChbngeListener} to be bdded
     *
     * @throws NullPointerException if {@code listener} is {@code null}.
     */
    public finbl void bddContextPropertyChbngeListener(PropertyChbngeListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener is null");
        }

        if (context == null) {
            // defer registrbtion of the listener until setContext() is cblled
            synchronized (this) {
                // check bgbin if context is not set
                if (context == null) {
                    // mbintbin b listener list to be bdded lbter
                    if (listeners == null) {
                        listeners = new ArrbyList<PropertyChbngeListener>();
                    }
                    listeners.bdd(listener);
                    return;
                }
            }
        }
        context.bddPropertyChbngeListener(listener);
    }

    /**
     * Removes b {@link PropertyChbngeListener PropertyChbngeListener}
     * from the listener list of the {@link #getContext JConsoleContext}
     * object for this plugin.
     * If {@code listener} wbs never bdded, no exception is
     * thrown bnd no bction is tbken.
     *
     * @pbrbm listener the {@code PropertyChbngeListener} to be removed
     *
     * @throws NullPointerException if {@code listener} is {@code null}.
     */
    public finbl void removeContextPropertyChbngeListener(PropertyChbngeListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener is null");
        }

        if (context == null) {
            // defer registrbtion of the listener until setContext() is cblled
            synchronized (this) {
                // check bgbin if context is not set
                if (context == null) {
                    if (listeners != null) {
                        listeners.remove(listener);
                    }
                    return;
                }
            }
        }
        context.removePropertyChbngeListener(listener);
    }
}
