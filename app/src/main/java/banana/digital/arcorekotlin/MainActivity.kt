package banana.digital.arcorekotlin

import android.animation.Animator
import android.animation.ValueAnimator
import android.net.sip.SipSession
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.math.MathUtils
import banana.digital.arcorekotlin.model.Ratings
import com.google.ar.core.HitResult
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.*
import com.google.ar.sceneform.ux.ArFragment

class
MainActivity : AppCompatActivity() {

    private var arFragment: ArFragment? = null
    private var renderable: Renderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arFragment = supportFragmentManager.findFragmentById(R.id.ar) as ArFragment?

        initialize()

        arFragment?.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            //addSphere(hitResult)
            addRatings(hitResult)
        }
    }

    private fun initialize() {
        val color = Color(0x00FF00)
        val radius = .1f
        val center = Vector3(0.0f, 0.0f, 0.0f)
        MaterialFactory.makeOpaqueWithColor(this, color)
            .thenAccept {material -> renderable = ShapeFactory.makeSphere(radius, center, material) }
    }

    private fun addSphere(hitResult: HitResult?) {
        val containerNode = AnchorNode(hitResult!!.createAnchor())
        containerNode.setParent(arFragment!!.arSceneView.scene)

        for (i in 0..2) {
            for (j in 0..3 ) {
                val node = Node()
                node.setParent(containerNode)
                node.renderable = renderable
                node.localPosition = Vector3(i * 0.2f, 0.0f, j * 0.2f)
                animate(node, true)
            }
        }
    }

    private fun animate(node: Node, direction: Boolean) {
        val a = if (direction) 0.0f else 1.0f
        var animator = ValueAnimator.ofFloat(a, 1 - a)
        animator.duration = 5000
        animator.startDelay = (Math.random() * 1000).toLong()
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            node.localPosition = Vector3(node.localPosition.x, value, node.localPosition.z)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(p0: Animator?) {
                //xTODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationEnd(p0: Animator?) {
                animate(node, !direction)
            }

            override fun onAnimationCancel(p0: Animator?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onAnimationStart(p0: Animator?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
        animator.start()
    }

    private fun addRatings(hitResult: HitResult) {
        val containerNode = AnchorNode(hitResult!!.createAnchor())
        containerNode.setParent(arFragment!!.arSceneView.scene)

        val ratingsView = RatingsVeiw(this)
        ratingsView.ratings = Ratings.createtestRatings()
        ViewRenderable.builder()
            .setView(this, ratingsView)
            .build()
            .thenAccept { renderable ->
                val node = Node()
                node.setParent(containerNode)
                node.renderable = renderable
            }
    }

}
